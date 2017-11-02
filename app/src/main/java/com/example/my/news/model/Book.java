package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class Book implements Serializable {

    private String title;
    private String description;
    private String contributor;
    private String author;
    private String contributor_note;
    private double price;
    private String age_group;
    private String publisher;
    private BookIsbn[] isbns;
    private BookRankHistory[] ranks_history;
    private BookReview[] reviews;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContributor_note() {
        return contributor_note;
    }

    public void setContributor_note(String contributor_note) {
        this.contributor_note = contributor_note;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public BookIsbn[] getIsbns() {
        return isbns;
    }

    public void setIsbns(BookIsbn[] isbns) {
        this.isbns = isbns;
    }

    public BookRankHistory[] getRanks_history() {
        return ranks_history;
    }

    public void setRanks_history(BookRankHistory[] ranks_history) {
        this.ranks_history = ranks_history;
    }

    public BookReview[] getReviews() {
        return reviews;
    }

    public void setReviews(BookReview[] reviews) {
        this.reviews = reviews;
    }
}
