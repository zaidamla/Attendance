package com.zaid.attendance;

import android.content.Context;
import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Vaibhav on 4/30/2017.
 */

public class BackgroundAttendance extends AsyncTask<Void,Void,Void> {
    Context con;
    BackgroundAttendance(Context c)
    {
        con=c;
    }
        String attendance="http://192.168.1.5:80/Attendance/attendance.php";
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            URL url=new URL(attendance);







        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
