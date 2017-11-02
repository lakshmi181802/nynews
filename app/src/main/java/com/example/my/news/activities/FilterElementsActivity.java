package com.example.my.news.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.R;

import java.util.ArrayList;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class FilterElementsActivity extends AppCompatActivity {

    //TODO: the number filter elements would ideally be dynamic...temporatily for the TEST purspose, taking them this way
    private TextView filter1;
    private TextView filter2;
    private TextView filter3;
    private TextView filter4;
    private Button filterDone;
    private Button filterClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_elements);
        initFilters();
    }

    private void initFilters() {
        try {
            filter1 = (TextView) findViewById(R.id.filter1);
            filter1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelected((TextView) view);
                }
            });

            filter2 = (TextView) findViewById(R.id.filter2);
            filter2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelected((TextView) view);
                }
            });

            filter3 = (TextView) findViewById(R.id.filter3);
            filter3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelected((TextView) view);
                }
            });

            filter4 = (TextView) findViewById(R.id.filter4);
            filter4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelected((TextView) view);
                }
            });

            displayAppliedFilters();

            filterDone = (Button) findViewById(R.id.filterDone);
            filterDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent output = new Intent();
                    setResult(RESULT_OK, output);
                    finish();
                }
            });

            filterClear = (Button) findViewById(R.id.filterClear);
            filterClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NyTimesApplication.getInstance().getContent().getContentFilterElements().clear();

                    setFilterStyle(filter1, false);
                    setFilterStyle(filter2, false);
                    setFilterStyle(filter3, false);
                    setFilterStyle(filter4, false);
                }
            });

        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), "FilterElementsActivity. Error = " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void displayAppliedFilters() {
        if (NyTimesApplication.getInstance().getContent().isFilterApplied()) {
            for (String filter : NyTimesApplication.getInstance().getContent().getContentFilterElements()) {
                if (filter1.getText().toString().equalsIgnoreCase(filter)) {
                    setFilterStyle(filter1, true);
                } else if (filter2.getText().toString().equalsIgnoreCase(filter)) {
                    setFilterStyle(filter2, true);
                } else if (filter3.getText().toString().equalsIgnoreCase(filter)) {
                    setFilterStyle(filter3, true);
                } else if (filter4.getText().toString().equalsIgnoreCase(filter)) {
                    setFilterStyle(filter4, true);
                }
            }
        }
    }

    private void setSelected(TextView tv) {

        addToFilterElements(tv.getText().toString().toLowerCase());

        boolean isSelectedAfterClick = !tv.isSelected();
        tv.setSelected(isSelectedAfterClick);

        Log.d(this.getClass().getSimpleName(), "FilterElementsActivity. " + tv.getText() + ". selected = " + isSelectedAfterClick);

        setFilterStyle(tv, true);
    }

    private void setFilterStyle(TextView tv, boolean selected) {
        if (selected) {
            tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
            //tv.setTextColor(getResources().getColor(R.color.color_default));
            tv.setBackground(getResources().getDrawable(R.drawable.round_corner_selected));
        } else {
            tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
            //tv.setTextColor(getResources().getColor(R.color.colorAccent));
            tv.setBackground(getResources().getDrawable(R.drawable.round_corner));
        }
        tv.setPadding(40, 40, 40, 40);
    }

    private void addToFilterElements(String element) {
        ArrayList<String> filters = NyTimesApplication.getInstance().getContent().getContentFilterElements();
        if (!filters.contains(element)) {
            filters.add(element);
        }

        Log.d(this.getClass().getSimpleName(), "FilterElementsActivity. Filters Size = " + filters.size());
    }
}
