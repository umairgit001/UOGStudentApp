package com.example.assign_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assign_1.databinding.LoginPageBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHandler dbhandler;
    Button login, register;
    TextInputLayout username, rollno;
    Boolean isAllFieldsChecked = false;

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.equals(1)) {
                recreate();
            }

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        recyclerView = findViewById(R.id.RecyclerView);
        username = findViewById(R.id.nametext);
        rollno =  findViewById(R.id.rolltext);
        login = findViewById(R.id.loginstudent);
        register = findViewById(R.id.registerstudent);

        dbhandler = new DBHandler(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isAllFieldsChecked = CheckAllFields();
                if (isAllFieldsChecked) {
                    Toast.makeText(MainActivity.this, "Sign in Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startForResult.launch(intent);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
                startForResult.launch(intent);

            }
        });

    }
    private boolean CheckAllFields() {
        if (username.getEditText().toString().length()==0) {
            Toast.makeText(this,"This field is required",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (rollno.getEditText().toString().length() == 0) {
            Toast.makeText(this,"This field is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}