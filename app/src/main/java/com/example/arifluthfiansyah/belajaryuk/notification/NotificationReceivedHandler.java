package com.example.arifluthfiansyah.belajaryuk.notification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.arifluthfiansyah.belajaryuk.BelajaryukApp;
import com.example.arifluthfiansyah.belajaryuk.ui.belajaryuk.BelajaryukActivity;
import com.example.arifluthfiansyah.belajaryuk.ui.pengajar.PengajarConfirmActivity;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by rama on 11/11/17.
 */

public class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {

    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String notificationID = notification.payload.notificationID;
        String title = notification.payload.title;
        String body = notification.payload.body;
        String smallIcon = notification.payload.smallIcon;
        String largeIcon = notification.payload.largeIcon;
        String bigPicture = notification.payload.bigPicture;
        String smallIconAccentColor = notification.payload.smallIconAccentColor;
        String sound = notification.payload.sound;
        String ledColor = notification.payload.ledColor;
        int lockScreenVisibility = notification.payload.lockScreenVisibility;
        String groupKey = notification.payload.groupKey;
        String groupMessage = notification.payload.groupMessage;
        String fromProjectNumber = notification.payload.fromProjectNumber;

        int userId; // Key of user who send notification
        String course; // Key of order notification course
        String description; // Key of order notification keterangan
        int session; // Key of order notification session
        int priceTotal; // Key of order notification price
        String statusKey; // Key if you need additional data for action from OS

        if (data != null) {
            userId = data.optInt("userId", 1);
            course = data.optString("pelajaran", null);
            description = data.optString("keterangan", null);
            session = data.optInt("sesi", 1);
            priceTotal = data.optInt("total_pembayarab", 0);
            statusKey = data.optString("status", null);
            if (statusKey != null) {
                Log.i("OneSignalExample", "statusKey set with value: " + statusKey);
                switch (statusKey) {
                    case "orderPengajar":
                        Intent intent = PengajarConfirmActivity.getStartIntent(BelajaryukApp.getContext());
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("userIdKey", userId);
                        intent.putExtra("titleKey", title);
                        intent.putExtra("courseKey", course);
                        intent.putExtra("descripionKey", description);
                        intent.putExtra("sessionKey", session);
                        intent.putExtra("priceTotal", priceTotal);
                        BelajaryukApp.getContext().startActivity(intent);
                        ((PengajarConfirmActivity) BelajaryukApp.getContext()).finish();
                        break;
                }
            }
        }
    }
}
