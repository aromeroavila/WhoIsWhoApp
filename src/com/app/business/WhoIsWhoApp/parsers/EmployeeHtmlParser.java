package com.app.business.WhoIsWhoApp.parsers;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.app.business.WhoIsWhoApp.model.Employee;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeHtmlParser extends AsyncTask<String, Void, Void> {

    private EmployeeHtmlParserListener mListener;
    private List<Employee> mEmployeeList;

    public EmployeeHtmlParser(EmployeeHtmlParserListener listener) {
        mListener = listener;
        mEmployeeList = new ArrayList<Employee>();
    }

    @Override
    protected Void doInBackground(String... urls) {
        String url = urls[0];

        try {
            Document document = Jsoup.connect(url).get();
            Elements employeeElements = document.select("div.col.col2");
            String name, title, photoUrl, bio;

            for (Element employeeElement : employeeElements) {
                photoUrl = employeeElement.select("div img").attr("src");
                name = employeeElement.select("h3").text();
                title = employeeElement.select("p:first-of-type").text();
                bio = employeeElement.select("p.user-description").text();

                // All the fields are required. This avoid parsing other elements that match the same
                // css selector but are not of our interest.
                if (!TextUtils.isEmpty(photoUrl) && !TextUtils.isEmpty(name)
                        && !TextUtils.isEmpty(title) && !TextUtils.isEmpty(bio)) {
                    mEmployeeList.add(new Employee(name, title, photoUrl, bio));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            mListener.onParseError(e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mListener.onEmployeeHtmlParseComplete(mEmployeeList);
    }
}
