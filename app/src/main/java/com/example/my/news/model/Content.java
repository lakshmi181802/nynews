package com.example.my.news.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class Content implements Serializable {

    private ContentArticles contentArticles;
    private ContentBooks contentBooks;
    private ContentArticleSearchResults contentArticleSearchResults;
    private ArrayList<String> contentFilterElements;

    public ContentArticles getContentArticles() {
        return contentArticles;
    }

    public void setContentArticles(ContentArticles contentArticles) {
        this.contentArticles = contentArticles;
    }

    public ContentBooks getContentBooks() {
        return contentBooks;
    }

    public void setContentBooks(ContentBooks contentBooks) {
        this.contentBooks = contentBooks;
    }

    public ContentArticleSearchResults getContentArticleSearchResults() {
        return contentArticleSearchResults;
    }

    public void setContentArticleSearchResults(ContentArticleSearchResults contentArticleSearchResults) {
        this.contentArticleSearchResults = contentArticleSearchResults;
    }

    public ArrayList<String> getContentFilterElements() {
        if (null == contentFilterElements) {
            contentFilterElements = new ArrayList<String>();
        }
        return contentFilterElements;
    }

    public void setContentFilterElements(ArrayList<String> contentFilterElements) {
        this.contentFilterElements = contentFilterElements;
    }

    public boolean isFilterApplied() {
        if (getContentFilterElements().size() > 0) {
            return true;
        }
        return false;
    }
}
