package com.aurghyadip.librarymanagementlibrarian;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

//TODO: Move and implement support methods in Deposit Activity
//TODO: Remove ComingDuesFragment and AllDuesFragment
public class ComingDuesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coming_dues, container, false);
    }

    /* Support methods for deposit and calculate fine */

    // Get current date string from simple date format
    private String getCurrentDateString() {
        Date today = new Date();
        SimpleDateFormat formatDate = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Locale.UK);
        return formatDate.format(today.getTime());
    }

    // Calculate the difference between two days
    private int dateDiffCalculator(String rentDate) {
        long difference = 0;
        SimpleDateFormat formatDate = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Locale.UK);
        try {
            Date rent = formatDate.parse(rentDate);
            Date deposit = formatDate.parse(getCurrentDateString());
            long duration = deposit.getTime() - rent.getTime();
            difference = TimeUnit.MILLISECONDS.toDays(duration);
            if(difference < 0) {
                difference = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) difference;
    }

    // Calculate the fine based on a slab structure
    private int calculateFine(String date1) {
        int fine = 0;
        int diff = dateDiffCalculator(date1);

        if(diff < 5) {
            fine = 0;
        } else if(diff > 5 && diff < 10) {
            fine = diff * 2;
        } else if(diff > 10 && diff < 20) {
            fine = diff * 5;
        }
        else {
            fine = 100;
        }
        return fine;
    }
    /* ---------------------------------------------- */
}
