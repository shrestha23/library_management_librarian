package com.aurghyadip.libararymanagementlibrarian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BookDetailsActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference mRef;

    String isbn;

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

        isbn = getIntent().getStringExtra("isbn");

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Books");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //TODO: Solve the NPE, remove hack
                if (dataSnapshot.hasChild(isbn)) {
                    // Dirty hack for solving the NPE Temporarily
                    // Map the values to a class
                    String title = dataSnapshot.child(isbn).child("title").getValue(String.class);
                    String description = dataSnapshot.child(isbn).child("description").getValue(String.class);
                    String author = dataSnapshot.child(isbn).child("author").getValue(String.class);
                    Long copies = dataSnapshot.child(isbn).child("copies").getValue(Long.class);

                    titleView.setText(title);
                    authorView.setText(author);
                    descriptionView.setText(description);
                    copiesView.setText(String.valueOf(copies));
                } else {
                    LinearLayout hasBookDetails = findViewById(R.id.has_book_details_layout);
                    LinearLayout doesNotHaveBookDetails = findViewById(R.id.does_not_have_book_details);

                    hasBookDetails.setVisibility(View.GONE);
                    doesNotHaveBookDetails.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Book failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(BookDetailsActivity.this, "Failed to load book.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        });
    }
}
