package com.zaid.attendance;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Attendance extends Fragment {


    public Attendance() {
        // Required empty public constructor
    }
    String stream_select;
Spinner subject,stream;
    Button DateButton,Submit;
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_attendance, container, false);
        stream=(Spinner)view.findViewById(R.id.stream);
        subject=(Spinner)view.findViewById(R.id.subject);
        DateButton=(Button)view.findViewById(R.id.DateButton);
        Submit=(Button)view.findViewById(R.id.attendanceSubmit);

        final Calendar myCalendar=Calendar.getInstance();

        DateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                int mYear=myCalendar.get(Calendar.YEAR);
                int mMonth=myCalendar.get(Calendar.MONTH);
                int mDay=myCalendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog= new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DateButton.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, mYear,mMonth,mDay);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "submit", Toast.LENGTH_SHORT).show();
//                backgroundTask backgroundTask=new backgroundTask(getContext());
//                backgroundTask.execute();
                stream_select=stream.getSelectedItem().toString();

                Intent i=new Intent(getContext(),ListActivity.class);
                i.putExtra("stream",stream_select);
                i.putExtra("subj", subject.getSelectedItem().toString());
                i.putExtra("date",DateButton.getText());
                getContext().startActivity(i);
                String type="class";
//              Toast.makeText(getContext(), stream_select, Toast.LENGTH_SHORT).show();




            }
        });

        return view;
    }


}
