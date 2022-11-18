package com.example.groupproject.ui.notifications;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.groupproject.MapsActivity;
import com.example.groupproject.R;
import com.example.groupproject.userProfile;
public class NotificationsFragment extends Fragment implements View.OnClickListener {
    private NotificationsViewModel notificationsViewModel;
    private Button prof;
    private Button Mapsbtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        prof = root.findViewById(R.id.userProfil);
        prof.setOnClickListener(this::onClick);
        Mapsbtn = root.findViewById(R.id.Mapsbtn);
        Mapsbtn.setOnClickListener(this::onClick);
        return root;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        String userName = sp.getString("userName","");
        TextView s1 = getActivity().findViewById(R.id.saveText);
        s1.setText(userName);


    }
    public String savData(){
        TextView s1 = getActivity().findViewById(R.id.saveText);
        return s1.getText().toString();
    }



    @Override
    public void onClick(View v) {

        String username = savData();




        if (v.getId() == R.id.userProfil) {
            Intent switchIntent = new Intent(getActivity(), userProfile.class);
            switchIntent.putExtra("USER_NAME", username);
            startActivity(switchIntent);
        }
        else {
            Intent switchIntent = new Intent(getActivity(), MapsActivity.class);
            startActivity(switchIntent);

        }
    }
}