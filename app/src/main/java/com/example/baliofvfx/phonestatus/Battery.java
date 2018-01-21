package com.example.baliofvfx.phonestatus;

/**
 * Created by BalioFVFX on 1/20/2018.
 */

public class Battery {
    public float batterylevel;
    public float batterytemp;
    public boolean isCharging;
    public boolean usbCharge;
    public boolean acCharge;

    public Battery(){

    }

    public Battery(float batterylevel, float batterytemp, boolean isCharging, boolean usbCharge, boolean acCharge){
        this.batterylevel = batterylevel;
        this.batterytemp = batterytemp;
        this.isCharging = isCharging;
        this.usbCharge = usbCharge;
        this.acCharge = acCharge;
    }
}
