package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entities.BookData;



public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertBook(BookData book) throws SQLException {
        String query = "INSERT INTO BOOKDATA(BOOKNAME, BOOKEDITION, BOOKPRICE) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookEdition());
            ps.setFloat(3, book.getBookPrice());
            int count = ps.executeUpdate();
            return count == 1;
        }
    }

    public boolean updateBook(BookData book) throws SQLException {
        String query = "UPDATE BOOKDATA SET BOOKNAME=?, BOOKEDITION=?, BOOKPRICE=? WHERE ID=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getBookEdition());
            ps.setFloat(3, book.getBookPrice());
            ps.setInt(4, book.getId());
            int count = ps.executeUpdate();
            return count == 1;
        }
    }

    public List<BookData> getAllBooks() throws SQLException {
        List<BookData> books = new ArrayList<>();
        String query = "SELECT ID, BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String bookName = rs.getString("BOOKNAME");
                String bookEdition = rs.getString("BOOKEDITION");
                float bookPrice = rs.getFloat("BOOKPRICE");
                BookData book = new BookData(id, bookName, bookEdition, bookPrice);
                books.add(book);
            }
        }
        return books;
    }
    
    public Optional<BookData> getBookById(int id) throws SQLException {
        String query = "SELECT ID, BOOKNAME, BOOKEDITION, BOOKPRICE FROM BOOKDATA WHERE ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                BookData book = new BookData(rs.getInt("ID"), rs.getString("BOOKNAME"), rs.getString("BOOKEDITION"), rs.getFloat("BOOKPRICE"));
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }
    
    public boolean deleteBook(int id) throws SQLException {
        String query = "DELETE FROM BOOKDATA WHERE ID=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        }
    }
}
