package com.aurghyadip.libararymanagementlibrarian;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aurghya on 20-02-2018.
 */

//TODO: Add data handling and Barcode Scanner

public class ScanFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Search for Books");
        return inflater.inflate(R.layout.fragment_scan, container, false);
    }
}
