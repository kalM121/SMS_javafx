package com.example.studentmanagementsystem;

import java.sql.Date;

public class studentData {
    private Integer studId ;
    private String year;
    private String course;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birth;
    private String status;
    private String image;
    private Double firstsem;
    private Double secondsem;
    private Double finals;
    private String password;

    public studentData(Integer studId,String year, String course, String firstName,String lastName,String gender,String status,String image,Date birth) {
        this.studId = studId;
        this.year = year;
        this.course = course;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.status = status;
        this.image = image;
        this.birth = birth;
    }



    public studentData(Integer studId, String year, String course, String firstName, String lastName,String gender ,String status) {
        this.studId=studId;
        this.year=year;
        this.course=course;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.status=status;

    }
    public studentData(Integer studId,String year,String course,double first_sem, double second_sem, double finals) {
        this.studId = studId;
        this.year = year;
        this.course = course;
        this.firstsem=first_sem;
        this.secondsem=second_sem;
        this.finals=finals;
    }


    public Integer getStudId() {

        return studId;
    }

    public String getYear() {

        return year;
    }

    public String getCourse() {

        return course;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getGender() {

        return gender;
    }

    public Date getBirth() {

        return birth;
    }

    public String getStatus() {

        return status;
    }

    public String getImage() {

        return image;
    }
    public Double getFirstsem(){
        return firstsem;
    }
    public Double getSecondsem(){
        return secondsem;
    }
    public Double getFinals(){
        return finals;
    }

    }









