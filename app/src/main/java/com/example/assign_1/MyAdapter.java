package com.example.assign_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    Context context;
    Activity activity;
    ArrayList<PersonalDetails> listData;

    public MyAdapter(Activity activity, Context context, ArrayList<PersonalDetails> listData) {
        this.context = context;
        this.listData = listData;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Name;
        public TextView RollNo;
        public TextView Faculty;
        public TextView noOfCredits;
        public TextView Email;
        public TextView Phone;
        public TextView TotalFee;
        public TextView ProgramEnrolled;
        public TextView PresentSemester;
        public TextView Per25;
        public TextView Per75;

        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.listName);
            RollNo = itemView.findViewById(R.id.Rollno);
            Faculty = itemView.findViewById(R.id.Faculty);
            this.noOfCredits = itemView.findViewById(R.id.noofcredits);
            Email = itemView.findViewById(R.id.email);
            Phone = itemView.findViewById(R.id.Phone);
            TotalFee = itemView.findViewById(R.id.fee);
            Per25 = itemView.findViewById(R.id.per25);
            Per75 = itemView.findViewById(R.id.per75);
            ProgramEnrolled = itemView.findViewById(R.id.enrollment);
            PresentSemester = itemView.findViewById(R.id.Semester);
            layout = itemView.findViewById(R.id.outer_constraint);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem  = layoutInflater.inflate(R.layout.list_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final PersonalDetails myListData = listData.get(position);
        holder.Name.setText(myListData.getStudentName());
        holder.RollNo.setText(myListData.getRollNo());
        holder.noOfCredits.setText(myListData.getNoOfCredits());
        holder.Faculty.setText(myListData.getFaculty());
        holder.Phone.setText(myListData.getPhone());
        holder.Email.setText(myListData.getEmail());
        holder.ProgramEnrolled.setText(myListData.getProgramEnrolled());
        holder.PresentSemester.setText(myListData.getPresentSemester());
        holder.TotalFee.setText(String.valueOf(myListData.getTotalFee()));
        holder.Per25.setText(String.valueOf(myListData.getPer25()));
        holder.Per75.setText(String.valueOf(myListData.getPer75()));

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("name", String.valueOf(myListData.getStudentName()));
                intent.putExtra("roll", String.valueOf(myListData.getRollNo()));
                intent.putExtra("credits", String.valueOf(myListData.getNoOfCredits()));
                intent.putExtra("faculty", String.valueOf(myListData.getFaculty()));
                intent.putExtra("phone", String.valueOf(myListData.getPhone()));
                intent.putExtra("email", String.valueOf(myListData.getEmail()));
                intent.putExtra("program", String.valueOf(myListData.getProgramEnrolled()));
                intent.putExtra("semester", String.valueOf(myListData.getPresentSemester()));
                intent.putExtra("fee", String.valueOf(myListData.getTotalFee()));
                intent.putExtra("per25", String.valueOf(myListData.getPer25()));
                intent.putExtra("per75", String.valueOf(myListData.getPer75()));


                activity.startActivityForResult(intent, 1);
            }

        });

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }

}
