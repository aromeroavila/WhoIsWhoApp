package com.app.business.WhoIsWhoApp.parsers;

import android.os.AsyncTask;
import com.app.business.WhoIsWhoApp.model.Employee;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeHtmlParser extends AsyncTask<String, String, Void> {

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
            int employeeElementsNumber = employeeElements.size();
            int current = 1;

            for (Element employeeElement : employeeElements) {
                name = title = photoUrl = bio = "";
                photoUrl = employeeElement.select("div img").attr("src");
                name = employeeElement.select("h3").text();
                title = employeeElement.select("p:first-child").text();
                bio = employeeElement.select("p.user-description").text();

                mEmployeeList.add(new Employee(name, title, photoUrl, bio));

                publishProgress(getUpdateMessage(current, employeeElementsNumber));
                current++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... progressMessages) {
        mListener.onUpdateHtmlParseProgress(progressMessages[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        mListener.onEmployeeHtmlParseComplete(mEmployeeList);
    }

    private String getUpdateMessage(int currentEmployee, int totalEmployees) {
        return currentEmployee + " of " + totalEmployees + " employees parsed";
    }
}
