package com.example.my.news.restclient;

import com.example.my.news.model.ContentArticleSearchResults;
import com.example.my.news.model.ContentArticles;
import com.example.my.news.model.ContentBooks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by ELURV001 on 11/1/17.
 */

public interface NyTimesService {

    @GET
    Call<ContentArticles> getTopStories(@Url String path);

    @GET
    Call<ContentBooks> getBestSellers(@Url String path);

    @GET
    Call<ContentArticleSearchResults> getArticlesSearch(@Url String path);
}
