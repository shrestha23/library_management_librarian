package com.aurghyadip.librarymanagementlibrarian;

import android.app.AlertDialog;
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

public class AddBookFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    AwesomeValidation awesomeValidation;

    TextInputEditText isbn;
    TextInputEditText bookTitle;
    TextInputEditText bookAuthor;
    TextInputEditText bookDescription;
    TextInputEditText bookCopies;

    Button addBookBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_book, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        isbn = rootView.findViewById(R.id.add_isbn);
        bookTitle = rootView.findViewById(R.id.add_title);
        bookAuthor = rootView.findViewById(R.id.add_author);
        bookDescription = rootView.findViewById(R.id.add_description);
        bookCopies = rootView.findViewById(R.id.add_copies);
        addBookBtn = rootView.findViewById(R.id.add_book_btn);


        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        databaseReference = firebaseDatabase.getReference("Books");

        // Validating the data through AwesomeValidation Library, same as ScanFragment
        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        //TODO: Extract string resource
        awesomeValidation.addValidation(isbn, "^(97(8|9))?\\d{9}(\\d|X)$", "ISBN Error");
        awesomeValidation.addValidation(bookTitle, RegexTemplate.NOT_EMPTY, "Title should not be empty");
        awesomeValidation.addValidation(bookAuthor, RegexTemplate.NOT_EMPTY, "Author should not be empty");
        awesomeValidation.addValidation(bookDescription, RegexTemplate.NOT_EMPTY, "Description should not be empty");
        awesomeValidation.addValidation(bookCopies, RegexTemplate.NOT_EMPTY, "Copies should not be empty, enter at least 1");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(!dataSnapshot.hasChild(isbn.getText().toString())) {
                                Book newBook = new Book(
                                        bookTitle.getText().toString(),
                                        bookAuthor.getText().toString(),
                                        bookDescription.getText().toString(),
                                        bookCopies.getText().toString()
                                );
                                databaseReference.child(isbn.getText().toString()).setValue(newBook);
                                //TODO: Add navigation back to show that the data has been entered.
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Please check ISBN number and enter it again")
                                        .setTitle("ISBN Exists in Database");

                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("AddBookFragment:", "Pushing to database failed", databaseError.toException());
                        }
                    });
                }
            }
        });
    }
}
