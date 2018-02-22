package com.aurghyadip.libararymanagementlibrarian;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Books {

    public String author;
    public String description;
    public String title;
    private Long copies;

    public Books() {
    }

    public Books(String title, String author, String description, Long copies) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.copies = copies;
    }

    String getCopies() {
        return Long.toString(this.copies);
    }

    public void addNewBook(String isbn, String title, String author, String description, Long copies) {
        DatabaseReference databaseReference;
        Books book = new Books(title, author, description, copies);
        databaseReference = FirebaseDatabase.getInstance().getReference("Books");
        databaseReference.child(isbn).setValue(book);
    }
}
