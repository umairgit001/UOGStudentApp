package com.example.assign_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_items);

        Database db;
        db = new Database(this);
        //Check the code
        Button btnAdd = findViewById(R.id.addDetails);
        EditText txtName = findViewById(R.id.txtname);
        EditText txtRoll = findViewById(R.id.txtroll);
        EditText txtProgram = findViewById(R.id.txtprogram);
        EditText txtFaculty = findViewById(R.id.txtfaculty);
        EditText txtCredits = findViewById(R.id.txtcredits);
        EditText txtEmail = findViewById(R.id.txtemail);
        EditText txtPhone = findViewById(R.id.txtphone);
        EditText txtSemester = findViewById(R.id.txtsemester);
        EditText txtFee = findViewById(R.id.txtfee);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                String name, roll, faculty, credits, email, phone, program, semester;
                int fee;
                name = txtName.getText().toString().trim();
                roll = txtRoll.getText().toString().trim();
                faculty = txtFaculty.getText().toString().trim();
                credits = txtCredits.getText().toString().trim();
                email = txtEmail.getText().toString().trim();
                phone = txtPhone.getText().toString().trim();
                fee = Integer.parseInt(txtFee.getText().toString().trim());
                program = txtProgram.getText().toString().trim();
                semester = txtSemester.getText().toString().trim();


                new PersonalDetails(name, roll, faculty, credits, email, phone,
                        fee, program, semester);
                 */

                /*
                *  name = getIntent().getStringExtra("name");
        roll = getIntent().getStringExtra("roll");
        faculty = getIntent().getStringExtra("faculty");
        credits = getIntent().getStringExtra("credit");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        fee = getIntent().getStringExtra("fee");
        program = getIntent().getStringExtra("program");
        semester = getIntent().getStringExtra("semester");*/

                String name, roll, faculty, credits, email, phone, program, semester;
                String fee;
                name = txtName.getText().toString();
                roll = txtRoll.getText().toString();
                faculty = txtFaculty.getText().toString();
                credits = txtCredits.getText().toString();
                email = txtEmail.getText().toString();
                phone = txtPhone.getText().toString();
                fee = txtFee.getText().toString();
                program = txtProgram.getText().toString();
                semester = txtSemester.getText().toString();

                Boolean checkInsertData = db.insertData(name,roll, faculty, credits, email, phone, program, semester, fee );
                if (checkInsertData == true){
                    Toast.makeText(DataActivity.this, "New Data Inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(DataActivity.this, "New Data NOT Inserted", Toast.LENGTH_SHORT).show();
                }


                Intent intent = new Intent(DataActivity.this, MainActivity.class);
                intent.putExtra("name", txtName.getText().toString());
                intent.putExtra("roll", txtRoll.getText().toString());
                intent.putExtra("faculty", txtFaculty.getText().toString());
                intent.putExtra("credits", txtCredits.getText().toString());
                intent.putExtra("email", txtEmail.getText().toString());
                intent.putExtra("phone", txtPhone.getText().toString());
                intent.putExtra("fee", txtFee.getText().toString());
                intent.putExtra("program", txtProgram.getText().toString());
                intent.putExtra("semester", txtSemester.getText().toString());



                startActivity(intent);
            }
        });
    }
}
