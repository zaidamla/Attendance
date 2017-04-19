package com.zaid.attendance;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Najma on 11-04-2017.
 */

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {

    private Context context = null;
    private final ArrayList<student_list> stdlist;

    myadapter(ArrayList<student_list> stdlist){
        this.context=context;
        this.stdlist=stdlist;
    }



    @Override
    public myadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myadapter.MyViewHolder holder, int position) {
        student_list std=stdlist.get(position);
        holder.tvroll.setText(Integer.toString(std.getRollno()));
        holder.tvName.setText(std.getName());
    }

    @Override
    public int getItemCount() {
        return stdlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvroll;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tvName);
            tvroll=(TextView)itemView.findViewById(R.id.tvroll);
        }
    }
}
