package com.scanbuy.webapp.Controller;

import com.scanbuy.webapp.DataAccessLayer.BookRepository;
import com.scanbuy.webapp.DataAccessLayer.DataRepository;
import com.scanbuy.webapp.Models.Book;
import com.scanbuy.webapp.Service.BookServices;
import org.apache.catalina.users.SparseUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("v1/books")
public class BookController {

//    DataRepository repo = new DataRepository();
    @Autowired
    BookServices repo;
    @PostMapping("/addbook")
    public ResponseEntity AddBook(@RequestBody Book book) {

        try {
            repo.addBook(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/getbook/{isbn}")
    public ResponseEntity GetBook(@PathVariable("isbn") Long isbn){
        try {
           Book book = repo.getBook(isbn);
           return new ResponseEntity<>(book, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @DeleteMapping("/deletebook/{isbn}")
    public ResponseEntity DeleteBook(@PathVariable("isbn") Long isbn){
        try {
            repo.deleteBook(isbn);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @PutMapping("/updatebook/{isbn}")
    public ResponseEntity UpdateBook(@PathVariable("isbn") Long isbn,@RequestBody Book book){
        try {
            repo.updateBook(isbn,book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/getBooks")
    public ResponseEntity GetBooks(){
        try {
            List<Book> books =  repo.getAllBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
