package com.scanbuy.webapp.Service;

import com.scanbuy.webapp.DataAccessLayer.BookRepository;
import com.scanbuy.webapp.Models.Book;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class BookServices implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Override
    public void addBook(Book book) throws Exception{
        Optional<Book> book1 = bookRepository.findById(book.getIsbn());
        if (book1.isPresent()) {
            throw new Exception("Book Already Exists");
        }
        bookRepository.save(book);
    }

    @Override
    public Book getBook(Long isbn) throws Exception{
        Optional<Book> book1 = bookRepository.findById(isbn);
        if (book1.isPresent()) {
            return book1.get();
        } else {
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
                Book book = new Book(isbn, title, author, numPages, "", false, 0.0f, imageUrl);
                return  bookRepository.save(book);
            }
            else{
                throw new Exception("No Book Found");
            }
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Long isbn, Book updatedBook) throws Exception {
        Optional<Book> optionalBook = bookRepository.findById(updatedBook.getIsbn());
        if (optionalBook.isPresent() && optionalBook.get().getIsbn()==isbn) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setNumPages(updatedBook.getNumPages());
            book.setNotes(updatedBook.getNotes());
            book.setRead(updatedBook.isRead());
            book.setRating(updatedBook.getRating());
            book.setImageUrl(updatedBook.getImageUrl());
            Book newBook = bookRepository.save(book);
            return newBook;
        } else {
            throw new Exception("No Book Found");
        }
    }

    @Override
    public void deleteBook(Long isbn) throws Exception{
        Optional<Book> book1 = bookRepository.findById(isbn);
        if (book1.isPresent()) {
            bookRepository.deleteById(isbn);
        } else {
            throw new Exception("No Book Found");
        }
    }
}
