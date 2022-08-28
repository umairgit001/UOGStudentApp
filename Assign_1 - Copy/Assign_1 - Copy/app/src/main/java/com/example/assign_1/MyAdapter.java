package com.example.assign_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private PersonalDetails[] listData;

    public MyAdapter(PersonalDetails[] listData) {
        this.listData = listData;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PersonalDetails myListData = listData[position];
        holder.Name.setText(listData[position].getStudentName());
        holder.RollNo.setText(listData[position].getRollNo());
        holder.noOfCredits.setText(listData[position].getNoOfCredits());
        holder.Faculty.setText(listData[position].getFaculty());
        holder.Phone.setText(listData[position].getPhone());
        holder.Email.setText(listData[position].getEmail());
        holder.ProgramEnrolled.setText(listData[position].getProgramEnrolled());
        holder.PresentSemester.setText(listData[position].getPresentSemester());
        holder.TotalFee.setText(String.valueOf(listData[position].getTotalFee()));
        holder.Per25.setText(String.valueOf(listData[position].getPer25()));
        holder.Per75.setText(String.valueOf(listData[position].getPer75()));


    }

    @Override
    public int getItemCount() {
        return listData.length;
    }


}
