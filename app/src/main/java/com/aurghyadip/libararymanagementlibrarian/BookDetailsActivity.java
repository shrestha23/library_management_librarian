package com.aurghyadip.libararymanagementlibrarian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//TODO: Add dynamic data searching from fragment.


public class BookDetailsActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRef;
    ListView listView;

    TextView authorView;
    TextView titleView;
    TextView descriptionView;

    public String isbn = "9780091906351";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        authorView = findViewById(R.id.book_author);
        titleView = findViewById(R.id.book_title);
        descriptionView = findViewById(R.id.book_description);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Books");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title = (String) dataSnapshot.child(isbn).child("title").getValue();
                String description = (String) dataSnapshot.child(isbn).child("description").getValue();
                String author = (String) dataSnapshot.child(isbn).child("author").getValue();

                titleView.setText(title);
                authorView.setText(author);
                descriptionView.setText(description);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
