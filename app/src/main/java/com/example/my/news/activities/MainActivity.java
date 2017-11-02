package com.example.my.news.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.R;
import com.example.my.news.fragments.MainFragment;
import com.example.my.news.manager.ContentManager;
import com.example.my.news.manager.ContentManagerService;
import com.example.my.news.manager.ContentUpdateEvent;
import com.example.my.news.model.Article;
import com.example.my.news.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by ELURV001 on 11/1/17.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new MainFragment());
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


    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(this).registerReceiver(contentManagerReceiver, new IntentFilter(ContentManagerService.ACTION_DATA_REFRESH_EXECUTED));

        List<Article> articles = new ArrayList<Article>();
        List<Book> books = new ArrayList<Book>();

        if (null != NyTimesApplication.getInstance().getContent().getContentArticles()
                && null != NyTimesApplication.getInstance().getContent().getContentArticles().getResults()
                && NyTimesApplication.getInstance().getContent().getContentArticles().getResults().length > 0) {
            articles = new ArrayList<>(Arrays.asList(NyTimesApplication.getInstance().getContent().getContentArticles().getResults()));

            Log.d(this.getClass().getSimpleName(), "onStart. articles"
                    + ". given = " + NyTimesApplication.getInstance().getContent().getContentArticles().getNum_results()
                    + ". results = " + NyTimesApplication.getInstance().getContent().getContentArticles().getResults().length);
        }
        if (null != NyTimesApplication.getInstance().getContent().getContentBooks()
                && null != NyTimesApplication.getInstance().getContent().getContentBooks().getResults()
                && NyTimesApplication.getInstance().getContent().getContentBooks().getResults().length > 0) {
            books = new ArrayList<>(Arrays.asList(NyTimesApplication.getInstance().getContent().getContentBooks().getResults()));

            Log.d(this.getClass().getSimpleName(), "onStart. books"
                    + ". given = " + NyTimesApplication.getInstance().getContent().getContentArticles().getNum_results()
                    + ". results = " + NyTimesApplication.getInstance().getContent().getContentArticles().getResults().length);
        }

        if (articles.size() == 0 || books.size() == 0) {

            Intent intent = new Intent(this, ContentManagerService.class);
            intent.putExtra(ContentManagerService.EXTRA_EXECUTION_MODE, ContentManager.EXECUTION_MODE_FIRST_LAUNCH);
            startService(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(contentManagerReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(this.getClass().getSimpleName(), "FILTER_ELEMENTS. requestCode = " + requestCode);
        Log.d(this.getClass().getSimpleName(), "FILTER_ELEMENTS. resultCode = " + resultCode);
        Log.d(this.getClass().getSimpleName(), "FILTER_ELEMENTS. isFilterApplied = " + NyTimesApplication.getInstance().getContent().isFilterApplied());

        //TODO: check the conditions
        //if (requestCode == ArticlesFragment.FILTER_ELEMENTS_REQUEST_CODE) {
            //if (resultCode == RESULT_OK) {

                Intent intent = new Intent(this, ContentManagerService.class);
                intent.putExtra(ContentManagerService.EXTRA_EXECUTION_MODE, ContentManager.EXECUTION_MODE_SEARCH_ARTICLES);
                startService(intent);
            //}
        //}
    }

    private final BroadcastReceiver contentManagerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d(this.getClass().getSimpleName(), "contentManagerReceiver. result = " + intent.getBooleanExtra(ContentManagerService.EXTRA_EXECUTION_RESULT, false));

            int executionMode = intent.getIntExtra(ContentManagerService.EXTRA_EXECUTION_MODE, 0);
            Log.d(this.getClass().getSimpleName(), "contentManagerReceiver. EXTRA_EXECUTION_MODE = " + executionMode);

            if (!intent.getBooleanExtra(ContentManagerService.EXTRA_EXECUTION_RESULT, false)) {
                Toast.makeText(context, "Content call failed, kill the app and try again.", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(context, "Content call successful!", Toast.LENGTH_LONG).show();

                Log.d(this.getClass().getSimpleName(), "Content Articles"
                        + ". given = " + NyTimesApplication.getInstance().getContent().getContentArticles().getNum_results()
                        + ". results = " + NyTimesApplication.getInstance().getContent().getContentArticles().getResults().length);

                Log.d(this.getClass().getSimpleName(), "Content Books"
                        + ". given = " + NyTimesApplication.getInstance().getContent().getContentArticles().getNum_results()
                        + ". results = " + NyTimesApplication.getInstance().getContent().getContentBooks().getResults().length);

                if (null != NyTimesApplication.getInstance().getContent().getContentArticleSearchResults()
                        && null != NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse()
                        && null != NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse().getDocs()
                        && NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse().getDocs().length > 0) {

                    Log.d(this.getClass().getSimpleName(), "Content Articles Documents"
                            + ". results = " + NyTimesApplication.getInstance().getContent().getContentArticleSearchResults().getResponse().getDocs().length);
                }

                if (executionMode == ContentManager.EXECUTION_MODE_SEARCH_ARTICLES) {
                    EventBus.getDefault().post(new ContentUpdateEvent("filter_applied"));
                } else {
                    EventBus.getDefault().post(new ContentUpdateEvent("updated"));
                }
            }
        }
    };
}
