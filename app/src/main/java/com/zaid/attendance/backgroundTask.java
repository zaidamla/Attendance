package com.zaid.attendance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Vaibhav on 4/18/2017.
 */

public class backgroundTask extends AsyncTask<String,student_list,Void> {
    Context ct;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    ArrayList<student_list> arrayList=new ArrayList<>();

    public backgroundTask(Context ct)
    {
        this.ct=ct;
        activity=(Activity)ct;
    }

    String json_string="https://zaidamla96.000webhostapp.com/android/select.php";

    @Override
    protected void onPreExecute() {

//        recyclerView=(RecyclerView)activity.findViewById(R.id.rv);
//        layoutManager= new LinearLayoutManager(ct);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);
//        adapter=new myadapter(arrayList);
//        recyclerView.setAdapter(adapter);
        progressDialog=new ProgressDialog(ct);
        progressDialog.setTitle("Please Wait.");
        progressDialog.setMessage("loading..");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url= new URL(json_string+"?stream="+params[0]);
            Log.v("url",url.toString());
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line;

            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }
            httpURLConnection.disconnect();
            json_string=stringBuilder.toString().trim();
            JSONObject jsonObject=new JSONObject(json_string);
            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            while (count<jsonArray.length())
            {
                JSONObject jO=jsonArray.getJSONObject(count);
                count++;
                student_list student_list=new student_list(jO.getInt("Rollno"),jO.getString("Name"));
                publishProgress(student_list);
                Thread.sleep(1000);
            }


            Log.d("JSON_STRING",json_string);


        } catch (IOException | JSONException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(student_list... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        Log.v("Array","kgj");
        progressDialog.dismiss();
        Log.v("array", arrayList.get(0).getName());

    }

}
