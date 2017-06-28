package com.vnnovate.fcmnotification.FCMNotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vnnovate.fcmnotification.MainActivity;
import com.vnnovate.fcmnotification.R;


/**
 * Created by Belal on 3/18/2016.
 */
//Class extending service as it is a service that will run in background
public class NotificationListener extends FirebaseMessagingService {
    String notification_for = "";

    private static final String TAG = "MyAndroidFCMService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("From: ", "" + remoteMessage.getFrom());
        //  Log.e("Notification Body: ","" + remoteMessage.getNotification().getBody());
        //    String message = remoteMessage.getNotification().getBody();

        sendNotification("New message");
    }

    private void sendNotification(String message) {

        Log.e("message", "message");

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v4.app.NotificationCompat.Builder notificationBuilder = new android.support.v4.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("RecoverRefuel")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());


    }


}
