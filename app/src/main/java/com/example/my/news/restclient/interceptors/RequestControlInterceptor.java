package com.example.my.news.restclient.interceptors;

import com.example.my.news.utils.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ELURV001 on 11/1/17.
 */

/**
 * <p>Intercepts the outgoing request and adds a {@link CacheControl} header
 * based on whether a cache or network call must be forced. If no forcing is
 * needed, request pass through unmodified</p>
 * <p>
 * <p>Forcing a request to either network or cache is done via flags that
 * can be set by calling the respective "force" methods. The cache-forced
 * response is also made when no network connection is detected (i.e.
 * airplane mode is on)</p>
 * <p>
 * <p>This interceptor should be added as a <b>Application Interceptor</b> on the
 * {@link okhttp3.OkHttpClient} object creation</p>
 */
public class RequestControlInterceptor implements Interceptor {

    private boolean forceNetwork;
    private boolean forceCache;

    /**
     * Force a network request, bypassing the cache validity.
     */
    public void forceNetwork() {
        forceNetwork = true;
    }

    /**
     * Force a cache request. Response might be valid or not based
     * on whether a cache was previously created from a valid response.
     */
    public void forceCache() {
        forceCache = true;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (forceNetwork) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            forceNetwork = false;
        }

        if (forceCache || !NetworkUtils.isConnected()) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            forceCache = false;
        }

        return chain.proceed(request);
    }

}
