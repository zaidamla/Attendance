package com.zaid.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class ReportList extends AppCompatActivity {


    private String subject;
    private String date;
    private String stream_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        Bundle bundle=getIntent().getExtras();


//        if(!bundle.getString("stream").isEmpty())
//            stream_report=bundle.getString("stream");

        if(!bundle.getString("subject").isEmpty())
            subject=bundle.getString("subject");

        if(!bundle.getString("date").isEmpty())
            date=bundle.getString("date");

        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, subject, Toast.LENGTH_SHORT).show();

        BackgroundReport backgroundReport=new BackgroundReport(ReportList.this);
        backgroundReport.execute(subject,date);
    }
}
