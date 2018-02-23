package com.aurghyadip.libararymanagementlibrarian;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditBookFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AwesomeValidation awesomeValidationIsbn;
    AwesomeValidation awesomeValidationEditBook;

    String isbnNumber;

    Button searchBtn;
    Button editBookDetailsBtn;

    TextInputEditText isbn;
    TextInputEditText bookTitle;
    TextInputEditText bookAuthor;
    TextInputEditText bookDescription;
    TextInputEditText bookCopies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_book, container, false);

        awesomeValidationIsbn = new AwesomeValidation(ValidationStyle.COLORATION);
        awesomeValidationEditBook = new AwesomeValidation(ValidationStyle.COLORATION);

        firebaseDatabase = FirebaseDatabase.getInstance();

        searchBtn = rootView.findViewById(R.id.edit_search_btn);
        isbn = rootView.findViewById(R.id.edit_search_isbn);
        bookTitle = rootView.findViewById(R.id.edit_book_name);
        bookAuthor = rootView.findViewById(R.id.edit_book_author);
        bookDescription = rootView.findViewById(R.id.edit_book_description);
        bookCopies = rootView.findViewById(R.id.edit_book_copies);
        editBookDetailsBtn = rootView.findViewById(R.id.edit_book_details);

        // Validation for ISBN
        awesomeValidationIsbn.addValidation(isbn, "^(97(8|9))?\\d{9}(\\d|X)$", getString(R.string.isbn_error));

        // Validation for Book Details
        //TODO: Extract string resource from here
        awesomeValidationEditBook.addValidation(bookTitle, RegexTemplate.NOT_EMPTY, "Title should not be empty");
        awesomeValidationEditBook.addValidation(bookAuthor, RegexTemplate.NOT_EMPTY, "Author should not be empty");
        awesomeValidationEditBook.addValidation(bookDescription, RegexTemplate.NOT_EMPTY, "Description should not be empty");
        awesomeValidationEditBook.addValidation(bookCopies, RegexTemplate.NOT_EMPTY, "Copies should not be empty, enter at least 1");

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = firebaseDatabase.getReference("Books");

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidationIsbn.validate()) {
                    isbnNumber = isbn.getText().toString();
                    // TODO: Add dynamic data loading from Google Books API
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(isbnNumber)) {
                                // Dirty hack for solving the NPE Temporarily
                                // Map the values to a class
                                String title = dataSnapshot.child(isbnNumber).child("title").getValue(String.class);
                                String description = dataSnapshot.child(isbnNumber).child("description").getValue(String.class);
                                String author = dataSnapshot.child(isbnNumber).child("author").getValue(String.class);
                                Long copies = dataSnapshot.child(isbnNumber).child("copies").getValue(Long.class);

                                bookTitle.setText(title);
                                bookAuthor.setText(author);
                                bookDescription.setText(description);
                                bookCopies.setText(String.valueOf(copies));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("No books found with that ISBN in Database")
                                        .setTitle("Book Not Found");

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        editBookDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidationEditBook.validate()) {
                    databaseReference.child(isbnNumber).child("title").setValue(bookTitle.getText().toString());
                    databaseReference.child(isbnNumber).child("author").setValue(bookAuthor.getText().toString());
                    databaseReference.child(isbnNumber).child("description").setValue(bookDescription.getText().toString());
                    databaseReference.child(isbnNumber).child("copies").setValue(bookCopies.getText().toString());
                }
                // TODO: Destroy the fragment here and go back to previous fragment
            }
        });

    }
}
