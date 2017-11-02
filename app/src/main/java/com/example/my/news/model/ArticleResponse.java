package com.example.my.news.model;

import java.io.Serializable;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ArticleResponse implements Serializable {
    private ArticleDocument[] docs;

    public ArticleDocument[] getDocs() {
        return docs;
    }

    public void setDocs(ArticleDocument[] docs) {
        this.docs = docs;
    }
}
