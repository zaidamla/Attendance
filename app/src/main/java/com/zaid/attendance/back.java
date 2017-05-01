package com.zaid.attendance;

import android.content.Context;
import android.os.AsyncTask;
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
 * Created by Vaibhav on 4/30/2017.
 */

public class back  extends AsyncTask<String,student_list,ArrayList<student_list>> {
    Context ctx;
    private ArrayList<student_list> std_list=new ArrayList<>();

    public back(Context c)
    {
        ctx=c;
    }

    String json_string="https://192.168.0.25:80/Attendance/select.php";

    @Override
    protected ArrayList<student_list> doInBackground(String... params) {

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
                std_list.add(student_list);
                Thread.sleep(1000);
            }


            Log.d("JSON_STRING",json_string);


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
    protected void onPostExecute(ArrayList<student_list> student_lists) {
        super.onPostExecute(student_lists);
        list(student_lists);
    }

    private ArrayList<student_list> list(ArrayList<student_list> list){
        return std_list;
    }
}
