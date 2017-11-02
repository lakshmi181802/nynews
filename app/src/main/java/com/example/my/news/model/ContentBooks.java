package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ContentBooks implements Serializable {

    private String status;
    private String copyright;
    private int num_results;
    private Book[] results;

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

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public Book[] getResults() {
        return results;
    }

    public void setResults(Book[] results) {
        this.results = results;
    }
}
