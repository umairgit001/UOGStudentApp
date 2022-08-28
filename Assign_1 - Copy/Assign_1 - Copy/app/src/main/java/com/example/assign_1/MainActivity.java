package com.example.assign_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.PrecomputedText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button delete, update;
    private Button addBtn;
    static ArrayList<PersonalDetails> myListData = new ArrayList<>();
    String name, roll, faculty, credits, email, phone, program, semester, fee;

//    EditText stdname, stdroll, stdcredits, stdemail, stdphone, stdprogram, stdsemester,
//    stdfaculty; int stdfee;

    Database dbhelper;
//    String dbname, dbroll, dbfaculty, dbcredits, dbemail, dbphone, dbprogram, dbsemester;
//    int dbfee;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = findViewById(R.id.btnAdd);


//        stdname = findViewById(R.id.txtname);
//        stdroll = findViewById(R.id.txtroll);
//        stdcredits = findViewById(R.id.txtcredits);
//        stdemail = findViewById(R.id.txtemail);
//        stdphone = findViewById(R.id.txtphone);
//        stdprogram = findViewById(R.id.txtprogram);
//        stdsemester = findViewById(R.id.txtsemester);
//        stdfaculty = findViewById(R.id.txtfaculty);
//        stdfee = findViewById(R.id.txtfee);

        EditText txtName =  findViewById(R.id.txtname);
        EditText txtRoll = findViewById(R.id.txtroll);
        EditText txtProgram = findViewById(R.id.txtprogram);
        EditText txtFaculty = findViewById(R.id.txtfaculty);
        EditText txtCredits = findViewById(R.id.txtcredits);
        EditText txtEmail = findViewById(R.id.txtemail);
        EditText txtPhone = findViewById(R.id.txtphone);
        EditText txtSemester = findViewById(R.id.txtsemester);
        EditText txtFee = findViewById(R.id.txtfee);

        name = getIntent().getStringExtra("name");
        roll = getIntent().getStringExtra("roll");
        faculty = getIntent().getStringExtra("faculty");
        credits = getIntent().getStringExtra("credit");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        fee = getIntent().getStringExtra("fee");
        program = getIntent().getStringExtra("program");
        semester = getIntent().getStringExtra("semester");

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                SaveDataPreference.writeListInPref(getApplicationContext(),myListData);
                startActivity(intent);
            }
        });


        dbhelper = new Database(this);
        delete = findViewById(R.id.btndelete);
        update = findViewById(R.id.btnupdate);


        myListData = (ArrayList<PersonalDetails>) SaveDataPreference.readListFromPref(this);

        if(myListData == null){
            myListData = new ArrayList<>();
        }
//        Log.d("ASD",preferences.getString("LK","NOT"));
        Log.d("ASD", String.valueOf(myListData.size()));


//        PersonalDetails[] myListData = new PersonalDetails[]{
//                new PersonalDetails("Muhammad Umair", "19011519-048",
//                        "Computer Science", "18", "19011519-048@uog.edu.pk",
//                        "03045085419", 35000, "Winter", "6th")
//        };




//        myListData.add(new PersonalDetails("Muhammad Umair", "19011519-048",
//                "Computer Science", "18", "19011519-048@uog.edu.pk",
//                "03045085419", 35000, "Winter", "6th"));


        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        if (name != null) {

            PersonalDetails personalDetails = new PersonalDetails(name, roll, faculty, credits, email, phone, Integer.parseInt(String.valueOf(fee)), program, semester);
            Log.d("ASD", personalDetails.toString());
            myListData.add(personalDetails);
        }
        //Sending data to custom Adapter
        Log.d("ASD", String.valueOf(myListData.size()));
        PersonalDetails[] personalDetails = new PersonalDetails[myListData.size()];
        for (int i = 0; i < personalDetails.length; i++) {
            personalDetails[i] = myListData.get(i);
        }

       // SaveInstance(personalDetails);
        MyAdapter adapter = new MyAdapter(personalDetails);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        name = txtName.getText().toString();
        roll = txtRoll.getText().toString();
        faculty = txtFaculty.getText().toString();
        credits = txtCredits.getText().toString();
        email = txtEmail.getText().toString();
        phone = txtPhone.getText().toString();
        fee = txtFee.getText().toString();
        program = txtProgram.getText().toString();
        semester = txtSemester.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean checkUpdateData = dbhelper.updateData(name, roll, faculty, credits, email, phone, program, semester, fee );
                if (checkUpdateData == true){
                    Toast.makeText(MainActivity.this, "Data Updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data NOT Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkDeleteData = dbhelper.deleteData(name);
                if (checkDeleteData == true){
                    Toast.makeText(MainActivity.this, "Data Deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data NOT Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}