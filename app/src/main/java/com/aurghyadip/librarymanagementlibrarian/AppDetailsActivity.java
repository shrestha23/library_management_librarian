package com.aurghyadip.librarymanagementlibrarian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AppDetailsActivity extends AppCompatActivity {
    private TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_details);

        version = findViewById(R.id.about_app_version);
        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        String versionString = versionName + "." + String.valueOf(versionCode);
        version.append(versionString);

    }
}
