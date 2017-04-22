package com.zaid.attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    private String stream;
    private String subj;
    private String date;
    //ArrayList<student_list> stdlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        toolbar=(Toolbar)findViewById(R.id.toolBar);

        Bundle bundle=getIntent().getExtras();
        if(!bundle.getString("stream").isEmpty())
           stream=bundle.getString("stream");

        if(!bundle.getString("subj").isEmpty())
            subj=bundle.getString("subj");

        if(!bundle.getString("date").isEmpty())
            date=bundle.getString("date");


        //Toast.makeText(this,"List "+ stream, Toast.LENGTH_SHORT).show();
        //setSupportActionBar(toolbar);
        backgroundTask backgroundTask=new backgroundTask(ListActivity.this);
        backgroundTask.execute(stream);
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
            Toast.makeText(this, "submit", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
