package com.zaid.attendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Najma on 11-04-2017.
 */

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {

    private Context context = null;
    private final ArrayList<student_list> stdlist;
    Button button;
    String date;
    String subj;



    myadapter(ArrayList<student_list> stdlist, Context context){

        this.context=context;
        this.stdlist=stdlist;
        /*this.button=button;
        this.date=date;
        this.subj=subj;*/
    }

    @Override
    public myadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final myadapter.MyViewHolder holder, final int position) {
        final student_list std=stdlist.get(position);
        holder.tvroll.setText(Integer.toString(std.getRollno()));
        holder.tvName.setText(std.getName());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+std.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        stdlist.get(position).setStatus((holder.cb.isChecked()) ? "Present" : "Absent");
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    stdlist.get(holder.getAdapterPosition()).setStatus("Present");
                else
                    stdlist.get(holder.getAdapterPosition()).setStatus("Absent");

                /*notifyDataSetChanged();*/
                /*Toast.makeText(context, stdlist.get(holder.getAdapterPosition()).getStatus()+" "+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();*/
            }
        });

      /*  button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<stdlist.size();i++){
                    Toast.makeText(context, ""+stdlist.get(i).getStatus(), Toast.LENGTH_SHORT).show();
                    BackgroundAttendance backgroundAttendance=new BackgroundAttendance(context);
                    backgroundAttendance.execute(subj,String.valueOf(stdlist.get(i).getRollno()),date,stdlist.get(i).getStatus());
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return stdlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvroll;
        CheckBox cb;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tvName);
            tvroll=(TextView)itemView.findViewById(R.id.tvroll);
            cb=(CheckBox)itemView.findViewById(R.id.cb);
        }

    }
}
