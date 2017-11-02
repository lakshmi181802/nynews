package com.example.my.news.manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by ELURV001 on 11/1/17.
 */
public class ContentManagerService extends Service {

    public final static String ACTION_DATA_REFRESH_EXECUTED = "dcl.intent.action.DATA_REFRESH_EXECUTED";
    public final static String EXTRA_EXECUTION_MODE = "executionMode";
    public final static String EXTRA_EXECUTION_RESULT = "executionResult";
    private LocalBroadcastManager broadcastManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        broadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        executeContentManager(intent.getIntExtra(EXTRA_EXECUTION_MODE, ContentManager.EXECUTION_MODE_FIRST_LAUNCH));
        return START_NOT_STICKY;
    }

    private void executeContentManager(int executionMode) {
        Log.d(this.getClass().getSimpleName(), "executeContentManager executionMode = " + executionMode);

        ContentManager.getInstance().execute(executionMode, new ContentManager.Callback() {
            @Override
            public void onExecutionSuccessful() {
                notifyResult(true);
            }

            @Override
            public void onExecutionFailure() {
                notifyResult(false);
            }
        });
    }

    private void notifyResult(boolean successful) {
        final Intent intent = new Intent(ACTION_DATA_REFRESH_EXECUTED);
        intent.putExtra(EXTRA_EXECUTION_RESULT, successful);
        broadcastManager.sendBroadcast(intent);

        stopSelf();
    }

}
