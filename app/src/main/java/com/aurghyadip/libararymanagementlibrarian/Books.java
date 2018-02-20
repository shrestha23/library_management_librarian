package com.aurghyadip.libararymanagementlibrarian;

/**
 * Created by Aurghya on 20-02-2018.
 */

public class Books {
    private String author;
    private String description;
    private String title;
    private int copies;

    public Books() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
