package com.example.baliofvfx.phonestatus;


import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by BalioFVFX on 12/17/2017.
 */

public class BatteryStatus extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(this.batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        return super.onStartCommand(intent, flags, startId);
    }


    private final BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryLevel = (level / (float)scale) * 100;

            System.out.println("Current battery level: " + batteryLevel);


            float batteryTemp = ((float) intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0) / 10);

            System.out.println("Temp: " + batteryTemp);

            Toast.makeText(context, "Level: " + batteryLevel + " Temp: " + batteryTemp, Toast.LENGTH_SHORT).show();
            RequestManager.sendBatteryStats(MainActivity.email, batteryTemp, batteryLevel);

        }
    };


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

    public float batteryLevel(Context context){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryLevel = (level / (float)scale) * 100;

        System.out.println("Current battery level: " + batteryLevel);
//        Toast.makeText(context
//                , Float.toString(batteryLevel), Toast.LENGTH_LONG).show();

        return batteryLevel;
    }

    public float getBatteryTemp(Context context){
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        float batteryTemp = ((float) batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0) / 10);

        return batteryTemp;

    }

    public void showBatteryTemperature(Context context, TextView batteryTemperatureTextView){

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        float batteryTemp = ((float) batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0) / 10);

        batteryTemperatureTextView.setText(Float.toString(batteryTemp) + "°C");

    }




}

