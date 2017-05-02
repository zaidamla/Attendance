package com.zaid.attendance;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText password, username;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        /*login = (Button) findViewById(R.id.login);*/
        /*login.setOnClickListener(new View.OnClickListener() {
           @Override
         public void onClick(View v) {

           Intent intent = new Intent(getApplicationContext(),MainActivity.class);
           startActivity(intent);
        }
        });*/
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().length()==0){
                    username.setError("Username is not entered");
                    username.requestFocus();
                }
                else if(password.getText().toString().trim().length()==0){
                    password.setError("Password is not entered");
                    password.requestFocus();
                }else
                    OnLogin(v);

            }
        });
    }


    public void OnLogin(View view) {
        String Username = username.getText().toString();
        String Password = password.getText().toString();
        String type = "login";
        BackgroundLogin Backgroundlogin = new BackgroundLogin(this);
        Backgroundlogin.execute(type, Username, Password);
        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            AlertDialog ag = new AlertDialog.Builder(this).create();
            ag.setMessage("Input Username Password");
        } /*else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }*/

    }
}
