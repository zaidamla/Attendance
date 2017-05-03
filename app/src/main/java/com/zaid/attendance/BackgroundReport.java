package com.zaid.attendance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Vaibhav on 4/25/2017.
 */

public class BackgroundReport extends AsyncTask<String,ReportGetSet,Void> {
    Context c;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    ArrayList<ReportGetSet> arrayList=new ArrayList<>();

    public BackgroundReport(Context c)
    {
        this.c=c;
        activity=(Activity)c;
    }

    //String json="https://zaidamla96.000webhostapp.com/android/report.php";
    String json="http://192.168.43.196:80/Attendance/report.php";
   // String json="http://192.168.1.5:80/Attendance/report.php";

    @Override
    protected void onPreExecute() {

        recyclerView=(RecyclerView)activity.findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new adapterReport(arrayList);
        recyclerView.setAdapter(adapter);
        progressDialog=new ProgressDialog(c);
        progressDialog.setTitle("Please Wait.");
        progressDialog.setMessage("loading..");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    @Override
    protected Void doInBackground(String... params) {
        try {

            URL url= new URL(json+"?subject="+params[0]+"&date="+params[1]+"&stream_report="+params[2]);
            Log.v("url",url.toString());
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader((inputStream)));
            StringBuilder stringBuilder=new StringBuilder();
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            String json_string=stringBuilder.toString().trim();
            Log.v("jsonstring",json_string);
            JSONObject jsonObject=new JSONObject(json_string);
            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            while(count<jsonArray.length())
            {
                JSONObject jo=jsonArray.getJSONObject(count);
                count++;
                ReportGetSet reportGetSet=new ReportGetSet(jo.getInt("Rollno"),jo.getString("Name"),jo.getString("Status"));
                publishProgress(reportGetSet);
                Thread.sleep(1000);
            }







        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(ReportGetSet... values) {
        super.onProgressUpdate(values);
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
    }


}
