package com.scanbuy.webapp.Models;

import jakarta.persistence.*;

@Entity
@Table
public class Book {
    @Id
    private Long  isbn;

    private String title;

    private String author;

    private int num_pages;

    private String notes;

    private boolean isread;

    private float rating;

    private String image_url;

    public Book() {

    }

    public Book(Long isbn, String title, String author, int num_pages, String notes, boolean read, float rating, String image_url) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.num_pages = num_pages;
        this.notes = notes;
        this.isread = read;
        this.rating = rating;
        this.image_url = image_url;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPages() {
        return num_pages;
    }

    public void setNumPages(int num_pages) {
        this.num_pages = num_pages;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isRead() {
        return isread;
    }

    public void setRead(boolean read) {
        this.isread = read;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }
}
