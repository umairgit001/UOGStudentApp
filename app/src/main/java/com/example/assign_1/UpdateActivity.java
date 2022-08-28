package com.example.assign_1;

import static android.content.SharedPreferences.*;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

import javax.xml.parsers.SAXParser;

public class UpdateActivity extends AppCompatActivity {
    EditText stdname, stdroll, stdcredits, stdemail, stdphone, stdprogram, stdsemester,
    stdfaculty, stdfee;
    String name, roll, faculty, credits, email, phone, program, semester, fee;
    Button update, delete;

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
        setContentView(R.layout.activity_update);


        stdname = findViewById(R.id.txtname2);
        stdroll = findViewById(R.id.txtroll2);
        stdfaculty = findViewById(R.id.txtfaculty2);
        stdcredits = findViewById(R.id.txtcredits2);
        stdemail = findViewById(R.id.txtemail2);
        stdphone = findViewById(R.id.txtphone2);
        stdfee = findViewById(R.id.txtfee2);
        stdprogram = findViewById(R.id.txtprogram2);
        stdsemester = findViewById(R.id.txtsemester2);

        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);


        getIntentData();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler db = new DBHandler(UpdateActivity.this);
                db.updateData(name, roll, faculty, credits, email, phone, fee, program, semester);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startForResult.launch(intent);

        }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
          }
        });

    }

    void getIntentData(){

        if (getIntent().hasExtra("name") && getIntent().hasExtra("roll")
                && getIntent().hasExtra("faculty") && getIntent().hasExtra("credits")
                && getIntent().hasExtra("email") && getIntent().hasExtra("phone")
                && getIntent().hasExtra("fee") && getIntent().hasExtra("program")
                && getIntent().hasExtra("semester")){

            name = getIntent().getStringExtra("name");
            roll = getIntent().getStringExtra("roll");
            faculty = getIntent().getStringExtra("faculty");
            credits = getIntent().getStringExtra("credits");
            email = getIntent().getStringExtra("email");
            phone = getIntent().getStringExtra("phone");
            fee = getIntent().getStringExtra("fee");
            program = getIntent().getStringExtra("program");
            semester = getIntent().getStringExtra("semester");

            stdname.setText(name);
            stdroll.setText(roll);
            stdfaculty.setText(faculty);
            stdcredits.setText(credits);
            stdemail.setText(email);
            stdphone.setText(phone);
            stdfee.setText(fee);
            stdprogram.setText(program);
            stdsemester.setText(semester);


        }else {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Delete "+ name + " ?");
        alertDialog.setMessage("Are you sure ?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHandler db = new DBHandler(UpdateActivity.this);
                db.deleteData(roll);

                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startForResult.launch(intent);

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
       });
        alertDialog.create().show();

    }


}