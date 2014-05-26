package com.app.business.WhoIsWhoApp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.app.business.WhoIsWhoApp.R;
import com.app.business.WhoIsWhoApp.activities.ErrorActivity;

public class Util {

    public static void displayErrorScreen(Activity contextActivity, String errorMessage) {
        Intent errorScreenIntent = new Intent(contextActivity, ErrorActivity.class);
        errorScreenIntent.putExtra(contextActivity.getString(R.string.BUNDLE_ERROR_ACTIVITY_ERROR_MESSAGE), errorMessage);
        contextActivity.startActivity(errorScreenIntent);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }
}
