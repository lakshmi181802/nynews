package com.example.my.news.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.R;
import com.example.my.news.adapters.BooksAdapter;
import com.example.my.news.manager.ContentUpdateEvent;
import com.example.my.news.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class BooksFragment extends Fragment {

    private RelativeLayout spinnerLayout;
    private TextView spinnerText;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    public BooksFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        spinnerLayout = (RelativeLayout) view.findViewById(R.id.spinner_layout);
        spinnerText = (TextView) view.findViewById(R.id.spinner_text);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        return view;
    }

    private void setAdapter() {

        List<Book> books = new ArrayList<Book>();
        if (null != NyTimesApplication.getInstance().getContent().getContentBooks()
                && null != NyTimesApplication.getInstance().getContent().getContentBooks().getResults()
                && NyTimesApplication.getInstance().getContent().getContentBooks().getResults().length > 0) {
            books = new ArrayList<>(Arrays.asList(NyTimesApplication.getInstance().getContent().getContentBooks().getResults()));

            Log.d(this.getClass().getSimpleName(), "setAdapter books"
                    + ". given = " + NyTimesApplication.getInstance().getContent().getContentBooks().getNum_results()
                    + ". results = " + NyTimesApplication.getInstance().getContent().getContentBooks().getResults().length);
        }

        mAdapter = new BooksAdapter(this.getContext(), books);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

        setAdapter();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(ContentUpdateEvent event) {
        setAdapter();
    }
}
