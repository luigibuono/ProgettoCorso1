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

import dao.BookDAO;
import entities.BookData;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/book", "Luigi", "Buono");

            BookDAO bookDAO = new BookDAO(con); // Initialize DAO with connection
            BookData book = bookDAO.getBookById(id).orElse(null);

            if (book != null) {
                pw.println("<form action='editurl?id=" + id + "' method='post'>");
                pw.println("<table align='center'>");
                pw.println("<tr>");
                pw.println("<td>Book Name</td>");
                pw.println("<td><input type='text' name='bookName' value='" + book.getBookName() + "'></td>");
                pw.println("</tr>");

                pw.println("<tr>");
                pw.println("<td>Book Edition</td>");
                pw.println("<td><input type='text' name='bookEdition' value='" + book.getBookEdition() + "'></td>");
                pw.println("</tr>");

                pw.println("<tr>");
                pw.println("<td>Book Price</td>");
                pw.println("<td><input type='text' name='bookPrice' value='" + book.getBookPrice() + "'></td>");
                pw.println("</tr>");

                pw.println("<tr>");
                pw.println("<td><input type='submit' value='Edit'></td>");
                pw.println("<td><input type='reset' value='Cancel'></td>");
                pw.println("</tr>");
                pw.println("</table>");
                pw.println("</form>");
            } else {
                pw.println("<h2>Book not found</h2>");
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
        pw.println("<a href='home.html'>Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
