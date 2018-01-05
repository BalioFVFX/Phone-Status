package com.example.baliofvfx.phonestatus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {


    private EditText passwordEditText;

    public void sendPassword(View view){
        if(passwordEditText.toString() != ""){
            RequestManager.sendPassword(MainActivity.email, passwordEditText.getText().toString());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

    }
}
