package com.app.business.WhoIsWhoApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.business.WhoIsWhoApp.R;
import com.app.business.WhoIsWhoApp.model.Employee;

import java.util.List;

public class EmployeeListAdapter extends ArrayAdapter<Employee> {

    private Context mContext;
    private int mResourceId;
    private List<Employee> mEmployees;


    public EmployeeListAdapter(Context context, int resource, List<Employee> employees) {
        super(context, resource);

        mContext = context;
        mResourceId = resource;
        mEmployees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        convertView = inflater.inflate(mResourceId, parent, false);

        if (convertView != null) {
            Employee employee = mEmployees.get(position);

            ImageView photoImgeView = (ImageView) convertView.findViewById(R.id.employee_photo_i);
            // TODO use image downloader
            TextView nameTextView = (TextView) convertView.findViewById(R.id.employee_name_tv);
            nameTextView.setText(employee.getmName());
            TextView titleTextView = (TextView) convertView.findViewById(R.id.employee_title_tv);
            titleTextView.setText(employee.getmTitle());
            TextView bioTextView = (TextView) convertView.findViewById(R.id.employee_bio_tv);
            bioTextView.setText(employee.getmBiography());
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mEmployees.size();
    }
}
