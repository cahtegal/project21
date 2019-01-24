package com.hidevs.cariweton;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FireBaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    public static final String TOKEN_REFRESH_EVENT = "messaging-token-refresh";
    @Override
    public void onTokenRefresh() {
//Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
