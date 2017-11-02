package com.example.my.news.restclient.interceptors;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ELURV001 on 11/1/17.
 */

/**
 * <p>Rewrites the response {@link CacheControl} header to enable
 * the response caching.</p>
 * <p>
 * <p>This interceptor should be added as a <b>Network Interceptor</b>
 * on the {@link okhttp3.OkHttpClient} object creation</p>
 */
public class RewriteResponseCacheControlHeaderInterceptor implements Interceptor {

    private int cacheDuration;

    /**
     * Builds a new Interceptor to rewrite the response header to add
     * the necessary Cache-Control instructions.
     *
     * @param cacheDuration The time period (in seconds) in which the cache is considered valid.
     */
    public RewriteResponseCacheControlHeaderInterceptor(int cacheDuration) {
        setCacheDuration(cacheDuration);
    }

    /**
     * Gets the cache time duration, in seconds.
     *
     * @return The time period in which the cache is considered valid.
     */
    public int getCacheDuration() {
        return cacheDuration;
    }

    /**
     * Sets the cache time duration, in seconds.
     *
     * @param cacheDuration The time period (in seconds) in which the cache is considered valid.
     */
    public void setCacheDuration(int cacheDuration) {
        this.cacheDuration = cacheDuration;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        // Builds the CacheControl with the instructions to enable the response caching.
        CacheControl cacheControl = new CacheControl.Builder()
                .maxAge(cacheDuration, TimeUnit.SECONDS)
                .build();

        // Add the new header to the obtained response.
        return response.newBuilder()
                .removeHeader("ETag")
                .header("Cache-Control", cacheControl.toString())
                .build();
    }
}
