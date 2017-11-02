package com.example.my.news;

import android.app.Application;
import android.content.Context;

import com.example.my.news.model.Content;

/**
 * Created by ELURV001 on 11/1/17.
 */

public class NyTimesApplication extends Application {

    private static NyTimesApplication instance;
    private Content content;

    public static NyTimesApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public Content getContent() {
        if (null ==  content) {
            content = new Content();
        }
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
