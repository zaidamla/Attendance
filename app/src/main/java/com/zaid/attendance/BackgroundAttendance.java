package com.zaid.attendance;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Vaibhav on 4/30/2017.
 */

public class BackgroundAttendance extends AsyncTask<String,Void,String> {
    Context con;
    BackgroundAttendance(Context c)
    {
        con=c;
    }
    String attendance="http://192.168.0.8:80/Attendance/attendance.php";
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            String rollno=params[1];
            String status=params[3];
            String sub=params[0];
            String date=params[2];

            URL url=new URL(attendance+"?subj="+sub+"&rollno="+rollno+"&date="+date+"&status="+status);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("sub","UTF-8")+"="+URLEncoder.encode(sub,"UTF-8")+"&"
                    +URLEncoder.encode("rollno","UTF-8")+"="+URLEncoder.encode(rollno,"UTF-8")+"&"
                    +URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8")+"&"
                    +URLEncoder.encode("status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputstream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!=null){
                result+=line;
            }
            bufferedReader.close();
            inputstream.close();
            httpURLConnection.disconnect();
            return result;







        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
