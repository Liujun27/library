package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Book;

public class BookDAO {
	public BookDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
 
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8", "root",
                "270531");
    }
 
    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select count(*) from book";
 
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
 
            System.out.println("total:" + total);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return total;
    }
 
    public void add(Book book) {
 
        String sql = "insert into book values(null,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, book.bookName);
            ps.setString(2, book.author);
            ps.setString(3, book.publishDate);
 
            ps.execute();
 
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                book.id = id;
            }
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    public void update(Book book) {
 
        String sql = "update book set bookName= ?, author = ? , publishDate = ? where id = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setString(1, book.bookName);
            ps.setString(2, book.author);
            ps.setString(3, book.publishDate);
            ps.setInt(4, book.id);
 
            ps.execute();
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
 
    }
 
    public void delete(int id) {
 
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
 
            String sql = "delete from book where id = " + id;
 
            s.execute(sql);
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
    }
 
    public Book get(int id) {
        Book book = null;
 
        try (Connection c = getConnection(); Statement s = c.createStatement();) {
 
            String sql = "select * from book where id = " + id;
 
            ResultSet rs = s.executeQuery(sql);
 
            if (rs.next()) {
                book = new Book();
                String bookName = rs.getString(2);
                String author = rs.getString("author");
                String publishDate = rs.getString(4);
                book.bookName = bookName;
                book.author = author;
                book.publishDate = publishDate;
                book.id = id;
            }
 
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return book;
    }
 
    public List<Book> list() {
        return list(0, Short.MAX_VALUE);
    }
 
    public List<Book> list(int start, int count) {
        List<Book> books = new ArrayList<Book>();
 
        String sql = "select * from book order by id desc limit ?,? ";
 
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
 
            ps.setInt(1, start);
            ps.setInt(2, count);
 
            ResultSet rs = ps.executeQuery();
 
            while (rs.next()) {
                Book book = new Book();
                int id = rs.getInt(1);
                String bookName = rs.getString(2);
                String author = rs.getString("author");
                String publishDate = rs.getString(4);
                book.id = id;
                book.bookName = bookName;
                book.author = author;
                book.publishDate = publishDate;
                books.add(book);
            }
            
        } catch (SQLException e) {
 
            e.printStackTrace();
        }
        return books;
    }
}
