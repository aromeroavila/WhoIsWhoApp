package com.app.business.WhoIsWhoApp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.app.business.WhoIsWhoApp.R;

public class ErrorActivity extends Activity {

    private String mErrorMessage;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_screen);

        setTitle(getString(R.string.error_screen_title));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mErrorMessage = bundle.getString(getString(R.string.BUNDLE_ERROR_ACTIVITY_ERROR_MESSAGE));
            TextView errorMessageTextView = (TextView) findViewById(R.id.error_message_tv);
            errorMessageTextView.setText(mErrorMessage);
        }
    }
}