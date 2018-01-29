package com.example.baliofvfx.phonestatus;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BatteryLevelFragment extends Fragment {


    private TextView batteryLevelTextView;
    private BatteryStatus batteryStatus = new BatteryStatus();
    private ImageView batteryLevelImage;
    private ToggleButton desktopMonitoringToggleButton;
    private TextView currentUserTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onStart() {
        super.onStart();
        currentUserTextView.setText("Username: " + MainActivity.currentUsername);
        drawBatteryLevel((int)batteryStatus.batteryLevel(getContext()),batteryLevelImage);
        batteryLevelTextView.setText("Battery Level: " + batteryStatus.batteryLevel(getContext()));


        Toast.makeText(getContext(), MainActivity.uid, Toast.LENGTH_SHORT).show();

        if(isMyServiceRunning(BatteryStatus.class) == true && isMyServiceRunning(NetworkStatus.class)){
            desktopMonitoringToggleButton.setChecked(true);
        }
        else{
            desktopMonitoringToggleButton.setChecked(false);
        }

    }

    private void drawBatteryLevel(int batteryLevel, ImageView batteryLevelImage){
        batteryLevel = 100 - batteryLevel;

        Bitmap bitmap = Bitmap.createBitmap(200,300, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GREEN);
        canvas.drawRect(0,0,170,300, paint);
        paint.setColor(Color.GRAY);
        canvas.drawRect(0,0,170,batteryLevel * 3, paint);
        batteryLevelImage.setImageBitmap(bitmap);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        batteryLevelTextView = (TextView)view.findViewById(R.id.batteryLevelTextViewID);

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battery_level, container, false);


        final Intent BatteryStatusIntent = new Intent(getContext(), BatteryStatus.class);
        final Intent WiFiIntent = new Intent(getContext(), NetworkStatus.class);


        System.out.println(isMyServiceRunning(BatteryStatus.class));

        batteryLevelImage = (ImageView)view.findViewById(R.id.batteryLevelImageViewID);

        currentUserTextView = (TextView)view.findViewById(R.id.usernameTextView);
        desktopMonitoringToggleButton = (ToggleButton)view.findViewById(R.id.monitoringToggleButton);

        desktopMonitoringToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    getActivity().startService(BatteryStatusIntent);

                    System.out.println("Click! Battery Status isServiceRunning: " + isMyServiceRunning(BatteryStatus.class));

                    getActivity().startService(WiFiIntent);
                    System.out.println("Click! WiFi Status isServiceRunning: " + isMyServiceRunning(NetworkStatus.class));
                }
                else{
                    getActivity().stopService(BatteryStatusIntent);
                    System.out.println("Click! Battery Status isServiceRunning: " + isMyServiceRunning(BatteryStatus.class));

                    getActivity().stopService(WiFiIntent);
                    System.out.println("Click! WiFi Status isServiceRunning: " + isMyServiceRunning(NetworkStatus.class));
                }
            }
        });
        return view;
    }

}
