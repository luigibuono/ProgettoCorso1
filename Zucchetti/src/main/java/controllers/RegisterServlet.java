package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDAO;
import entities.CourseData;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection connection; // Store connection in servlet

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/universita", "Luigi", "Buono");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database initialization failed", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String courseName = req.getParameter("courseName");
        String courseDuration = req.getParameter("courseDuration");
        Float coursePrice = null; // Inizializzare a null

        // Verifica se bookPrice è stato fornito come parametro
        String coursePriceStr = req.getParameter("coursePrice");
        if (coursePriceStr != null && !coursePriceStr.isEmpty()) {
            try {
            	coursePrice = Float.parseFloat(coursePriceStr); // Converti in Float se presente
            } catch (NumberFormatException e) {
                pw.println("<div class='alert alert-danger' role='alert'>Invalid price format.</div>");
                pw.println("<a href='courseList'>Courses List </a>");
                return;
            }
        } else {
        	res.sendRedirect("html/404.html");
            pw.println("<a href='courseList'>Courses List </a>");
            return;
        }

        // Se tutti i parametri sono validi, procedi con l'inserimento del libro nel database
        try {
            CourseDAO courseDAO = new CourseDAO(connection); // Crea un'istanza di BookDAO con la connessione
            CourseData newCourse = new CourseData(0, courseName, courseDuration, coursePrice); // Crea un nuovo oggetto BookData
            boolean success = courseDAO.insertCourse(newCourse); // Esegui l'inserimento nel database
            if (success) {
                res.sendRedirect("html/successo.html"); // Reindirizza alla home se l'inserimento ha successo
            } else {
                pw.println("html/404.html");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }

        // Stampare i link di navigazione
        pw.println("<a href='html/home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='courseList'>Courses List </a>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (connection != null) {
                connection.close(); // Close the connection when servlet is destroyed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}