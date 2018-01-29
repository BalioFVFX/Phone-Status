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
        Stats.put("author_id", uid);
        Stats.put("Battery level", 0.0);
        Stats.put("Battery temperature", 0.0);
        Stats.put("Is charging", false);
        Stats.put("AC charge", false);
        Stats.put("USB charge", false);
        Stats.put("Wi-Fi", "<unknown ssid>");



        db.collection("users").document(MainActivity.uid).set(Stats).addOnSuccessListener(new OnSuccessListener<Void>(){
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(uid);
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }



    public static void  updateBatteryLevel(final float batteryLevel, final float batteryTemp, final boolean isCharging, final boolean usbCharge, final boolean acCharge){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> Stats = new HashMap<>();
        Stats.put("Battery level", batteryLevel);
        Stats.put("Battery temperature", batteryTemp);
        Stats.put("Is charging", isCharging);
        Stats.put("USB charge", usbCharge);
        Stats.put("AC charge", acCharge);


        db.collection("users").document(MainActivity.uid).update(Stats).addOnSuccessListener(new OnSuccessListener<Void>(){
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
