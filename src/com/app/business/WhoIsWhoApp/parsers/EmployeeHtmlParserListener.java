package com.app.business.WhoIsWhoApp.parsers;

import com.app.business.WhoIsWhoApp.model.Employee;

import java.util.List;

public interface EmployeeHtmlParserListener {
    public void onUpdateHtmlParseProgress(String progressMessage);
    public void onEmployeeHtmlParseComplete(List<Employee> employeeList);
}
