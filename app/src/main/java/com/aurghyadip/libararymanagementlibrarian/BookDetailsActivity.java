package com.aurghyadip.libararymanagementlibrarian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BookDetailsActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRef;

    Toolbar toolbar;

    TextView authorView;
    TextView titleView;
    TextView descriptionView;
    TextView copiesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        toolbar = findViewById(R.id.toolbar_book_details);
        setSupportActionBar(toolbar);

        authorView = findViewById(R.id.book_author);
        titleView = findViewById(R.id.book_title);
        descriptionView = findViewById(R.id.book_description);
        copiesView = findViewById(R.id.book_copies);

        String isbn = getIntent().getStringExtra("isbn");

        database = FirebaseDatabase.getInstance();
        //TODO: Check if the ISBN exists in the database.
        mRef = database.getReference("Books" + "/" + isbn);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Books book = dataSnapshot.getValue(Books.class);
                Log.d("TAG", "onDataChange: " + book.getCopies());

                titleView.setText(book.title);
                authorView.setText(book.author);
                descriptionView.setText(book.description);
                copiesView.setText(book.getCopies());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
