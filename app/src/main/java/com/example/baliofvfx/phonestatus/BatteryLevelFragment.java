package com.example.baliofvfx.phonestatus;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class BatteryLevelFragment extends Fragment {


    private TextView batteryLevelTextView;
    private BatteryStatus batteryStatus = new BatteryStatus();
    private ImageView batteryLevelImage;

    @Override
    public void onStart() {
        super.onStart();
        RequestManager.sendPost(batteryStatus.batteryLevel(getContext()));
        batteryStatus.testFunction(getContext(), batteryLevelTextView);
        drawBatteryLevel((int)batteryStatus.batteryLevel(getContext()),batteryLevelImage);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battery_level, container, false);
        batteryLevelImage = (ImageView)view.findViewById(R.id.batteryLevelImageViewID);

        return view;
    }

}
