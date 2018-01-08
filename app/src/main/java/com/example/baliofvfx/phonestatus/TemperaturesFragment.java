package com.example.baliofvfx.phonestatus;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TemperaturesFragment extends Fragment {

    BatteryStatus batteryStatus = new BatteryStatus();
    TextView batteryLevelTextView;

    @Override
    public void onStart() {
        super.onStart();
        batteryStatus.showBatteryTemperature(getContext(), batteryLevelTextView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_temperatures, container, false);
        batteryLevelTextView = (TextView)view.findViewById(R.id.batteryLevelTextView);

        return view;
    }



}
