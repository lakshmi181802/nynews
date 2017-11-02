package com.example.my.news.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.my.news.R;
import com.example.my.news.fragments.BookDetailsFragment;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int index = 0;
        if (null != getIntent()) {
            index = getIntent().getIntExtra("index", 0);
            Log.d(this.getClass().getSimpleName(), "BookDetailsActivity index = " + index);
        }

        Bundle args = new Bundle();
        args.putInt("index", index);
        BookDetailsFragment frag = new BookDetailsFragment();
        frag.setArguments(args);

        addFragment(frag);

        Log.d(this.getClass().getSimpleName(), "BookDetailsActivity added index = " + index);
    }

    /**
     * Add a fragment to the activity container.
     *
     * @param fragment
     */
    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
