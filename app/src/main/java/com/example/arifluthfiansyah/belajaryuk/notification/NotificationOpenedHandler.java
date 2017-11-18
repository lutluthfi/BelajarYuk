package com.example.arifluthfiansyah.belajaryuk.notification;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.arifluthfiansyah.belajaryuk.BelajaryukApp;
import com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk.BelajaryukActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.main.MainActivity;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by rama on 11/11/17.
 */

public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String notificationID = result.notification.payload.notificationID;
        String title = result.notification.payload.title;
        String body = result.notification.payload.body;
        String smallIcon = result.notification.payload.smallIcon;
        String largeIcon = result.notification.payload.largeIcon;
        String bigPicture = result.notification.payload.bigPicture;
        String smallIconAccentColor = result.notification.payload.smallIconAccentColor;
        String sound = result.notification.payload.sound;
        String ledColor = result.notification.payload.ledColor;
        int lockScreenVisibility = result.notification.payload.lockScreenVisibility;
        String groupKey = result.notification.payload.groupKey;
        String groupMessage = result.notification.payload.groupMessage;
        String fromProjectNumber = result.notification.payload.fromProjectNumber;

        String statusKey; // Key if you need additional data for action from OS

        if (data != null) {
            statusKey = data.optString("status", null);
            if (statusKey != null) {
                Log.i("OneSignalExample", "statusKey set with value: "+statusKey);
                switch (statusKey){
                    case "orderPengajar":
                        // Intent intent = BelajaryukActivity.getStartIntent(BelajaryukApp.getContext());
                        // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                        // BelajaryukApp.getContext().startActivity(intent);
                        // Don't know what action have to do
                        break;
                }
            }
        }

        // These code can be used to take some actions if you opened notification
        if (actionType == OSNotificationAction.ActionType.ActionTaken){
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
            if (result.action.actionID != null) {
                switch (result.action.actionID){
                    case "ActionOrderAccept":
                        // "Terima"
                        Toast.makeText(BelajaryukApp.getContext(), "Accept button was pressed", Toast.LENGTH_SHORT).show();
                        break;
                    case "ActionOrderDecline":
                        // "Tidak"
                        Toast.makeText(BelajaryukApp.getContext(), "Decline button was pressed", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }

        // The following can be used to open an Activity of your choice.
        // Replace - getApplicationContext() - with any Android Context.
        // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
        // startActivity(intent);

        // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
        //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
    }
}
