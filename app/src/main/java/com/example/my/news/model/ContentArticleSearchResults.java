package com.example.my.news.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ContentArticleSearchResults implements Serializable {

    private String status;
    private String copyright;

    @SerializedName("response")
    private ArticleResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public ArticleResponse getResponse() {
        return response;
    }

    public void setResponse(ArticleResponse response) {
        this.response = response;
    }
}
