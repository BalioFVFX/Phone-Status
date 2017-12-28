package com.example.baliofvfx.phonestatus;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BalioFVFX on 12/28/2017.
 */

public class NetworkStatus {

    public void showWiFiName(Context context, TextView wifiTextView){
        WifiManager wifiMgr = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        String wifiName = wifiInfo.getSSID();

        if(wifiName == "<unknown ssid>"){
            wifiTextView.setText("WiFi: Not connected");
        }
        else{
            wifiTextView.setText("WiFi: " + wifiName);
        }
    }

    public void showNetwrokName(Context context, TextView networkTextView){
        TelephonyManager manager = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        String networkName = manager.getNetworkOperatorName();
        if(networkName == ""){
            networkTextView.setText("Network: Not found");
        }
        else{
            networkTextView.setText("Network: " + networkName);
        }

    }
}
