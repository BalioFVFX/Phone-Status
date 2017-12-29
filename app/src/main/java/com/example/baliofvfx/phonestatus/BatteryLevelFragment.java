package com.example.baliofvfx.phonestatus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class BatteryLevelFragment extends Fragment {


    private TextView batteryLevelTextView;
    private BatteryStatus batteryStatus = new BatteryStatus();

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        batteryLevelTextView = (TextView)view.findViewById(R.id.batteryLevelTextViewID);
        RequestManager.sendPost(batteryStatus.batteryLevel(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battery_level, container, false);

        final Button button = view.findViewById(R.id.buttonTestId);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                batteryStatus.testFunction(getContext(), batteryLevelTextView);
            }
        });

        return view;
    }

}
