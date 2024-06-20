package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDAO;
import entities.CourseData;



@WebServlet("/courseList")
public class CourseListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/universita", "Luigi", "Buono");

            CourseDAO courseDAO = new CourseDAO(con);
            List<CourseData> courses;
            
            String searchIdStr = req.getParameter("searchId");
            if (searchIdStr != null && !searchIdStr.trim().isEmpty()) {
                try {
                    int searchId = Integer.parseInt(searchIdStr);
                    courses = courseDAO.searchCourseById(searchId);
                } catch (NumberFormatException e) {
                    courses = courseDAO.getAllCourses();
                    req.setAttribute("errorMessage", "L'ID inserito non è valido. Visualizzazione di tutti i corsi.");
                }
            } else {
                courses = courseDAO.getAllCourses();
            }
            
            req.setAttribute("courses", courses);
            req.getRequestDispatcher("jsp/courselist.jsp").forward(req, res);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            res.getWriter().println("<h1>Error: " + e.getMessage() + "</h1>");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
