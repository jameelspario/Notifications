package com.spario.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "channel id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();
    }

    public void btn1(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                notification();

            }
        }).start();
    }

    public void btn2(View view) {

    }

    public void btn3(View view) {

    }

    public void btn4(View view) {

    }

    String imgUrl="https://i.ibb.co/Qd41Zwg/OIP.jpg";
    String appImgUrl="https://i.ibb.co/LzSVGLW/OIP-1.jpg";
    String notifTitle="spario";
    String notifText="heres the app that will be loaded";
    RemoteViews /*contentViewBig,*/contentViewSmall;

    int icon = R.drawable.ic_android_black_24dp;

    void notification(){
        try {
            long when = System.currentTimeMillis();
            NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            //contentViewBig = new RemoteViews(getPackageName(), R.layout.custom_notification);
            contentViewSmall = new RemoteViews(getPackageName(),R.layout.custom_notification_small);
            //Null and Empty checks for your Key Value Pairs
            /*if(imgUrl!=null && !imgUrl.isEmpty()) {
                URL imgUrlLink = new URL(imgUrl);
                contentViewBig.setImageViewBitmap(R.id.image_pic, BitmapFactory.decodeStream(imgUrlLink.openConnection().getInputStream()));
            }*/
            if(imgUrl!=null && !imgUrl.isEmpty()) {
                URL appImgUrlLink = new URL(imgUrl);
                //contentViewBig.setImageViewBitmap(R.id.image_app, BitmapFactory.decodeStream(appImgUrlLink.openConnection().getInputStream()));
                contentViewSmall.setImageViewBitmap(R.id.image_app, BitmapFactory.decodeStream(appImgUrlLink.openConnection().getInputStream()));
            }
            if(notifTitle!=null && !notifTitle.isEmpty()) {
                //contentViewBig.setTextViewText(R.id.title, notifTitle);
                contentViewSmall.setTextViewText(R.id.title, notifTitle);
            }

            if(notifText!=null && !notifText.isEmpty()) {
                //contentViewBig.setTextViewText(R.id.text, notifText);
                contentViewSmall.setTextViewText(R.id.text, notifText);
            }

            Intent notificationIntent = new Intent(getApplicationContext(), NitifActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(icon)
                    .setCustomContentView(contentViewSmall)
                    .setCustomBigContentView(contentViewSmall)
                    .setContentTitle("Custom Notification")
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setWhen(when);

            Log.e("TAG", "notification showing..");
            mNotificationManager.notify(1, notificationBuilder.build());


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}