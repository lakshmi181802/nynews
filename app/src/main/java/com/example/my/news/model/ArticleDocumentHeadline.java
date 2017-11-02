package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ArticleDocumentHeadline implements Serializable {
    private String main;
    private String print_headline;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPrint_headline() {
        return print_headline;
    }

    public void setPrint_headline(String print_headline) {
        this.print_headline = print_headline;
    }
}
