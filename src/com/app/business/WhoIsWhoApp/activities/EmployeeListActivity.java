package com.app.business.WhoIsWhoApp.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.app.business.WhoIsWhoApp.R;
import com.app.business.WhoIsWhoApp.adapters.EmployeeListAdapter;
import com.app.business.WhoIsWhoApp.model.Employee;
import com.app.business.WhoIsWhoApp.parsers.EmployeeHtmlParser;
import com.app.business.WhoIsWhoApp.parsers.EmployeeHtmlParserListener;
import com.app.business.WhoIsWhoApp.util.Util;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends Activity implements EmployeeHtmlParserListener {

    private final static String THE_APP_BUSINESS_TEAM_URL = "http://www.theappbusiness.com/our-team/";

    private ProgressDialog mProgressDialog;
    private ListView mEmployeesListView;
    private EmployeeHtmlParser mEmployeeHtmlParser;
    private List<Employee> mSavedEmployeeList;
    private EmployeeListAdapter mEmployeeListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);

        mEmployeesListView = (ListView) findViewById(R.id.employees_lv);

        if (Util.isNetworkConnected(this)) {
            if (savedInstanceState != null && savedInstanceState.getParcelableArrayList(
                    getString(R.string.BUNDLE_EMPLOYEE_LIST_ACTIVITY_SAVED_LIST)) != null) {
                mSavedEmployeeList = savedInstanceState.
                        getParcelableArrayList(getString(R.string.BUNDLE_EMPLOYEE_LIST_ACTIVITY_SAVED_LIST));
                EmployeeListAdapter employeeListAdapter =
                        new EmployeeListAdapter(this, R.layout.employee_list_item, mSavedEmployeeList);
                mEmployeesListView.setAdapter(employeeListAdapter);
            } else {
                startHtmlParsing();
            }
        } else {
            Util.displayErrorScreen(this, getString(R.string.no_internet_connection_error_message));
        }
    }

    private void startHtmlParsing() {
        initProgressDialog();
        mEmployeeHtmlParser = new EmployeeHtmlParser(this);
        mEmployeeHtmlParser.execute(THE_APP_BUSINESS_TEAM_URL);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mSavedEmployeeList != null) {
            outState.putParcelableArrayList(getString(R.string.BUNDLE_EMPLOYEE_LIST_ACTIVITY_SAVED_LIST),
                    (ArrayList<Employee>) mSavedEmployeeList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // The async tasks are cancelled in case they exist and they are running to avoid problems
        // when the phone is rotated.
        if (mEmployeeHtmlParser != null && mEmployeeHtmlParser.getStatus() == AsyncTask.Status.RUNNING) {
            mEmployeeHtmlParser.cancel(true);
        }

        // If the activity is destroyed before the dialog has been dismissed here that is done to avoid
        // a memory leak.
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employee_list_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                if (mEmployeeListAdapter != null) {
                    mEmployeeListAdapter.notifyDataSetChanged();
                }
                startHtmlParsing();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle(getString(R.string.parsing_progress_dialog_title));
        mProgressDialog.setMessage(getString(R.string.parsing_progress_dialog_initial_message));
        mProgressDialog.show();
    }

    @Override
    public void onEmployeeHtmlParseComplete(List<Employee> employeeList) {
        mProgressDialog.dismiss();

        if (employeeList != null && employeeList.size() > 0) {
            mSavedEmployeeList = employeeList;
            mEmployeeListAdapter = new EmployeeListAdapter(this, R.layout.employee_list_item, mSavedEmployeeList);
            mEmployeesListView.setAdapter(mEmployeeListAdapter);
        } else {
            Util.displayErrorScreen(this, getString(R.string.empty_employee_list_received_error_message));
        }
    }

    @Override
    public void onParseError(String reason) {
        Util.displayErrorScreen(this, reason);
    }
}
