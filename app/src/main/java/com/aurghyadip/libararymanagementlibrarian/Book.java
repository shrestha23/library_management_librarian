package com.aurghyadip.libararymanagementlibrarian;


public class Book {
    public String title;
    public String author;
    public String description;
    public String copies;

    public Book() {
    }

    public Book(String title, String author, String description, String copies) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getCopies() {
        return copies;
    }
}
