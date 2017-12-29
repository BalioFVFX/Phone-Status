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

    public void showNetworkInfo(Context context, TextView networkTextView, TextView networkTypeTextView, TextView mobileDataTextView){
        TelephonyManager manager = (TelephonyManager)context.getSystemService(context.TELEPHONY_SERVICE);
        String networkName = manager.getNetworkOperatorName();

        int networkType = manager.getNetworkType();

        if(networkName == ""){
            networkTextView.setText("Network: Not found");
        }
        else{
            networkTextView.setText("Network: " + networkName);
        }


        // Fix this!
        switch (networkType){
            case TelephonyManager.DATA_CONNECTED:
                mobileDataTextView.setText("Mobile Data: Turned On");
                break;
            case TelephonyManager.DATA_DISCONNECTED:
                mobileDataTextView.setText("Mobile Data: Turned Off");
                break;
            default:
                mobileDataTextView.setText("Mobile Data: Not Found");
        }

        switch (networkType) {

            //2G Network
            case TelephonyManager.NETWORK_TYPE_GPRS:
                networkTypeTextView.setText("Network Type: 2G GPRS");
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                networkTypeTextView.setText("Network Type: 2G EDGE");
                break;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                networkTypeTextView.setText("Network Type: 2G CDMA");
                break;
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                networkTypeTextView.setText("Network Type: 2G 1xRTT");
                break;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                networkTypeTextView.setText("Network Type: 2G iDEN");
                break;

                //3G Network
            case TelephonyManager.NETWORK_TYPE_UMTS:
                networkTypeTextView.setText("Network Type: 3G UMTS");
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                networkTypeTextView.setText("Network Type: 3G EVDO revision 0");
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                networkTypeTextView.setText("Network Type: 3G EVDO revision A");
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                networkTypeTextView.setText("Network Type: 3G HSDPA");
                break;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                networkTypeTextView.setText("Network Type: 3G HSUPA");
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                networkTypeTextView.setText("Network Type: 3G HSPA");
                break;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                networkTypeTextView.setText("Network Type: 3G EVDO revision B");
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                networkTypeTextView.setText("Network Type: 3G eHRDP");
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                networkTypeTextView.setText("Network Type: 3G HSPA+");
                break;
                // 4G Network
            case TelephonyManager.NETWORK_TYPE_LTE:
                networkTypeTextView.setText("Network Type: 4G LTE");
                break;
            default:
                networkTypeTextView.setText("Network Type: Unknown");
                break;
        }

    }
}
