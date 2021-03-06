package com.example.boradcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class ExampleBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        /*if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show();
        }*/
        //Will not work above android api 24 if we register it statically/manifest
        /*if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
           // Toast.makeText(context, "connectivity changed", Toast.LENGTH_SHORT).show();
            boolean noConnectivity = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noConnectivity){
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            }
        }*/

        //Our Implicit broadcast
        if ("com.codinginflow.EXAMPLE_ACTION".equals(intent.getAction())){
            String receivedText = intent.getStringExtra("com.codinginflow.EXAMPLE_TEXT");
            Toast.makeText(context, receivedText, Toast.LENGTH_SHORT).show();
        }
    }
}
/* *
 * Explicit broadcast: We specifie the component we want to trigger directly. For example, we want to send a broadcast that only trigger our ExampleBroadcastReceiver and no other broadcast reciver.
 * Implicit broadcast: Same as system broadcast where we define an action and if the broadcast receiver want to reseave this broadcast we have to set an intent filter with the same action on it.
 * **/