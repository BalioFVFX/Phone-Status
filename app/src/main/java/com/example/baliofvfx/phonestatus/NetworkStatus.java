package com.example.baliofvfx.phonestatus;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.widget.TextView;

/**
 * Created by BalioFVFX on 12/28/2017.
 */

public class NetworkStatus extends Service {

    public void showWiFiName(Context context, TextView wifiTextView){
        WifiManager wifiMgr = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        String name = wifiInfo.getSSID();

        if(name == "<unknown ssid>" || name == "0x"){
            wifiTextView.setText("WiFi: Not connected");
        }
        else{
            wifiTextView.setText("WiFi: " + name);

        }
    }


    private final BroadcastReceiver WiFiReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

            WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            final String wifiName = wifiInfo.getSSID();


            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            RequestManager.sendWiFi(MainActivity.uid, wifiName);
                        }
                    },
                    5000
            );



        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        registerReceiver(WiFiReceiver, intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    

    public void showNetworkInfo(Context context, TextView networkTextView){
        TelephonyManager manager = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        String networkName = manager.getNetworkOperatorName();

        int networkType = manager.getDataState();

        if(networkName == ""){
            networkTextView.setText("Network: Not found");
        }
        else{
            networkTextView.setText("Network: " + networkName);
        }


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
