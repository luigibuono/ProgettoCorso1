package controllers;

import java.io.IOException;
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


@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/universita", "Luigi", "Buono");

            CourseDAO CourseDAO = new CourseDAO(con);
            CourseData course = CourseDAO.getCourseById(id).orElse(null);

            if (course != null) {
                req.setAttribute("course", course);
                req.getRequestDispatcher("jsp/editScreen.jsp").forward(req, res);
            } else {
            	res.sendRedirect("html/ErrorUpdate.html");
            }

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
