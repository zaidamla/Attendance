package com.zaid.attendance;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Najma on 20-03-2017.
 */

public class BackgroundLogin extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    BackgroundLogin (Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        //String login_url = "https://zaidamla96.000webhostapp.com/android/login.php";
        String login_url = "http://192.168.0.22:80/Attendance/login.php";
        String insert_url = "http://192.168.0.22:80/Attendance/insert.php";
        //String insert_url = "https://zaidamla96.000webhostapp.com/android/insert.php";
        if(type.equals("login")){
            try {
                //Toast.makeText(context, "Login..", Toast.LENGTH_SHORT).show();
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("insert")){
            try {
                String rollno = params[1];
                String eno = params[2];
                String name = params[3];
                String contact = params[4];
                String parent = params[5];
                String streamstudent = params[6];
                URL url = new URL(insert_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("rollno","UTF-8")+"="+URLEncoder.encode(rollno,"UTF-8")+"&"
                        +URLEncoder.encode("eno","UTF-8")+"="+URLEncoder.encode(eno,"UTF-8")+"&"
                        +URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("contact","UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")+"&"
                        +URLEncoder.encode("parent","UTF-8")+"="+URLEncoder.encode(parent,"UTF-8")+"&"
                        +URLEncoder.encode("streamstudent","UTF-8")+"="+URLEncoder.encode(streamstudent,"UTF-8");
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        if(result.contains("Login")){
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }
        else if (result.contains("Insert success")){
            Toast.makeText(context, "Student added", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setMessage("Student added successfully");
            builder.setCancelable(false);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();



        }
        else if (result.contains("Insert fail")){
            Toast.makeText(context, "Insert Data", Toast.LENGTH_SHORT).show();
        }
        else if (result.contains("Failed")){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Wrong Username/Password", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
