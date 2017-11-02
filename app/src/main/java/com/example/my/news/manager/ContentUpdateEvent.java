package com.example.my.news.manager;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class ContentUpdateEvent {
    private final String message;

    public ContentUpdateEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
