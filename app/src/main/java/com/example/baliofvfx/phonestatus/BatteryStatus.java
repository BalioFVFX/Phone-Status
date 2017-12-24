package com.example.baliofvfx.phonestatus;


import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BalioFVFX on 12/17/2017.
 */

public class BatteryStatus {


    public void testFunction(Context context, TextView batteryTextView){

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryLevel = (level / (float)scale) * 100;

        System.out.println("Current battery level: " + batteryLevel);
//        Toast.makeText(context
//                , Float.toString(batteryLevel), Toast.LENGTH_LONG).show();

        batteryTextView.setText("Battery level: " + Float.toString(batteryLevel) + "%");
    }




}

