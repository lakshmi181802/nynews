package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class BookReview implements Serializable {

    private String book_review_link;
    private String first_chapter_link;
    private String sunday_review_link;
    private String article_chapter_link;

    public String getBook_review_link() {
        return book_review_link;
    }

    public void setBook_review_link(String book_review_link) {
        this.book_review_link = book_review_link;
    }

    public String getFirst_chapter_link() {
        return first_chapter_link;
    }

    public void setFirst_chapter_link(String first_chapter_link) {
        this.first_chapter_link = first_chapter_link;
    }

    public String getSunday_review_link() {
        return sunday_review_link;
    }

    public void setSunday_review_link(String sunday_review_link) {
        this.sunday_review_link = sunday_review_link;
    }

    public String getArticle_chapter_link() {
        return article_chapter_link;
    }

    public void setArticle_chapter_link(String article_chapter_link) {
        this.article_chapter_link = article_chapter_link;
    }
}
