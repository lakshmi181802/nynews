package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ContentArticles implements Serializable {

    private String status;
    private String copyright;
    private String section;
    private String last_updated;
    private int num_results;
    private Article[] results;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public int getNum_results() {
        return num_results;
    }

    public void setNum_results(int num_results) {
        this.num_results = num_results;
    }

    public Article[] getResults() {
        return results;
    }

    public void setResults(Article[] results) {
        this.results = results;
    }
}
