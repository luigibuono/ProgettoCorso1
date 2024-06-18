package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
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

        Connection conn = Connessione.getCon();
        HttpSession session = request.getSession();

        try {
            // Verifica se è uno studente
            PreparedStatement stmtStudente = conn.prepareStatement("SELECT matricola FROM studente WHERE username = ? AND password = ?");
            stmtStudente.setString(1, username);
            stmtStudente.setString(2, password);
            ResultSet rsStudente = stmtStudente.executeQuery();

            if (rsStudente.next()) {
                String matricola = rsStudente.getString("matricola");

                PreparedStatement stmtCorsi = conn.prepareStatement("SELECT idcorso, materia, nome, cognome FROM corso JOIN professore ON cattedra = idprofessore");
                ResultSet rsCorsi = stmtCorsi.executeQuery();

                session.setAttribute("matricola", matricola);
                request.setAttribute("tabella_corso", rsCorsi);

                RequestDispatcher rd = request.getRequestDispatcher("studente.jsp");
                rd.forward(request, response);
                return;
            }

            // Verifica se è un professore
            PreparedStatement stmtProfessore = conn.prepareStatement("SELECT idProfessore, nome, cognome FROM professore WHERE username = ? AND password = ?");
            stmtProfessore.setString(1, username);
            stmtProfessore.setString(2, password);
            ResultSet rsProfessore = stmtProfessore.executeQuery();

            if (rsProfessore.next()) {
                int idProfessore = rsProfessore.getInt("idProfessore");
                String nome = rsProfessore.getString("nome");
                String cognome = rsProfessore.getString("cognome");

                PreparedStatement stmtCorsiProf = conn.prepareStatement("SELECT idcorso, materia FROM corso WHERE cattedra = ?");
                stmtCorsiProf.setInt(1, idProfessore);
                ResultSet rsCorsiProf = stmtCorsiProf.executeQuery();

                session.setAttribute("nome", nome);
                session.setAttribute("cognome", cognome);
                request.setAttribute("tabella_corso", rsCorsiProf);

                RequestDispatcher rd = request.getRequestDispatcher("professore.jsp");
                rd.forward(request, response);
                return;
            }

            // Se non viene trovata alcuna corrispondenza, reindirizza alla pagina di login con un messaggio di errore
            RequestDispatcher rd3 = request.getRequestDispatcher("index.jsp");
            String messaggio = "Username e password non validi";
            request.setAttribute("messaggio", messaggio);
            rd3.forward(request, response);

        } catch (SQLException e) {
            // Gestione delle eccezioni SQL
            System.out.println(e.getMessage());
        }
    }
}
