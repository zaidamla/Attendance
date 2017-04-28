package com.zaid.attendance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class Student extends Fragment {
    EditText rollno,eno,name, contact, parent;
    Button submitStudent;
    Spinner streamstudent;
    String str_rollno, str_eno, str_name, str_contact, str_parent;

    public Student() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_student,container,false);
        rollno=(EditText)view.findViewById(R.id.rollno);
        eno=(EditText)view.findViewById(R.id.eno);
        name=(EditText)view.findViewById(R.id.name);
        contact=(EditText)view.findViewById(R.id.contact);
        parent=(EditText)view.findViewById(R.id.parent);
        submitStudent=(Button) view.findViewById(R.id.submitStudent);
        streamstudent=(Spinner) view.findViewById(R.id.streamstudent);

        submitStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OnReg();
            }
        });

        return view;
    }
    public void OnReg(){
        String str_rollno=rollno.getText().toString();
        String str_eno=eno.getText().toString();
        String str_name=name.getText().toString();
        String str_contact=contact.getText().toString();
        String str_parent=parent.getText().toString();
        String str_streamstudent=streamstudent.getSelectedItem().toString();
        String type="insert";
        BackgroundLogin Backgroundlogin = new BackgroundLogin(getContext());
        Backgroundlogin.execute(type,str_rollno,str_eno,str_name,str_contact,str_parent,str_streamstudent);

    }

}
