package com.example.baliofvfx.phonestatus;

import android.net.wifi.WifiInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class StatsFragment extends Fragment {


    private TextView androidVersionTextView;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        androidVersionTextView = (TextView)view.findViewById(R.id.androidVersionTextView);
        androidVersionTextView.setText("Android SDK: " + Build.VERSION.SDK_INT + " " + Build.VERSION.RELEASE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }
}
