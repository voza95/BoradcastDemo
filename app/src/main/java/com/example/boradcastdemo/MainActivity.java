package com.example.boradcastdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    ExampleBroadcastReceiver exampleBroadcastReceiver =  new ExampleBroadcastReceiver();

    Switch wifiSwitch;
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifiSwitch = findViewById(R.id.wifiSwitch);

        //For Implicit broadcast call
        IntentFilter filter = new IntentFilter("com.codinginflow.EXAMPLE_ACTION");
        registerReceiver(exampleBroadcastReceiver, filter);

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                wifiManager.setWifiEnabled(true);
                wifiSwitch.setText("WiFi is ON");
            }else {
                wifiManager.setWifiEnabled(false);
                wifiSwitch.setText("WiFi is OFF");
            }
        });

        /*if (wifiManager.isWifiEnabled()){
            wifiManager.setWifiEnabled(true);
            wifiSwitch.setText("WiFi is ON");
        }else {
            wifiManager.setWifiEnabled(false);
            wifiSwitch.setText("WiFi is OFF");
        }*/
    }

    //To call our Implicit broadcast call this from another application
    /*void callFromOtherApplication(View v){
        Intent intent = new Intent("com.codinginflow.EXAMPLE_ACTION");
        intent.putExtra("com.codinginflow.EXAMPLE_TEXT","Broadcast received");
        sendBroadcast(intent);
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        //To specify what action we want to liston to.
        /*IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(exampleBroadcastReceiver, filter);*/
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(exampleBroadcastReceiver);
        unregisterReceiver(wifiStateReceiver);
    }



    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiStateExtra){
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiManager.setWifiEnabled(true);
                    wifiSwitch.setText("WiFi is ON");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiManager.setWifiEnabled(false);
                    wifiSwitch.setText("WiFi is OFF");
                    break;
            }
        }
    };
}