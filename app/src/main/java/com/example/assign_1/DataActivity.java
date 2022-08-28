package com.example.assign_1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    DBHandler dbHandler;
    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.equals(1)) {
                recreate();
            }

        }
    });

    EditText txtName;
    EditText txtRoll;
    EditText txtProgram;
    EditText txtFaculty;
    EditText txtCredits;
    EditText txtEmail;
    EditText txtPhone;
    EditText txtSemester;
    EditText txtFee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_items);

        Button btnAdd = findViewById(R.id.addDetails);
        txtName = findViewById(R.id.txtname);
        txtRoll = findViewById(R.id.txtroll);
        txtProgram = findViewById(R.id.txtprogram);
        txtFaculty = findViewById(R.id.txtfaculty);
        txtCredits = findViewById(R.id.txtcredits);
        txtEmail = findViewById(R.id.txtemail);
        txtPhone = findViewById(R.id.txtphone);
        txtSemester = findViewById(R.id.txtsemester);
        txtFee = findViewById(R.id.txtfee);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                dbHandler = new DBHandler(DataActivity.this);

                insertData(txtName.getText().toString(),txtRoll.getText().toString(),
                        txtCredits.getText().toString(),txtFaculty.getText().toString(),
                        txtEmail.getText().toString(),txtPhone.getText().toString(),
                        txtFee.getText().toString(),txtProgram.getText().toString(),
                        txtSemester.getText().toString());

                startForResult.launch(intent);
            }
        });

    }

        private void insertData(String n,String r,String fac,String c,String e,
                               String ph,String fee,String pr,String ns){
                String res  =  new DBHandler(this).addStudent(n,r,fac,c,e,ph,fee,pr,ns);
                txtName.setText("");
                txtRoll.setText("");
                txtFaculty.setText("");
                txtCredits.setText("");
                txtEmail.setText("");
                txtPhone.setText("");
                txtFee.setText("");
                txtProgram.setText("");
                txtSemester.setText("");
                Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();


        }
}
