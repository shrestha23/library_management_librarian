package com.aurghyadip.libararymanagementlibrarian;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

//TODO: Add data handling and Barcode Scanner

public class ScanFragment extends Fragment {

    Button scanBooks;
    TextInputEditText isbn;

    AwesomeValidation awesomeValidation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Search for Books");

        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);
        scanBooks = rootView.findViewById(R.id.search_books);
        isbn = rootView.findViewById(R.id.book_search_field);

        //Awesome validation block
        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        awesomeValidation.addValidation(isbn, "^(97(8|9))?\\d{9}(\\d|X)$", getString(R.string.isbn_error));


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        scanBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (awesomeValidation.validate()) {
                    Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                    intent.putExtra("isbn", isbn.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}
