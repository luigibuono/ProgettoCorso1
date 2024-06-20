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
import entities.Professore;


/**
 * Servlet implementation class ProfRegister
 */
@WebServlet("/jsp/ProfRegister")
public class ProfRegister extends HttpServlet {
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

	        String nome = req.getParameter("nome");
	        String cognome = req.getParameter("cognome");
	        String tipo_utente = req.getParameter("tipo_utente");
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");
	        

	        try {
	            CourseDAO courseDAO = new CourseDAO(connection);
	            Professore newProfessore = new Professore(0, nome, cognome,tipo_utente, username , password);
	            boolean success = courseDAO.insertProfessore(newProfessore);
	            if (success) {
	                res.sendRedirect("/Zucchetti/jsp/index.jsp");
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

