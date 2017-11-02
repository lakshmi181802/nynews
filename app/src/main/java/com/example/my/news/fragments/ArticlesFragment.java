package com.example.my.news.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.R;
import com.example.my.news.activities.FilterElementsActivity;
import com.example.my.news.adapters.ArticlesAdapter;
import com.example.my.news.manager.ContentUpdateEvent;
import com.example.my.news.model.Article;
import com.example.my.news.model.ArticleDocument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ArticlesFragment extends Fragment {

    public static int FILTER_ELEMENTS_REQUEST_CODE = 1001;
    private FloatingActionButton search;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Context context;

    //TODO: show spinner while fetching data
    private RelativeLayout spinnerLayout;
    private TextView spinnerText;

    public ArticlesFragment() {
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

        context = this.getContext();
        spinnerLayout = (RelativeLayout) view.findViewById(R.id.spinner_layout);
        spinnerText = (TextView) view.findViewById(R.id.spinner_text);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        search = (FloatingActionButton) view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FilterElementsActivity.class);
                startActivityForResult(intent, FILTER_ELEMENTS_REQUEST_CODE);
            }
        });

        return view;
    }

    private void setAdapter() {

        List<Article> articles = new ArrayList<Article>();
        if (null != NyTimesApplication.getInstance().getContent().getContentArticles()
                && null != NyTimesApplication.getInstance().getContent().getContentArticles().getResults()
                && NyTimesApplication.getInstance().getContent().getContentArticles().getResults().length > 0) {
            articles = new ArrayList<>(Arrays.asList(NyTimesApplication.getInstance().getContent().getContentArticles().getResults()));

            Log.d(this.getClass().getSimpleName(), "setAdapter Articles"
                    + ". given = " + NyTimesApplication.getInstance().getContent().getContentArticles().getNum_results()
                    + ". results = " + NyTimesApplication.getInstance().getContent().getContentArticles().getResults().length);
        }

        Log.d(this.getClass().getSimpleName(), "setAdapter. isFilterApplied = " + NyTimesApplication.getInstance().getContent().isFilterApplied());

        boolean isFilterApplied = false;
        List<ArticleDocument> articleDocs = null;
        if (NyTimesApplication.getInstance().getContent().isFilterApplied()) {

            articleDocs = new ArrayList<ArticleDocument>();

            if (null != NyTimesApplication.getInstance().getContent().getContentArticleSearchResults()
                    && null != NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse()
                    && null != NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse().getDocs()
                    && NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse().getDocs().length > 0) {

                articleDocs = new ArrayList<>(Arrays.asList(NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse().getDocs()));

                Log.d(this.getClass().getSimpleName(), "setAdapter ArticleDocs"
                        + ". results = " + NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse().getDocs().length);

                isFilterApplied = true;
            }
        }

        adapter = new ArticlesAdapter(this.getContext(), articles, articleDocs);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (isFilterApplied) {
            Toast.makeText(context, "Filtered successfully!", Toast.LENGTH_LONG).show();
        }
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
        if (event.getMessage().equalsIgnoreCase("filter_applied")) {

            //https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=5c4d90ba65dc4965b048d20873258f06
            setAdapter();
        } else {
            setAdapter();
        }
    }

}
