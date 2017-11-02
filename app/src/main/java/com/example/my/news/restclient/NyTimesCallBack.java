package com.example.my.news.restclient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ELURV001 on 11/1/17.
 */

/**
 * Custom implementation of the {@link Callback} that checks if the
 * response was successful and re-directs to specific methods so
 * the handling of the response is better made by the caller.
 * <p>
 * This callback can be passed to the {@link Call#enqueue(Callback)}
 * method directly instead of the normal Retrofit callback.
 *
 * @param <T> Successful response body type.
 */
public abstract class NyTimesCallBack<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponseSuccess(response.body(), response.raw().cacheResponse() != null);
        } else {
            onResponseError(response.code());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(new Exception(t));
    }

    /**
     * Invoked for a received HTTP successful response.
     */
    public abstract void onResponseSuccess(T t, boolean fromCache);

    /**
     * Invoked when an error response is received, returning the error code
     * sent by the service.
     */
    public abstract void onResponseError(int errorCode);

    /**
     * Invoked when an unknown error occurred talking to the server.
     */
    public abstract void onFailure(Exception e);
}
