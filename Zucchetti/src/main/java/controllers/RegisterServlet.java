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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Connection connection;

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
        handleRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        handleRequest(req, res);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String courseName = req.getParameter("courseName");
        String courseDuration = req.getParameter("courseDuration");
        String coursePriceStr = req.getParameter("coursePrice");
        Float coursePrice = null;

        if (courseName == null || courseName.isEmpty() ||
            courseDuration == null || courseDuration.isEmpty() ||
            coursePriceStr == null || coursePriceStr.isEmpty()) {
        	res.sendRedirect("html/404.html");
            pw.println("<a href='/Zucchetti/courseList'>Courses List </a>");
            return;
        }

        try {
            coursePrice = Float.parseFloat(coursePriceStr);
        } catch (NumberFormatException e) {
            pw.println("<div class='alert alert-danger' role='alert'>Invalid price format.</div>");
            pw.println("<a href='/Zucchetti/courseList'>Courses List </a>");
            return;
        }

        try {
            CourseDAO courseDAO = new CourseDAO(connection);
            CourseData newCourse = new CourseData(0, courseName, courseDuration, coursePrice);
            boolean success = courseDAO.insertCourse(newCourse);
            if (success) {
                res.sendRedirect("html/successo.html");
            } else {
                pw.println("<div class='alert alert-danger' role='alert'>Course registration failed.</div>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
        }

        pw.println("<a href='/Zucchetti/courseList'>Courses List </a>");
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
