package com.example.jarvis;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.*;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    int value=0;
    boolean runOn=false;
    List<UsbDevice> listDevices= new ArrayList<>();
    List<String> listNames= new ArrayList<>();
    TextView text = (TextView) findViewById(R.id.Strategia);
    UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
    IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if(device != null){
                            setupCommunication(device);
                        }
                    }
                    else {
                        Log.d(TAG, "permission denied for device " + device);
                    }
                }
            }
          }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PendingIntent permissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);

        registerReceiver(usbReceiver, filter);
        manager.requestPermission(listDevices.get(0),permissionIntent);
    }
    private void setupCommunication(UsbDevice device) {
        //TODO: implement communication setup
    }

    public void startRun(View view) {
        runOn = true;

        HashMap<String,UsbDevice> devices= manager.getDeviceList();
        for (UsbDevice d : devices.values()) {
            listNames.add(d.getDeviceName());
            listDevices.add(d);
        }
        //TODO: read from usb in a while loop

        //TODO: close usb connection
    }

    public void StopRun(View view) {
        runOn=false;
        text.setText(String.valueOf(value));
    }
}