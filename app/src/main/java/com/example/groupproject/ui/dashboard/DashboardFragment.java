package com.example.groupproject.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupproject.Job;
import com.example.groupproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/*
Dashboard Fragment
Author: Gufran
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {
    DatabaseReference JDatabase;
    private Button PostJob;
    private DashboardViewModel dashboardViewModel;
    List<String> Emp;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);


        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        PostJob = root.findViewById(R.id.SubmitJob);
        PostJob.setOnClickListener(this::onClick);
        Emp = new ArrayList<>();

        return root;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JDatabase = FirebaseDatabase.getInstance().getReference();

        SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String userName = sp.getString("userName", "");
        TextView s1 = getActivity().findViewById(R.id.Username);
        s1.setText(userName);


        Spinner spin = getActivity().findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(myAdapter);

    }

    protected String getJobName() {
        EditText JobTitle = getActivity().findViewById(R.id.jobName);
        return JobTitle.getText().toString().trim();
    }

    protected String getDescription() {
        EditText Description = getActivity().findViewById(R.id.Desc);
        return Description.getText().toString().trim();
    }

    protected String getCurrentDate() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return date;
    }

    protected String getTypeOfJob() {
        Spinner spin = getActivity().findViewById(R.id.spinner1);
        String spinnerValue = spin.getSelectedItem().toString();
        return spinnerValue;
    }

    protected String getLongitude() {
        EditText Longitude = getActivity().findViewById(R.id.Longitude);
        return Longitude.getText().toString().trim();
    }

    protected String getLat() {
        EditText Lat = getActivity().findViewById(R.id.Latitude);
        return Lat.getText().toString().trim();
    }

    protected String getNumOfApplications() {
        EditText Applications = getActivity().findViewById(R.id.Positions);
        return (Applications.getText().toString());
    }

    protected String getRatingPoints() {
        EditText Rating = getActivity().findViewById(R.id.Points);
        return (Rating.getText().toString());
    }

    protected boolean isEmptyJob(String Job) {
        return Job.isEmpty();
    }

    protected boolean isEmptyDesc(String desc) {
        return desc.isEmpty();
    }


    public String SaveData() {
        TextView s1 = getActivity().findViewById(R.id.Username);
        return s1.getText().toString();
    }

    protected void setStatusMessage2(String message) {
        TextView statusLabel = getActivity().findViewById(R.id.statusLabel2);
        statusLabel.setText(message);
    }
    protected boolean isValidLat(String Lat){
        return Lat.matches("^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$");
    }
    protected boolean isValidLong(String Long){
        return Long.matches("^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$");
    }

    public List EmployeeListCreation(View v) {
        Emp.add("Employees:");
        return Emp;

    }


    @Override
    public void onClick(View v) {

        String Job = getJobName();
        String Desc = getDescription();
        String type = getTypeOfJob();
        String Date = getCurrentDate();
        String Status = "Available";
        String PostedBy = SaveData();
        String Long = getLongitude();


        String errorMessage = new String();


        if (isEmptyJob(Job)) {
            errorMessage = getResources().getString(R.string.Empty_Job_Name);
        } else {
            if (isEmptyDesc(Desc)) {
                errorMessage = getResources().getString(R.string.Empty_Desc);
            } else {
                if (getNumOfApplications().isEmpty()) {
                    errorMessage = getResources().getString(R.string.Empty_Positions);
                } else {
                    if (getLongitude().isEmpty()){
                        errorMessage = getResources().getString(R.string.Longitude);
                    } else {
                        if (getLat().isEmpty()) {
                            errorMessage = getResources().getString(R.string.Latitude);
                        } else {
                            if (getRatingPoints().isEmpty()) {
                                errorMessage = getResources().getString(R.string.Empty_Ratings);
                            }
                        }
                    }
                }
            }
        }


        if (errorMessage.isEmpty()) {
            int Positions = Integer.parseInt(getNumOfApplications());
            int rating = Integer.parseInt(getRatingPoints());
            double longitude = Double.parseDouble(getLongitude());
            double latitiude = Double.parseDouble(getLat());
            Emp = EmployeeListCreation(v);

            Job j = new Job(Desc, Emp, Positions, rating, PostedBy, Date, Status, type, latitiude,longitude);
            JDatabase.child(String.valueOf("Jobs")).child(Desc).setValue(j);
            JDatabase.child(String.valueOf("Jobs")).child(Desc).child("Employees").setValue(Emp);
            setStatusMessage2("");
            Toast.makeText(getActivity(), "Job Posted", Toast.LENGTH_SHORT).show();
        } else {
            setStatusMessage2(errorMessage);
        }

    }
}