package com.app.business.WhoIsWhoApp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import com.app.business.WhoIsWhoApp.R;
import com.app.business.WhoIsWhoApp.adapters.EmployeeListAdapter;
import com.app.business.WhoIsWhoApp.model.Employee;
import com.app.business.WhoIsWhoApp.parsers.EmployeeHtmlParser;
import com.app.business.WhoIsWhoApp.parsers.EmployeeHtmlParserListener;
import com.app.business.WhoIsWhoApp.util.Util;

import java.util.List;

public class EmployeeListActivity extends Activity implements EmployeeHtmlParserListener {

    private final static String THE_APP_BUSINESS_TEAM_URL = "http://www.theappbusiness.com/our-team/";

    private ProgressDialog mProgressDialog;
    private ListView mEmployeesListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);

        initProgressDialog();

        mEmployeesListView = (ListView) findViewById(R.id.employees_lv);

        EmployeeHtmlParser employeeHtmlParser = new EmployeeHtmlParser(this);
        employeeHtmlParser.execute(THE_APP_BUSINESS_TEAM_URL);
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.parsing_progress_dialog_title));
        mProgressDialog.setMessage(getString(R.string.parsing_progress_dialog_initial_message));
        mProgressDialog.show();
    }

    @Override
    public void onEmployeeHtmlParseComplete(List<Employee> employeeList) {
        mProgressDialog.dismiss();

        if (employeeList != null && employeeList.size() > 0) {
            EmployeeListAdapter employeeListAdapter = new EmployeeListAdapter(this, R.layout.employee_list_item, employeeList);
            mEmployeesListView.setAdapter(employeeListAdapter);
        } else {
            Util.displayErrorScreen(this, getString(R.string.empty_employee_list_received_error_message));
        }
    }

    @Override
    public void onParseError(String reason) {
        Util.displayErrorScreen(this, reason);
    }
}
