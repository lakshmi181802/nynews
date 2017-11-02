package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ArticleDocument implements Serializable {

    private String web_url;
    private String snippet;
    private String source;
    private Multimedia[] multimedia;
    private String pub_date;
    private String document_type;
    private String new_desk;
    private String section_name;
    private String type_of_material;
    private String _id;
    private String word_count;
    private String uri;
    private ArticleDocumentHeadline headline;
    private ArticleDocumentByLine byline;

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Multimedia[] getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia[] multimedia) {
        this.multimedia = multimedia;
    }

    public String getPub_date() {
        return pub_date;
    }

    public void setPub_date(String pub_date) {
        this.pub_date = pub_date;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getNew_desk() {
        return new_desk;
    }

    public void setNew_desk(String new_desk) {
        this.new_desk = new_desk;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public String getType_of_material() {
        return type_of_material;
    }

    public void setType_of_material(String type_of_material) {
        this.type_of_material = type_of_material;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWord_count() {
        return word_count;
    }

    public void setWord_count(String word_count) {
        this.word_count = word_count;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ArticleDocumentHeadline getHeadline() {
        return headline;
    }

    public void setHeadline(ArticleDocumentHeadline headline) {
        this.headline = headline;
    }

    public ArticleDocumentByLine getByline() {
        return byline;
    }

    public void setByline(ArticleDocumentByLine byline) {
        this.byline = byline;
    }
}
