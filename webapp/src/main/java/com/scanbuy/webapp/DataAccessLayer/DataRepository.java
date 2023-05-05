package com.scanbuy.webapp.DataAccessLayer;

import com.scanbuy.webapp.Models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class DataRepository {
    public SessionFactory DBConnection(){
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();
        return sessionFactory;
    }
    public void addBook(Book book){
        SessionFactory db=DBConnection();
        Session session = db.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        db.close();
    }
    public Book getBook(Long isbn) throws Exception {
        SessionFactory db = DBConnection();
        Session session = db.openSession();
        session.beginTransaction();
        // query book by ID
        Book book = session.get(Book.class, isbn);
        if (book != null) {
            session.getTransaction().commit();
            session.close();
            db.close();
            return book;
        }
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
        URL url = new URL(apiUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        StringBuilder sb = new StringBuilder();
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }
        JSONObject response = new JSONObject(sb.toString());
        JSONArray items = response.getJSONArray("items");

        if (items.length() > 0) {
            JSONObject volumeInfo = items.getJSONObject(0).getJSONObject("volumeInfo");

            String title = volumeInfo.getString("title");
            String author = volumeInfo.getJSONArray("authors").getString(0);
            int numPages = volumeInfo.getInt("pageCount");
            String imageUrl = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
            book = new Book(isbn, title, author, numPages, "", false, 0.0f, imageUrl);
            session.save(book);
            session.getTransaction().commit();
            session.close();
            db.close();

            return book;
        } else {
            throw new Exception("No Book Found");
        }
    }
    public void deleteBook(Long isbn) throws Exception {
        SessionFactory db = DBConnection();
        Session session = db.openSession();
        session.beginTransaction();
        Book book = session.get(Book.class, isbn);
        if (book != null) {
            session.delete(book);
            session.getTransaction().commit();
            session.close();
            db.close();
        } else {
            session.getTransaction().rollback();
            session.close();
            db.close();
            throw new Exception("No Book Found");
        }
    }
    public void updateBook(Long isbn, Book updatedBook) throws Exception {
        SessionFactory db = DBConnection();
        Session session = db.openSession();
        session.beginTransaction();

        Book book = session.get(Book.class, isbn);
        if (book == null) {
            session.getTransaction().rollback();
            session.close();
            db.close();
            throw new Exception("Book not found");
        }

        if (!book.getIsbn().equals(updatedBook.getIsbn())) {
            session.getTransaction().rollback();
            session.close();
            db.close();
            throw new Exception("ISBN does not match");
        }

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setNumPages(updatedBook.getNumPages());
        book.setNotes(updatedBook.getNotes());
        book.setRead(updatedBook.isRead());
        book.setRating(updatedBook.getRating());
        book.setImageUrl(updatedBook.getImageUrl());

        session.merge(book);
        session.getTransaction().commit();
        session.close();
        db.close();
    }
    public List<Book> getAllBooks() {
        SessionFactory db = DBConnection();
        Session session = db.openSession();
        List<Book> books = session.createQuery("from Book", Book.class).list();
        session.close();
        db.close();
        return books;
    }


}
