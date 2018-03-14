package com.young.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("MainActivity", "the Service is Bound ");
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("MainActivity", "the BluetoothService is Disconnected ");
            isBound = false;

        }
    };
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        intent = new Intent();
        //显式打开
        intent.setComponent(new ComponentName("com.young.server",
                "com.young.server.service.CleanerService"));

        //隐式打开
//        intent.setPackage("com.young.server");
//        intent.setAction("android.intent.action.CLEANER");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                 //startService(intent);
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.button2:
                //stopService(intent);
                if (isBound) {
                    isBound = false;
                    unbindService(connection);
                }
                break;
        }
    }
}