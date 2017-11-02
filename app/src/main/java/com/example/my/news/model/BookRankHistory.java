package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class BookRankHistory implements Serializable {

    private String primary_isbn10;
    private String primary_isbn13;
    private int rank;
    private String list_name;
    private String display_name;
    private String published_date;
    private String bestsellers_date;
    private int weeks_on_list;
    private String ranks_last_week;
    private int asterisk;
    private int dagger;

    public String getPrimary_isbn10() {
        return primary_isbn10;
    }

    public void setPrimary_isbn10(String primary_isbn10) {
        this.primary_isbn10 = primary_isbn10;
    }

    public String getPrimary_isbn13() {
        return primary_isbn13;
    }

    public void setPrimary_isbn13(String primary_isbn13) {
        this.primary_isbn13 = primary_isbn13;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public String getBestsellers_date() {
        return bestsellers_date;
    }

    public void setBestsellers_date(String bestsellers_date) {
        this.bestsellers_date = bestsellers_date;
    }

    public int getWeeks_on_list() {
        return weeks_on_list;
    }

    public void setWeeks_on_list(int weeks_on_list) {
        this.weeks_on_list = weeks_on_list;
    }

    public String getRanks_last_week() {
        return ranks_last_week;
    }

    public void setRanks_last_week(String ranks_last_week) {
        this.ranks_last_week = ranks_last_week;
    }

    public int getAsterisk() {
        return asterisk;
    }

    public void setAsterisk(int asterisk) {
        this.asterisk = asterisk;
    }

    public int getDagger() {
        return dagger;
    }

    public void setDagger(int dagger) {
        this.dagger = dagger;
    }
}
