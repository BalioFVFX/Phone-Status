package com.example.baliofvfx.phonestatus;


import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by BalioFVFX on 12/29/2017.
 */

public class RequestManager {

    public static void sendBatteryStatus(String uid, float batteryTemp, float batteryLevel, boolean isCharging, boolean usbCharge, boolean acCharge){
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Battery battery = new Battery(batteryTemp, batteryLevel, isCharging, usbCharge, acCharge);

        databaseReference.child("users").child(uid).child("batterystatus").setValue(battery);
    }


    public static void sendWiFi(String uid, String wifiName){
        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        MyNetwork myNetwork = new MyNetwork(wifiName);

        databaseReference.child("users").child(uid).child("networkstatus").setValue(myNetwork);
    }


}
