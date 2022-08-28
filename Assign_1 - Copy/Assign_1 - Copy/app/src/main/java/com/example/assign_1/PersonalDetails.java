package com.example.assign_1;


import java.io.Serializable;

public class PersonalDetails implements Serializable {
    private String StudentName;
    private String RollNo;
    private String Faculty;
    private String noOfCredits;
    private String Email;
    private String Phone;
    private int TotalFee;
    private String ProgramEnrolled;
    private String PresentSemester;
    private int Per25;
    private int Per75;

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public String getNoOfCredits() {
        return noOfCredits;
    }

    public void setNoOfCredits(String noOfCredits) {
        this.noOfCredits = noOfCredits;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getTotalFee() {
        return TotalFee;
    }

    public void setTotalFee(int totalFee) {
        TotalFee = totalFee;
    }

    public String getProgramEnrolled() {
        return ProgramEnrolled;
    }

    public void setProgramEnrolled(String programEnrolled) {
        ProgramEnrolled = programEnrolled;
    }

    public String getPresentSemester() {
        return PresentSemester;
    }

    public void setPresentSemester(String presentSemester) {
        PresentSemester = presentSemester;
    }

    public int getPer25() {
        return Per25;
    }

    public void setPer25(int per25) {
        Per25 = this.TotalFee*25/100;
    }

    public int getPer75() {
        return Per75;
    }

    public void setPer75(int per75) {
        Per75 = this.TotalFee*75/100;
    }

    public PersonalDetails(String studentName, String rollNo, String faculty, String noOfCredits, String email, String phone, int totalFee, String programEnrolled, String presentSemester) {
        StudentName = studentName;
        RollNo = rollNo;
        Faculty = faculty;
        this.noOfCredits = noOfCredits;
        Email = email;
        Phone = phone;
        TotalFee = totalFee;
        ProgramEnrolled = programEnrolled;
        PresentSemester = presentSemester;
        setPer25(TotalFee);
        setPer75(TotalFee);
    }
}
