package com.app.business.WhoIsWhoApp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.app.business.WhoIsWhoApp.model.Employee;

import java.util.List;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {

    private int mResourceId;
    private List<Employee> mEmployees;


    public EmployeeListAdapter(Context context, int resource, List<Employee> employees) {
        super(context, resource);

        mResourceId = resource;
        mEmployees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return mEmployees.size();
    }
}
