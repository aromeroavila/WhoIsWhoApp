package com.app.business.WhoIsWhoApp.parsers;

import com.app.business.WhoIsWhoApp.model.Employee;

import java.util.List;

public interface EmployeeHtmlParserListener {
    public void onEmployeeHtmlParseComplete(List<Employee> employeeList);
    public void onParseError(String reason);
}
