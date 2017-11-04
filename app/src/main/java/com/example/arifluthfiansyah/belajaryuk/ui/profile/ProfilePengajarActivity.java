package com.example.arifluthfiansyah.belajaryuk.ui.profile;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.arifluthfiansyah.belajaryuk.R;

public class ProfilePengajarActivity extends AppCompatActivity {

    private static final String TAG = ProfilePengajarActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_pengajar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //TODO Selesaikan profile pengajar activity untuk show profile pengajar
}
