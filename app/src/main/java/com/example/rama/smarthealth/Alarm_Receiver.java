package com.example.rama.smarthealth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Rama on 13-11-2017.
 */

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("We are in the reciever","Yay!");
        Intent service_intent = new Intent(context,RingtonePlayingService.class);
        context.startService(service_intent);


    }
}
