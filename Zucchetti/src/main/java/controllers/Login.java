package controllers;

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

import utils.Connessione;

@WebServlet("/jsp/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = Connessione.getCon();
        try {
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery("SELECT username, password FROM studente");
            HttpSession session;

            while (rs.next()) {
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password");

                if (dbUsername != null && dbPassword != null && dbUsername.equalsIgnoreCase(username) && dbPassword.equalsIgnoreCase(password)) {
                    PreparedStatement smt1 = conn.prepareStatement("SELECT matricola FROM studente WHERE username = ?");
                    smt1.setString(1, username);
                    ResultSet rs1 = smt1.executeQuery();
                    rs1.next();
                    String matricola = rs1.getString("matricola");
                    Statement smt2 = conn.createStatement();
                    ResultSet rs2 = smt2.executeQuery("SELECT idcorso, materia, nome, cognome FROM corso JOIN professore ON cattedra = idprofessore");
                    session = request.getSession(true);
                    session.setAttribute("matricola", matricola);
                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/studente.jsp");
                    request.setAttribute("tabella_corso", rs2);
                    rd.forward(request, response);
                    return;
                }
            }

            Statement smt3 = conn.createStatement();
            ResultSet rs3 = smt3.executeQuery("SELECT username, password FROM professore");
            while (rs3.next()) {
                String dbUsername = rs3.getString("username");
                String dbPassword = rs3.getString("password");

                if (dbUsername != null && dbPassword != null && dbUsername.equalsIgnoreCase(username) && dbPassword.equalsIgnoreCase(password)) {
                    session = request.getSession(true);
                    PreparedStatement smt4 = conn.prepareStatement("SELECT nome, cognome, idProfessore FROM professore WHERE username = ?");
                    smt4.setString(1, username);
                    ResultSet rs4 = smt4.executeQuery();
                    rs4.next();
                    String nome = rs4.getString("nome");
                    String cognome = rs4.getString("cognome");
                    int idProfessore = rs4.getInt("idProfessore");
                    PreparedStatement smt5 = conn.prepareStatement("SELECT idcorso, materia FROM corso WHERE cattedra = ?");
                    smt5.setInt(1, idProfessore);
                    ResultSet rs5 = smt5.executeQuery();
                    rs5.next();
                    int idcorso = rs5.getInt("idcorso");
                    String materia = rs5.getString("materia");
                    PreparedStatement smt6 = conn.prepareStatement("SELECT idAppello, Data FROM appello WHERE Materia = ?");
                    smt6.setString(1, materia);
                    ResultSet appelli = smt6.executeQuery();
                    session.setAttribute("nome", nome);
                    session.setAttribute("cognome", cognome);
                    RequestDispatcher rd4 = request.getRequestDispatcher("/jsp/professore.jsp");
                    session.setAttribute("materia", materia);
                    request.setAttribute("appelli", appelli);
                    rd4.forward(request, response);
                    return;
                }
            }

            RequestDispatcher rd3 = request.getRequestDispatcher("/jsp/index.jsp");
            String messaggio = "username e password non sono presenti";
            request.setAttribute("messaggio", messaggio);
            rd3.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
