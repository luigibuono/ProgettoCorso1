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
import entities.CourseData;



@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));
        String courseName = req.getParameter("courseName");
        String courseDuration = req.getParameter("courseDuration");
        float coursePrice = Float.parseFloat(req.getParameter("coursePrice"));

        CourseData course = new CourseData(id, courseName, courseDuration, coursePrice );

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/universita", "Luigi", "Buono");

            CourseDAO CourseDAO = new CourseDAO(con); // Initialize DAO with connection
            boolean success = CourseDAO.updateCourse(course); // Update book using DAO

            if (success) {
                res.sendRedirect("html/Update.html"); // Redirect to the update page
            } else {
                res.sendRedirect("html/ErrorUpdate.html"); // Redirect to the error page
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

        pw.println("<a href='home/home.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='courseList'>Courses List</a>");
        pw.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
}
