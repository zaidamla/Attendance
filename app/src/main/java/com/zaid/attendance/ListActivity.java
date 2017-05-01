package com.zaid.attendance;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

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

public class ListActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button button;
    RecyclerView recyclerView;
    private String stream;
    private String subj;
    private String date;
    ArrayList<student_list> arrayList;
    private myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       /* button=(Button)findViewById(R.id.sub);*/
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        arrayList=new ArrayList<>();
        Bundle bundle=getIntent().getExtras();
        if(bundle.getString("stream")!=null)
            stream=bundle.getString("stream");

        if(bundle.getString("subj")!=null)
            subj=bundle.getString("subj");

        if(bundle.getString("date")!=null)
            date=bundle.getString("date");

        backgroundTask backgroundTask=new backgroundTask(ListActivity.this);
        backgroundTask.execute(stream);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(layoutManager);


        toolbar=(Toolbar)findViewById(R.id.toolBar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.submit){
            for(int i=0;i<arrayList.size();i++){
                BackgroundAttendance backgroundAttendance=new BackgroundAttendance(this);
                backgroundAttendance.execute(subj,String.valueOf(arrayList.get(i).getRollno()),date,arrayList.get(i).getStatus());
            }
            Toast.makeText(this, "submit", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public class backgroundTask extends AsyncTask<String,student_list,Void> {
        Context ct;
        Activity activity;
        ProgressDialog progressDialog;

        public backgroundTask(Context ct)
        {
            this.ct=ct;
            activity=(Activity)ct;
        }

        String json_string="http://192.168.0.8:80/Attendance/select.php";

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
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter=new myadapter(arrayList,ct);
            recyclerView.setAdapter(adapter);
            progressDialog.dismiss();
        }

    }
}
