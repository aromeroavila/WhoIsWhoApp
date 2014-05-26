package com.app.business.WhoIsWhoApp.activities;

import android.app.Activity;
import android.os.Bundle;
import com.app.business.WhoIsWhoApp.R;
import com.app.business.WhoIsWhoApp.model.Employee;
import com.app.business.WhoIsWhoApp.parsers.EmployeeHtmlParser;
import com.app.business.WhoIsWhoApp.parsers.EmployeeHtmlParserListener;

import java.io.IOException;
import java.util.List;

public class EmployeeListActivity extends Activity implements EmployeeHtmlParserListener {

    private final static String THE_APP_BUSINESS_TEAM_URL = "http://www.theappbusiness.com/our-team/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_list_activity);

        EmployeeHtmlParser employeeHtmlParser = new EmployeeHtmlParser(this);
        employeeHtmlParser.execute(THE_APP_BUSINESS_TEAM_URL);
    }

    @Override
    public void onUpdateHtmlParseProgress(String progressMessage) {

    }

    @Override
    public void onEmployeeHtmlParseComplete(List<Employee> employeeList) {

    }
}
