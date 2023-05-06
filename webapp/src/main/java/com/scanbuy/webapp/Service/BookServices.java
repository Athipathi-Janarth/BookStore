package com.scanbuy.webapp.Service;

import com.scanbuy.webapp.DataAccessLayer.BookRepository;
import com.scanbuy.webapp.Models.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookServices implements BookService{
    @Autowired
    BookRepository bookRepository;

    @Override
    public void addBook(Book book) throws Exception{
        Optional<Book> book1 = bookRepository.findById(book.getIsbn());
        if (book1.isPresent()) {
            throw new Exception("Book Already Exists");
        }
    }

    @Override
    public Book getBook(Long isbn) throws Exception{
        Optional<Book> book1 = bookRepository.findById(isbn);
        if (book1.isPresent()) {
            return book1.get();
        } else {
            throw new Exception("No Book found");
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
