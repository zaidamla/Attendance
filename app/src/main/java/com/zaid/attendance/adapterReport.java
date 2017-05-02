package com.zaid.attendance;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vaibhav on 4/25/2017.
 */

public class adapterReport  extends RecyclerView.Adapter <adapterReport.RecyclerViewHolder>{

    ArrayList<ReportGetSet> arrayList = new ArrayList<>();
    private static final int type_head=0;
    private static final int type_list=1;


    public  adapterReport(ArrayList<ReportGetSet> arrayList)
    {
            this.arrayList=arrayList;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==type_head)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.reportheader_layout,parent,false);
            RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;

        }
        else if(viewType==type_list)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.report_layout,parent,false);
            RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        if(holder.viewtype==type_list)
        {
            ReportGetSet reportGetSet=arrayList.get(position-1);
            holder.Rollno.setText(Integer.toString(reportGetSet.getRollno()));
             holder.Name.setText(reportGetSet.getName());
          //  holder.Date.setText(reportGetSet.getDate());
            holder.Status.setText(reportGetSet.getSatus());
            Log.v("name",reportGetSet.getName());
        }



    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView Rollno,Name,Date,Status;
        int viewtype;
        public RecyclerViewHolder(View view,int viewtype)
        {
            super(view);
            if(viewtype==type_list)
            {
                Rollno=(TextView)view.findViewById(R.id.tvrollno);
                 Name=(TextView)view.findViewById(R.id.tvSname);
                //Date=(TextView)view.findViewById(R.id.tvDate);
                Status=(TextView)view.findViewById(R.id.tvStatus);
                this.viewtype=type_list;;

            }
            else if(viewtype==type_head)
            {
                this.viewtype=type_head;
            }


        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
        {
            return type_head;
        }
        return type_list;
    }
}
