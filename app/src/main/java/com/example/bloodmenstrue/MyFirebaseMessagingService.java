package com.example.bloodmenstrue;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private static final String CANAL = "MyNotificationCanal";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String myMessage = remoteMessage.getNotification().getBody();
        Log.d("FirebaseMessage", "vous avez une nouvelle notification : " +myMessage);

        //creer une notification
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this,CANAL);
        notification.setContentTitle("ma super notif");
        notification.setContentText(myMessage);

        //action rediriger
        Intent intent = new Intent( getApplicationContext(),welcome.class);

        //icone
            notification.setSmallIcon(R.drawable.cloche);
            //envoie de la notif
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId =getString(R.string.notification_channel_id);
            String channelTitle=getString(R.string.notification_channel_title);
            String channelDescription = getString(R.string.notification_channel_description);
            NotificationChannel channel = new NotificationChannel(channelId, channelTitle, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelDescription);

            notification.setChannelId(channelId);

        }
        NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification.build());

    }
}
