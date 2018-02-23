package com.aurghyadip.libararymanagementlibrarian;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class AddBookFragment extends Fragment {
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
                // TODO: Add database reference here and then push the values to database
                // TODO: Before pushing, check if the value already exists.
                awesomeValidation.validate();
            }
        });
    }
}
