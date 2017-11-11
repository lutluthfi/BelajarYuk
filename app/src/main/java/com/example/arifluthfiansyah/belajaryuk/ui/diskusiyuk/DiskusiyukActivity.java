package com.example.arifluthfiansyah.belajaryuk.ui.diskusiyuk;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import com.example.arifluthfiansyah.belajaryuk.R;

import butterknife.BindView;

/**
 * Created by Arif Luthfiansyah on 11/11/2017.
 */

public class DiskusiyukActivity extends AppCompatActivity {

    private static final String TAG = DiskusiyukActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.diskusiyuk_content)
    SwipeRefreshLayout mDiskusiyukRefreshLayout;

    @BindView(R.id.rv_diskusiyuk)
    RecyclerView mDiskusiyukRecyclerView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
