package com.app.business.WhoIsWhoApp.util;

import android.app.Activity;
import android.content.Intent;
import com.app.business.WhoIsWhoApp.R;
import com.app.business.WhoIsWhoApp.activities.ErrorActivity;

public class Util {

    public static void displayErrorScreen(Activity contextActivity, String errorMessage) {
        Intent errorScreenIntent = new Intent(contextActivity, ErrorActivity.class);
        errorScreenIntent.putExtra(contextActivity.getString(R.string.BUNDLE_ERROR_ACTIVITY_ERROR_MESSAGE), errorMessage);
        contextActivity.startActivity(errorScreenIntent);
    }
}
