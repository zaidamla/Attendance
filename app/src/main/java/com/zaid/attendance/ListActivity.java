package com.zaid.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<student_list> stdlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        toolbar=(Toolbar)findViewById(R.id.toolBar);

        //setSupportActionBar(toolbar);
        backgroundTask backgroundTask=new backgroundTask(ListActivity.this);
        backgroundTask.execute();
    }
}
