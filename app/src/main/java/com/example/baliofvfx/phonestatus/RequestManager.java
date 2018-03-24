package com.example.baliofvfx.phonestatus;



import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by BalioFVFX on 12/29/2017.
 */


public class RequestManager {


    public static void  initCollectionForUser(final String uid){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Stats = new HashMap<>();
        Stats.put("Battery temperature", 0);
        Stats.put("author_id", uid);
        Stats.put("Is charging", false);
        Stats.put("AC charge", false);
        Stats.put("USB charge", false);
        Stats.put("Wi-Fi", "<unknown ssid>");



        db.collection("users").document(uid).set(Stats).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written! (User collection)");

                // Init the battery level collection if the users collection is created sucessfuly
                FirebaseFirestore batteryDB = FirebaseFirestore.getInstance();

                Map<String, Object> BatteryDBStats = new HashMap<>();
                BatteryDBStats.put("author_id", uid);
                BatteryDBStats.put("Battery level", 0.0);

                batteryDB.collection("users").document(uid).collection("level").document("l").set(BatteryDBStats).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written! (Battery level collection)");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println(uid);
                                Log.w(TAG, "Error writing (Battery level collection)", e);
                            }
                        });
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(uid);
                        Log.w(TAG, "Error writing document (User collection)", e);
                    }
                });

    }



    public static void  updateBatteryLevel(final float batteryLevel, final String uid){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Stats = new HashMap<>();
        Stats.put("Battery level", batteryLevel);


        db.collection("users").document(uid).collection("level").document("l").update(Stats).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written! (Battery level)");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error writing document (Battery level)", e);
                    }
                });
    }

    public static void  updateBatteryChargingStatus(final boolean isCharging, final boolean usbCharge, final boolean acCharge, final String uid){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Stats = new HashMap<>();
        Stats.put("Is charging", isCharging);
        Stats.put("USB charge", usbCharge);
        Stats.put("AC charge", acCharge);


        db.collection("users").document(uid).update(Stats).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written! (Battery charging status)");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error writing document (Battery charging status)", e);
                    }
                });
    }

    public static void  updateBatteryTemperature(final float batteryTemperature, final String uid){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Stats = new HashMap<>();
        Stats.put("Battery temperature", batteryTemperature);



        db.collection("users").document(uid).update(Stats).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written! (Battery temperature)");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error writing document (Battery temperature)", e);
                    }
                });
    }

    public static void  sendWiFi(final String wifiName, final String uid){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Stats = new HashMap<>();
        Stats.put("Wi-Fi", wifiName);


        db.collection("users").document(uid).update(Stats).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }


}
