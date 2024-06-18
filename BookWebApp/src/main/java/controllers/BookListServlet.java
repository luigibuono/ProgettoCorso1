package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import entities.BookData;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // get PrintWriter
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/book", "Luigi", "Buono");

            BookDAO bookDAO = new BookDAO(con); // Initialize DAO with connection
            List<BookData> books = bookDAO.getAllBooks(); // Retrieve all books using DAO

            pw.println("<table border='1' align='center'>");
            pw.println("<tr>");
            pw.println("<th>Book Id</th>");
            pw.println("<th>Book Name</th>");
            pw.println("<th>Book Edition</th>");
            pw.println("<th>Book Price</th>");
            pw.println("<th>Edit</th>");
            pw.println("<th>Delete</th>");
            pw.println("</tr>");
            for (BookData book : books) {
                pw.println("<tr>");
                pw.println("<td>" + book.getId() + "</td>");
                pw.println("<td>" + book.getBookName() + "</td>");
                pw.println("<td>" + book.getBookEdition() + "</td>");
                pw.println("<td>" + book.getBookPrice() + "</td>");
                pw.println("<td><a href='editScreen?id=" + book.getId() + "'>Edit</a></td>");
                pw.println("<td><a href='deleteurl?id=" + book.getId() + "'>Delete</a></td>");
                pw.println("</tr>");
            }
            pw.println("</table>");
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
