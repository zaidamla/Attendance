package com.zaid.attendance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Najma on 24-04-2017.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.RecyclerViewHolder>{
    ArrayList<report_list> arrayList = new ArrayList<>();
    public  ReportAdapter(ArrayList<report_list> arrayList)
    {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        report_list reportList = arrayList.get(position);
        holder.rroll.setText(Integer.toString(report_list.getRollno()));
        holder.Name

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        TextView rollno,Name,Status;
        public RecyclerViewHolder(View view){
            super(view);
            rollno=(TextView)view.findViewById(R.id.rroll);
            Name=(TextView)view.findViewById(R.id.rName);
            Status=(TextView)view.findViewById(R.id.rstatus);
        }
    }
}
