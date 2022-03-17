package com.example.jarvis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value=0;
    boolean runOn=false;
    UsbManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startRun(View view) {
        runOn = true;
        TextView text = (TextView) findViewById(R.id.Strategia);
        //TODO: setup usb
        Intent intent= new Intent();
        UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
        //TODO: read from usb in a while loop

        text.setText(String.valueOf(value));
        //TODO: close usb connection
    }

    public void StopRun(View view) {
        runOn=false;
        TextView text = (TextView) findViewById(R.id.Strategia);
        text.setText(String.valueOf(value));
    }
}