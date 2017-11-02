package com.example.my.news.manager;

import android.support.annotation.IntDef;
import android.util.Log;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.model.ContentArticleSearchResults;
import com.example.my.news.model.ContentArticles;
import com.example.my.news.model.ContentBooks;
import com.example.my.news.restclient.NyTimesCallBack;
import com.example.my.news.restclient.NyTimesClient;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ContentManager {

    @IntDef({EXECUTION_MODE_FIRST_LAUNCH, EXECUTION_MODE_NORMAL, EXECUTION_MODE_SEARCH_ARTICLES})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExecutionMode {
    }

    public static final int EXECUTION_MODE_FIRST_LAUNCH = 0;
    public static final int EXECUTION_MODE_NORMAL = 1;
    public static final int EXECUTION_MODE_SEARCH_ARTICLES = 3;

    private static ContentManager instance;

    private NyTimesClient nyTimesClient;
    private boolean executionInProgress;

    @ExecutionMode
    private int executionMode;
    private Callback callback;
    private boolean contentFromCache;

    private ContentManager() {
        nyTimesClient = NyTimesClient.getInstance();
        executionInProgress = false;
    }

    public static ContentManager getInstance() {
        if (instance == null) {
            instance = new ContentManager();
        }
        return instance;
    }

    /**
     * Attempts to execute the data refresh process if it isn't running
     * already.
     *
     * @param executionMode It can be {@link #EXECUTION_MODE_FIRST_LAUNCH}
     *                      or {@link #EXECUTION_MODE_NORMAL}
     * @param callback      Interface use to notify the result of the
     *                      execution.
     */
    public void execute(@ExecutionMode int executionMode, Callback callback) {
        if (executionInProgress) {
            return;
        }
        executionInProgress = true;

        this.executionMode = executionMode;
        this.callback = callback;

        Log.e(this.getClass().getSimpleName(), "execute mode = " + executionMode);

        if (executionMode == EXECUTION_MODE_SEARCH_ARTICLES) {
            executeArticlesSearch();
        } else {
            executeServiceCalls();
        }
    }


    private void executeServiceCalls() {
        executeTopStories();
    }

    private void executeTopStories() {

        nyTimesClient.getTopStories(new NyTimesCallBack<ContentArticles>() {
            @Override
            public void onResponseSuccess(ContentArticles articles, boolean fromCache) {
                Log.d(this.getClass().getSimpleName(), "getTopStories onResponseSuccess ");
                contentFromCache = fromCache;
                NyTimesApplication.getInstance().getContent().setContentArticles(articles);

                executeBestSellers();
            }

            @Override
            public void onResponseError(int errorCode) {
                Log.e(this.getClass().getSimpleName(), "getTopStories onResponseError ");
                onExecutionFinished(false);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(this.getClass().getSimpleName(), "getTopStories. onFailure " + e.getMessage());
                e.printStackTrace();
                onExecutionFinished(false);
            }
        });
    }

    private void executeBestSellers() {

        nyTimesClient.getBestSellers(new NyTimesCallBack<ContentBooks>() {
            @Override
            public void onResponseSuccess(ContentBooks books, boolean fromCache) {
                Log.d(this.getClass().getSimpleName(), "getBestSellers onResponseSuccess ");
                contentFromCache = fromCache;
                NyTimesApplication.getInstance().getContent().setContentBooks(books);

                onExecutionFinished(true);
            }

            @Override
            public void onResponseError(int errorCode) {
                Log.e(this.getClass().getSimpleName(), "getBestSellers. onResponseError errorCode = " + errorCode);
                onExecutionFinished(false);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(this.getClass().getSimpleName(), "getBestSellers. onFailure " + e.getMessage());
                e.printStackTrace();
                onExecutionFinished(false);
            }
        });
    }

    private void executeArticlesSearch() {

        nyTimesClient.getArticlesSearch(new NyTimesCallBack<ContentArticleSearchResults>() {
            @Override
            public void onResponseSuccess(ContentArticleSearchResults articleSearchResults, boolean fromCache) {

                Log.d(this.getClass().getSimpleName(), "getArticlesSearch onResponseSuccess ");
                contentFromCache = fromCache;
                NyTimesApplication.getInstance().getContent().setContentArticleSearchResults(articleSearchResults);

                onExecutionFinished(true);
            }

            @Override
            public void onResponseError(int errorCode) {
                Log.e(this.getClass().getSimpleName(), "getArticlesSearch onResponseError ");
                onExecutionFinished(false);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(this.getClass().getSimpleName(), "getTopStories. onFailure " + e.getMessage());
                e.printStackTrace();
                onExecutionFinished(false);
            }
        }, NyTimesApplication.getInstance().getContent().getContentFilterElements());
    }

    private void onExecutionFinished(boolean successful) {
        Log.d(this.getClass().getSimpleName(), "onExecutionFinished. successful " + successful);
        executionInProgress = false;
        if (successful) {
            callback.onExecutionSuccessful();
        } else {
            callback.onExecutionFailure();
        }
    }

    /**
     * Notifies when data refresh process execution finishes.
     */
    public interface Callback {

        /**
         * Called when data refresh process execution is successful.
         */
        void onExecutionSuccessful();

        /**
         * Called when data refresh process execution failed.
         */
        void onExecutionFailure();
    }
}
