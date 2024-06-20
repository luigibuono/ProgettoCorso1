package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Connessione;

@WebServlet("/jsp/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = Connessione.getCon()) {
            HttpSession session = request.getSession(true);
            boolean loggedIn = false;

            // Query per gli studenti
            String queryStudente = "SELECT matricola FROM studente WHERE username = ? AND password = ?";
            PreparedStatement smtStudente = conn.prepareStatement(queryStudente);
            smtStudente.setString(1, username);
            smtStudente.setString(2, password);
            ResultSet rsStudente = smtStudente.executeQuery();

            if (rsStudente.next()) {
                String matricola = rsStudente.getString("matricola");
                session.setAttribute("matricola", matricola);
                loggedIn = true;

                // Recupera i corsi e i dati del professore correlati
                String queryCorsi = "SELECT idcorso, materia, nome, cognome " +
                                    "FROM corso JOIN professore ON cattedra = idprofessore";
                PreparedStatement smtCorsi = conn.prepareStatement(queryCorsi);
                ResultSet rsCorsi = smtCorsi.executeQuery();
                request.setAttribute("tabella_corso", rsCorsi);

                RequestDispatcher rd = request.getRequestDispatcher("/jsp/studente.jsp");
                rd.forward(request, response);
            }

            // Se non è un professore e non è uno studente, controlla tra i professori
            if (!loggedIn) {
                String queryProfessore = "SELECT nome, cognome, idProfessore " +
                                         "FROM professore WHERE username = ? AND password = ?";
                PreparedStatement smtProfessore = conn.prepareStatement(queryProfessore);
                smtProfessore.setString(1, username);
                smtProfessore.setString(2, password);
                ResultSet rsProfessore = smtProfessore.executeQuery();

                if (rsProfessore.next()) {
                    String nome = rsProfessore.getString("nome");
                    String cognome = rsProfessore.getString("cognome");
                    int idProfessore = rsProfessore.getInt("idProfessore");

                    String queryCorsoProfessore = "SELECT idcorso, materia " +
                                                  "FROM corso WHERE cattedra = ?";
                    PreparedStatement smtCorsoProfessore = conn.prepareStatement(queryCorsoProfessore);
                    smtCorsoProfessore.setInt(1, idProfessore);
                    ResultSet rsCorsoProfessore = smtCorsoProfessore.executeQuery();

                    if (rsCorsoProfessore.next()) {
                        int idcorso = rsCorsoProfessore.getInt("idcorso");
                        String materia = rsCorsoProfessore.getString("materia");

                        String queryAppelli = "SELECT idAppello, Data FROM appello WHERE Materia = ?";
                        PreparedStatement smtAppelli = conn.prepareStatement(queryAppelli);
                        smtAppelli.setString(1, materia);
                        ResultSet appelli = smtAppelli.executeQuery();

                        session.setAttribute("nome", nome);
                        session.setAttribute("cognome", cognome);
                        session.setAttribute("materia", materia);
                        request.setAttribute("appelli", appelli);

                        RequestDispatcher rd = request.getRequestDispatcher("/jsp/professore.jsp");
                        rd.forward(request, response);
                    }
                }
            }

            // Se non si è trovata corrispondenza per studente o professore, visualizza messaggio di errore
            if (!loggedIn) {
                String messaggio = "Username e/o password non validi";
                request.setAttribute("messaggio", messaggio);
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
                rd.forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Errore durante l'accesso al database";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = request.getRequestDispatcher("/html/404.html");
            rd.forward(request, response);
        }
    }
}
