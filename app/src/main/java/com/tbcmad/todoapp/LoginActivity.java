package com.tbcmad.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText username;
    EditText password;
    private static  final String TAG = "ToDoActivity";
    int valid=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.login_username);
        password = (EditText)findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_activity_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().length()==0)
                {
                    valid=0;
                    username.requestFocus();
                    username.setError("Username is required!");
                    return;
                }
                if (password.getText().length()==0)
                {
                    valid=0;
                    password.requestFocus();
                    password.setError("Password is Required");
                    return;
                }
                SharedPreferences preference = getApplicationContext().getSharedPreferences("todo_pref",  0);                 SharedPreferences.Editor editor = preference.edit();
                editor.putBoolean("authentication",true);
                editor.commit();
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();





            }
        });
    }
}