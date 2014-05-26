package com.app.business.WhoIsWhoApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.business.WhoIsWhoApp.R;
import com.app.business.WhoIsWhoApp.model.Employee;
import com.squareup.picasso.Picasso;

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

            ImageView photoImageView = (ImageView) convertView.findViewById(R.id.employee_photo_i);
            downloadAndSetPhotoImage(photoImageView, employee.getmPhotoUrl());
            TextView nameTextView = (TextView) convertView.findViewById(R.id.employee_name_tv);
            nameTextView.setText(employee.getmName());
            TextView titleTextView = (TextView) convertView.findViewById(R.id.employee_title_tv);
            titleTextView.setText(employee.getmTitle());
            TextView bioTextView = (TextView) convertView.findViewById(R.id.employee_bio_tv);
            bioTextView.setText(employee.getmBiography());
        }

        return convertView;
    }

    private void downloadAndSetPhotoImage(ImageView photoImageView, String imageUrl) {
        int imageResize = (int) mContext.getResources().getDimension(R.dimen.image_resize);

        Picasso.with(mContext)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .resize(imageResize, imageResize)
                .centerCrop()
                .into(photoImageView);
    }

    @Override
    public int getCount() {
        return mEmployees.size();
    }
}
