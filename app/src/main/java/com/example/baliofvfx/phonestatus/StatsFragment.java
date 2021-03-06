package com.example.baliofvfx.phonestatus;


import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class StatsFragment extends Fragment {

    Timer t = new Timer();

    private TextView androidVersionTextView;
    private TextView systemUpTimeTextView;
    private TextView wifiTextView;
    private TextView networkTextView;
    NetworkStatus networkStatus = new NetworkStatus();

    int secondsUpTime = 0;
    int minutesUpTime = 0;
    int hoursUpTime = 0;
    int daysUpTime = 0;

    String uptimeArray[] = new String[4];



    @Override
    public void onStart() {
        super.onStart();
        showUptime(systemUpTimeTextView);
        androidVersionTextView.setText("Android SDK: " + Build.VERSION.SDK_INT + " " + Build.VERSION.RELEASE);
        networkStatus.showWiFiName(getContext(), wifiTextView);
        networkStatus.showNetworkInfo(getContext(), networkTextView);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        androidVersionTextView = (TextView)view.findViewById(R.id.androidVersionTextView);
        systemUpTimeTextView = (TextView)view.findViewById(R.id.systemUpTimeTextView);
        wifiTextView = (TextView)view.findViewById(R.id.WiFiTextViewID);
        networkTextView = (TextView)view.findViewById(R.id.netwrokTextViewID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }


    private void showUptime(final TextView textview){
        this.t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsUpTime = (int)(SystemClock.elapsedRealtime() / ((1000)) % 60);
                minutesUpTime = (int)(SystemClock.elapsedRealtime() / ((1000 * 60)) % 60);
                hoursUpTime = (int)(SystemClock.elapsedRealtime() / ((1000 * 60 * 60)) % 24);
                daysUpTime = (int)(SystemClock.elapsedRealtime() / (1000*60*60*24));


                hoursToString(daysUpTime, hoursUpTime, minutesUpTime, secondsUpTime); // Formats the uptime array[]

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textview.setText("System uptime: " + uptimeArray[0] + ":" + uptimeArray[1] + ":" + uptimeArray[2] + ":" + uptimeArray[3]);
                    }
                });

            }
        }, 0, 1000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("ON DESTROY! ");
        t.purge();
        t.cancel();
    }

    private void hoursToString(int days, int hours, int minutes, int seconds){
        if(days <= 9){
            uptimeArray[0] = "0" + Integer.toString(days);
        }
        else{
            uptimeArray[0] = Integer.toString(days);
        }

        if(hours <= 9){
            uptimeArray[1] = "0" + Integer.toString(hours);
        }
        else{
            uptimeArray[1] = Integer.toString(hours);
        }

        if(minutes <= 9){
            uptimeArray[2] = "0" + Integer.toString(minutes);
        }
        else{
            uptimeArray[2] = Integer.toString(minutes);
        }

        if(seconds <= 9){
            uptimeArray[3] = "0" + Integer.toString(seconds);
        }
        else{
            uptimeArray[3] = Integer.toString(seconds);
        }


    }
}
