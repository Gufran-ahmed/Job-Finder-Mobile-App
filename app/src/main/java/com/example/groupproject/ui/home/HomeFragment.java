

package com.example.groupproject.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupproject.JobPage;
import com.example.groupproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/*
    HomeFragment
    Author: Archer Zhou
 */
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    FirebaseDatabase database = null;
    DatabaseReference jobRef = null;

    String userName;

    public ArrayList<String> JobNameList = new ArrayList<>();

    ListView listOfJobs;
    ArrayAdapter arrayAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initializeDatabase();

        Button searchButton = getActivity().findViewById(R.id.searchButton);

        searchButton.setOnClickListener(v -> searchJob(getSearchKeyWord()));

        listOfJobs = getActivity().findViewById(R.id.listOfJobs);

        arrayAdapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1,JobNameList);

        setDefaultJobs();

        listOfJobs.setOnItemClickListener((parent, view, position, id) -> openJobDetail(JobNameList.get(position)));

        Intent intent = getActivity().getIntent();
        userName = intent.getStringExtra("USER_NAME");

    }

    //initialize the database
    protected void initializeDatabase() {

        database = FirebaseDatabase.getInstance();
        jobRef = database.getReference("Jobs");


    }

    //get the content in the search bar
    protected String getSearchKeyWord(){
        EditText searchJobBar = getActivity().findViewById(R.id.searchJobBar);
        return searchJobBar.getText().toString().trim();

    }

    //search the jobs in the remote database
    protected void searchJob(String keyWord){


        if(keyWord.equals("")){
            if(JobNameList.get(0).equals("No such Job")){
                JobNameList.remove(0);
            }
            setDefaultJobs();
        }
        else{
            JobNameList.clear();
            jobRef.orderByKey().equalTo(keyWord).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot adSnapshot : snapshot.getChildren()){
                        JobNameList.add(adSnapshot.getKey());
                    }
                    if(JobNameList.isEmpty()){
                        JobNameList.add("No such Job");
                    }
                    listOfJobs.setAdapter(arrayAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    }

    //switch to jobpage and pass the jobname
    protected void openJobDetail(String jobName) {
        Intent switchIntent = new Intent(getActivity(), JobPage.class);
        if(!jobName.equals("No such Job")) {
            switchIntent.putExtra("JOB_NAME", jobName);
            switchIntent.putExtra("USER_NAME", userName);
            startActivity(switchIntent);
        }


    }

    //set the adapter to make the listview show the jobs
    protected void setDefaultJobs(){
        JobNameList.clear();
        jobRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot adSnapshot : snapshot.getChildren()){
                    JobNameList.add(adSnapshot.getKey());
                }
                listOfJobs.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }






}