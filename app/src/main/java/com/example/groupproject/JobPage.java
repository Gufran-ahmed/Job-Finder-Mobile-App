package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/*
    JobPage
    Author: Archer Zhou
 */

public class JobPage extends AppCompatActivity implements View.OnClickListener{

    FirebaseDatabase database = null;
    DatabaseReference jobRef = null;
    DatabaseReference userRef = null;
    DatabaseReference jobDetail = null;
    DatabaseReference userDetail = null;
    String jobName,userName;
    Job temp;
    List<String> EmployeeRating = new ArrayList<>();
    int JobPoint = 0;
    int counter = 0;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_page);

        Button back2jobs = findViewById(R.id.back2jobs);
        Button takeJob = findViewById(R.id.takeJob);

        back2jobs.setOnClickListener(this);
        takeJob.setOnClickListener(this);

        this.initializeDatabase();

        //get the job name from HomeFragment and get the username from last intent
        Intent intent = getIntent();
        jobName = intent.getStringExtra("JOB_NAME");
        userName = intent.getStringExtra("USER_NAME");
        jobDetail = jobRef.child(jobName);
        userDetail = userRef.child(userName);

        setJobDetails();
    }

    //initialize the database
    protected void initializeDatabase() {

        database = FirebaseDatabase.getInstance();
        jobRef = database.getReference("Jobs");
        userRef = database.getReference("User");

    }


    //set the textview with proper input and set certain value to local variables
    protected void setJobDetails(){


        ValueEventListener jobSetUp = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                temp = snapshot.getValue(Job.class);
                System.out.println(temp);
                setJobDescription(temp.getDescription());
                setJobPoster(temp.getPostedBy());
                setJobStartDate(temp.getStartDate());
                setJobStatus(temp.getStatus());
                setJobType(temp.getTypes());

                setPositions(String.valueOf(temp.getPositions()));
                position = temp.getPositions();

                setPoints(String.valueOf(temp.getPoints()));
                JobPoint = temp.getPoints();

                GenericTypeIndicator<List<String>> genericTypeIndicator = new GenericTypeIndicator<List<String>>(){};
                List<String> tempEmployee = snapshot.child("Employees").getValue(genericTypeIndicator);
                EmployeeRating = snapshot.child("Employees").getValue(genericTypeIndicator);
                setEmployeeAndRating(EmployeeAndRating(tempEmployee));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        jobDetail.addListenerForSingleValueEvent(jobSetUp);


    }


    //method to set job description
    protected void setJobDescription(String jobDescription){
        TextView descriptionView = findViewById(R.id.descriptionView);
        descriptionView.setText(jobDescription);
    }

    //method to set job poster
    protected void setJobPoster(String jobPoster){
        TextView PosterView = findViewById(R.id.PosterView);
        PosterView.setText("Poster: " + jobPoster);
    }

    //method to set job star date
    protected void setJobStartDate(String jobStartDate){
        TextView startDateView = findViewById(R.id.startDateView);
        startDateView.setText("StartDate: " + jobStartDate);
    }

    //method to set job status
    protected void setJobStatus(String jobStatus){
        TextView statusView = findViewById(R.id.statusView);
        statusView.setText("Status: " + jobStatus);
    }

    //method to set job type
    protected void setJobType(String jobType){
        TextView jobTypeView = findViewById(R.id.jobTypeView);
        jobTypeView.setText("Type: " + jobType);
    }

    //method to set job positions
    protected void setPositions(String positions){
        TextView jobTypeView = findViewById(R.id.employeeNumber);
        jobTypeView.setText("Positions: " + positions);
    }

    //method to set job points
    protected void setPoints(String Points){
        TextView jobTypeView = findViewById(R.id.jobValue);
        jobTypeView.setText("Points: " + Points);
    }

    //method to set employee and rating
    protected void setEmployeeAndRating(String EmployeeNRating){
        TextView jobTypeView = findViewById(R.id.employeeMember);
        jobTypeView.setText(EmployeeNRating);
    }

    //method to set error message to the status label
    protected void setStatus(String error){
        TextView jobTypeView = findViewById(R.id.statusLabelForJob);
        jobTypeView.setText(error);
    }

    //method to set employee with a certain format
    public String EmployeeAndRating(List<String> Employee){
        String answer = "";
        for(int i = 0 ; i < Employee.size() ; i++){
            answer = answer + Employee.get(i) + "\n";
        }
        return answer;
    }

    //back to home page and send the username back
    protected void switch2HomeWindow() {

        Intent SwitchIntent = new Intent(this, Homepage.class);
        SwitchIntent.putExtra("USER_NAME", userName);
        startActivity(SwitchIntent);

    }

    //change the job status to doing
    protected void changeJobStatus(){
        DatabaseReference jobStatus = jobDetail.child("status");
        DatabaseReference userRating = userDetail.child("rating");
        DatabaseReference jobEmployee = jobDetail.child("Employees");
        DatabaseReference jobPosition = jobDetail.child("positions");


        //set status to doing
        jobStatus.setValue("Doing");


        //if position > 0 and counter < 1 take the job
        userRating.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(counter < 1){
                    if(position > 0){
                        int temp = snapshot.getValue(Integer.class);
                        int testTemp = temp;
                        temp += JobPoint;
                        userRating.setValue(temp);

                        String tempString = userName + " Rating: " +temp;
                        String testString = userName + " Rating: " +testTemp;

                        //after take the job successfully, increase the counter by one and decrease the position by one
                        if(!EmployeeRating.contains(testString)){
                            EmployeeRating.add(tempString);
                            counter++;
                            position--;
                            jobEmployee.setValue(EmployeeRating);
                            jobPosition.setValue(position);
                        }

                        setJobDetails();
                    }


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //if position < 0 or counter > 1, give error message
            case R.id.takeJob:
                if(position > 0){
                    if(counter < 1){
                        changeJobStatus();
                    }
                    else{
                        setStatus("You have taken this job");
                    }
                }
                else{
                    setStatus("There is no position");
                }

                break;


            case R.id.back2jobs:
                switch2HomeWindow();

                break;
        }

    }
}