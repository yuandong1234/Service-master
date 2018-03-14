package com.young.server.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by yuandong on 2018/3/14.
 */

public class CleanerService extends Service {
    private final IBinder mBinder = new BluetoothBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("CleanerService", "onCreate()...");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e("CleanerService", "onStart()...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("CleanerService", "onStartCommand()...");
        showDialog("onStartCommand()...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("CleanerService", "onBind()...");
        showDialog("onBind()...");
        return this.mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("CleanerService", "onUnbind()...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("CleanerService", "onDestroy()...");
        showDialog("onDestroy()...");
        super.onDestroy();
    }

    public class BluetoothBinder extends Binder {
        public CleanerService getService() {
            return CleanerService.this;
        }
    }



    private void showDialog(String life) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(life);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("owen", "Yes is clicked");
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.e("owen", "No is clicked");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }
}
