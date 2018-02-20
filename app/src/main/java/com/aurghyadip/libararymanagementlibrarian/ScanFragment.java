package com.aurghyadip.libararymanagementlibrarian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Aurghya on 20-02-2018.
 */

//TODO: Add data handling and Barcode Scanner

public class ScanFragment extends Fragment {

    Button scanBooks;
    EditText isbn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Search for Books");

        View rootView = inflater.inflate(R.layout.fragment_scan, container, false);

        scanBooks = rootView.findViewById(R.id.search_books);
        isbn = rootView.findViewById(R.id.book_search_field);
        scanBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
                intent.putExtra("isbn", isbn.getText().toString());
                startActivity(intent);
            }
        });

        return rootView;
    }
}
