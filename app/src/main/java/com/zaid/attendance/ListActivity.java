package com.zaid.attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<student_list> stdlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=(RecyclerView)findViewById(R.id.rv);

        dummydata();

        myadapter adapter=new myadapter(this,stdlist);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void dummydata() {
        stdlist=new ArrayList<>();
        for(int i=0;i<10;i++){
            student_list std = new student_list();
            std.setRollno(i+1);
            std.setName("Name "+(i+1));
            stdlist.add(std);
        }
    }
}
