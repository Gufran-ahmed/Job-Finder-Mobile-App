/* Archer Zhou
   B00806294
   CSCI3130 group9 project
   Sign in page
   P.S.:
         The page is the first page that the users will see, it enable the user to log in and continue using the app

         DONT ADJUST ANY CODE WITHOUT TALKING WITH ARCHER
 */

package com.example.groupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.groupproject.ui.dashboard.DashboardFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    //static variable to check the user's password
    private String tempPassword = "";
    public static String Shared_Prefs = "sharedPrefs";
    public static String TEXT = "text";
    SharedPreferences sp;


    //get the user's data especially the password from remote database
    ValueEventListener userCheck = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            User temp = null;
            for (DataSnapshot adSnapshot : snapshot.getChildren()) {
                temp = adSnapshot.getValue(User.class);
            }

            if (temp != null) {
                tempPassword = temp.getPassword();
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    FirebaseDatabase database = null;
    DatabaseReference userRef = null;

    EditText userNameBox;
    EditText passwordBox;

    //add a textWatcher so that every time the user finished typing username, it will automatically search the username in the database
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            userRef.orderByChild("userName").equalTo(getUserName()).addListenerForSingleValueEvent(userCheck);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_sign);

        userNameBox = findViewById(R.id.userNameBox);
        passwordBox = findViewById(R.id.passwordBox);
        Button signInButton = findViewById(R.id.signInButton);
        Button button2SignUp = findViewById(R.id.button2SignUp);

        userNameBox.addTextChangedListener(textWatcher);
        signInButton.setOnClickListener(this);
        button2SignUp.setOnClickListener(this);

        sp = getSharedPreferences("MyUserPrefs",Context.MODE_PRIVATE);

        this.initializeDatabase();
    }

    //initialize the remote database and get a reference from path user
    protected void initializeDatabase() {

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("User");

    }

    protected String getUserName() {

        return userNameBox.getText().toString().trim();
    }

    protected String getPassWord() {

        return passwordBox.getText().toString().trim();
    }

    //check whether the username is empty
    protected boolean isEmptyUserName(String userName) {
        return userName.isEmpty();
    }

    //check whether the password is empty
    protected boolean isEmptyPassword(String password) {
        return password.isEmpty();
    }

    //check the username is alphanumeric or not
    protected boolean isValidUserName(String userName) {

        return (userName.matches("^[a-zA-Z0-9]*$") && !userName.isEmpty());
    }

    //check the password is alphanumeric or not
    protected boolean isValidPassword(String password) {

        return (password.matches("^[a-zA-Z0-9]*$") && !password.isEmpty());
    }

    //method to switch to employer page when clicking the button and pass the user name to that intent
    protected void switch2EmployerWindow() {

        Intent switchIntent = new Intent(this, Homepage.class);
        switchIntent.putExtra("USER_NAME", getUserName());
        startActivity(switchIntent);

    }

    //check does the username exist in the database
    protected boolean UserExist() {

        boolean valid = false;

        if (!tempPassword.equals("")) {
            valid = true;
        }

        return valid;
    }

    //check does the password match the username
    protected boolean PasswordMatch(String password) {

        boolean valid = false;

        if (password.equals(tempPassword)) {
            valid = true;
        }
        tempPassword = "";

        return valid;
    }


    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }

    //method to switch to sign up page when clicking the button
    protected void switch2SignUpWindow() {

        Intent SwitchIntent = new Intent(SignIn.this, SignUp.class);
        startActivity(SwitchIntent);
    }
    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(Shared_Prefs, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT,userNameBox.getText().toString());
        editor.commit();
    }



    //if any error comes out, give the error message
    //if there is no error, switch to employer page
    //if sign up button is clicked, go to sign up page
    @Override
    public void onClick(View v) {

        Button temp = (Button) v;
        String userName = getUserName();
        String password = getPassWord();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("userName", userName);
        editor.commit();


        switch (temp.getId()) {

            case R.id.signInButton:

                String errorMessage = "";

                if (isEmptyUserName(userName)) {
                    errorMessage = "Empty user name!";
                } else {
                    if (!isValidUserName(userName)) {
                        errorMessage = "Non-Valid user name!";
                    } else {
                        if (isEmptyPassword(password)) {
                            errorMessage = "Empty password!";
                        } else {
                            if (!isValidPassword(password)) {
                                errorMessage = "Non-Valid password!";
                            } else {
                                if (!UserExist()) {
                                    errorMessage = "No such user!";
                                } else {
                                    if (!PasswordMatch(password)) {
                                        errorMessage = "Wrong password!";
                                    }
                                }
                            }
                        }
                    }
                }


                if (errorMessage.isEmpty()) {
                    switch2EmployerWindow();
                } else {
                    setStatusMessage(errorMessage);
                }
                break;

            case R.id.button2SignUp:
                switch2SignUpWindow();

                break;

        }


    }


}