package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDAO;



@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // get PrintWriter
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/universita", "Luigi", "Buono");

            CourseDAO courseDAO = new CourseDAO(con); // Initialize DAO with connection
            boolean success = courseDAO.deleteCourse(id); // Delete book using DAO

            if (success) {
                res.sendRedirect("html/deleteError.html"); // Redirect to the update page
            } else {
                res.sendRedirect("/"); // Redirect to the error page
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        pw.println("<a href='html/home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='courseList'>Courses List</a>");
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
