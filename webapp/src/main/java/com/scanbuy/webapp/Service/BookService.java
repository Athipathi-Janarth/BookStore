package com.scanbuy.webapp.Service;

import com.scanbuy.webapp.Models.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
        public void addBook(Book book) throws Exception;
        public Book getBook(Long isbn)  throws Exception;
        public List<Book> getAllBooks()  throws Exception;
        public Book updateBook(Long isbn, Book updatedBook)  throws Exception;
        void deleteBook(Long isbn)  throws Exception;
}
