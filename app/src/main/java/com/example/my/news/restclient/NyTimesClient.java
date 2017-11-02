package com.example.my.news.restclient;

import android.content.Context;
import android.util.Log;

import com.example.my.news.NyTimesApplication;
import com.example.my.news.model.ContentArticleSearchResults;
import com.example.my.news.model.ContentArticles;
import com.example.my.news.model.ContentBooks;
import com.example.my.news.restclient.interceptors.RequestControlInterceptor;
import com.example.my.news.restclient.interceptors.RewriteResponseCacheControlHeaderInterceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class NyTimesClient {

    private static final long TIMEOUT_READ_SECONDS = 100;
    private static final long TIMEOUT_WRITE_SECONDS = 100;
    private static final long TIMEOUT_CONNECT_SECONDS = 100;

    private static final String CACHE_DIR = "http-cache";
    private static final int CACHE_SIZE = 10 * 1024 * 1024; //10MB
    private static final int CACHE_DURATION = (int) TimeUnit.MINUTES.toSeconds(15);

    private static final String BASE_ADDRESS = "https://api.nytimes.com";
    private static final String TOP_STORIES_API = "/svc/topstories/v2/home.json?api-key=5c4d90ba65dc4965b048d20873258f06";
    private static final String ARTICLES_SEARCH_API = "/svc/search/v2/articlesearch.json?api-key=5c4d90ba65dc4965b048d20873258f06";
    private static final String BOOKS_BEST_SELLERS_API = "/svc/books/v3/lists/best-sellers/history.json?api-key=5c4d90ba65dc4965b048d20873258f06";
    public static final String ARTICLE_SEARCH_DOC_IMAGE_BASE_ADDRESS = "https://static01.nyt.com/";

    private static NyTimesClient instance;
    private OkHttpClient client;
    private final NyTimesService service;

    private RewriteResponseCacheControlHeaderInterceptor rewriteResponseCacheControlHeaderInterceptor;
    private RequestControlInterceptor requestControlInterceptor;

    public NyTimesClient() {
        service = createService();
        serviceCustomCache = createServiceWithCustomCache();
    }

    public static NyTimesClient getInstance() {
        if (instance == null) {
            instance = new NyTimesClient();
        }
        return instance;
    }

    private NyTimesService createService() {
        requestControlInterceptor = new RequestControlInterceptor();

        final Context context = NyTimesApplication.getInstance();
        final Cache cache = new Cache(new File(context.getCacheDir(), CACHE_DIR), CACHE_SIZE);
        client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(requestControlInterceptor)
                .build();

        Log.e(this.getClass().getSimpleName(), "createService. BASE_ADDRESS = " + BASE_ADDRESS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(NyTimesService.class);
    }

    public void getTopStories(final NyTimesCallBack<ContentArticles> callback) {
        try {
            Call<ContentArticles> call = service.getTopStories(TOP_STORIES_API);
            call.enqueue(new retrofit2.Callback<ContentArticles>() {
                @Override
                public void onResponse(Call<ContentArticles> call, retrofit2.Response<ContentArticles> response) {
                    Log.d(this.getClass().getSimpleName(), "getTopStories. onResponse ");
                    callback.onResponse(call, response);
                }

                @Override
                public void onFailure(Call<ContentArticles> call, Throwable t) {
                    Log.e(this.getClass().getSimpleName(), "getTopStories. onFailure " + t.getMessage());
                    t.printStackTrace();
                    callback.onFailure(call, t);
                }
            });

        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void getBestSellers(final NyTimesCallBack<ContentBooks> callback) {
        try {
            Call<ContentBooks> call = service.getBestSellers(BOOKS_BEST_SELLERS_API);
            call.enqueue(new retrofit2.Callback<ContentBooks>() {
                @Override
                public void onResponse(Call<ContentBooks> call, retrofit2.Response<ContentBooks> response) {
                    Log.d(this.getClass().getSimpleName(), "getBestSellers. onResponse ");
                    callback.onResponse(call, response);
                }

                @Override
                public void onFailure(Call<ContentBooks> call, Throwable t) {
                    Log.e(this.getClass().getSimpleName(), "getBestSellers. onFailure " + t.getMessage());
                    t.printStackTrace();
                    callback.onFailure(call, t);
                }
            });

        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void getArticlesSearch(final NyTimesCallBack<ContentArticleSearchResults> callback, ArrayList<String> filters) {
        try {
            String url = ARTICLES_SEARCH_API;
            for (String filter : filters) {
                url = url + "&q=" + filter;
            }
            Call<ContentArticleSearchResults> call = service.getArticlesSearch(url);
            call.enqueue(new retrofit2.Callback<ContentArticleSearchResults>() {
                @Override
                public void onResponse(Call<ContentArticleSearchResults> call, retrofit2.Response<ContentArticleSearchResults> response) {
                    Log.d(this.getClass().getSimpleName(), "getArticlesSearch. onResponse ");
                    callback.onResponse(call, response);
                }

                @Override
                public void onFailure(Call<ContentArticleSearchResults> call, Throwable t) {
                    Log.e(this.getClass().getSimpleName(), "getTopStories. onFailure " + t.getMessage());
                    t.printStackTrace();
                    callback.onFailure(call, t);
                }
            });

        } catch (Exception ex) {
            Log.e(this.getClass().getSimpleName(), ex.getMessage());
            ex.printStackTrace();
        }
    }


    public void forceNetwork() {
        requestControlInterceptor.forceNetwork();
    }

    public void forceCache() {
        requestControlInterceptor.forceCache();
    }

    @Deprecated
    private final NyTimesService serviceCustomCache;

    @Deprecated
    private NyTimesService createServiceWithCustomCache() {
        requestControlInterceptor = new RequestControlInterceptor();
        rewriteResponseCacheControlHeaderInterceptor = new RewriteResponseCacheControlHeaderInterceptor(CACHE_DURATION);

        final Context context = NyTimesApplication.getInstance();
        final Cache cache = new Cache(new File(context.getCacheDir(), CACHE_DIR), CACHE_SIZE);
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
                .cache(cache)
                .addInterceptor(requestControlInterceptor)
                .addNetworkInterceptor(rewriteResponseCacheControlHeaderInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(NyTimesService.class);
    }

    @Deprecated
    protected NyTimesService getServiceWithCustomCache() {
        return serviceCustomCache;
    }

    @Deprecated
    protected int getCacheDuration() {
        if (rewriteResponseCacheControlHeaderInterceptor != null) {
            return rewriteResponseCacheControlHeaderInterceptor.getCacheDuration();
        } else {
            return 0;
        }
    }

    @Deprecated
    protected void setCacheDuration(int duration) {
        if (rewriteResponseCacheControlHeaderInterceptor != null) {
            rewriteResponseCacheControlHeaderInterceptor.setCacheDuration(duration);
        }
    }
}
