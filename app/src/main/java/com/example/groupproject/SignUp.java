package com.example.groupproject;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.SpanWatcher;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/*
Sign up
Author: Gufran
 */
public class SignUp extends AppCompatActivity implements View.OnClickListener {
    DatabaseReference sDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button SignUpBtn = findViewById(R.id.signUp);
        SignUpBtn.setOnClickListener(this);
        //   Button AlreadyHaveAnAccount = findViewById(R.id.Button2);
        // AlreadyHaveAnAccount.setOnClickListener(this);
        sDatabase = FirebaseDatabase.getInstance().getReference();
    }
    private void userAlreadyExists() {
        String userName = getUserName();

        String u = userName;
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query checkUser = ref.child("User").orderByChild("userName").equalTo(u);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String checker = snapshot.child(u).child("userName").getValue(String.class);
                    if (checker.equals(userName)){
                        setStatusMessage("user exists");
                    }
                    else {
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    protected String getFirstName() {
        EditText userName = findViewById(R.id.firstName);
        return userName.getText().toString().trim();
    }
    protected String getLastName() {
        EditText userName = findViewById(R.id.lastName);
        return userName.getText().toString().trim();
    }
    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.email);
        return emailAddress.getText().toString().trim();
    }
    protected String getUserName() {
        EditText userName = findViewById(R.id.userName);
        return userName.getText().toString().trim();
    }
    protected String getPassword() {
        EditText userName = findViewById(R.id.password);
        return userName.getText().toString().trim();
    }
    protected String getConfirmPassword() {
        EditText userName = findViewById(R.id.ConfirmPassword);
        return userName.getText().toString().trim();
    }
    protected boolean isEmptyUserName(String userName) {
        return userName.isEmpty();
    }
    protected boolean isEmptyFirstName(String firstName) {
        return firstName.isEmpty();
    }
    protected boolean isEmptyLastName(String LastName) {
        return LastName.isEmpty();
    }
    protected boolean isEmptyPassword(String Password) {
        return Password.isEmpty();
    }
    protected boolean isEmptyConfirmPassword(String ConfirmPassword) {
        return ConfirmPassword.isEmpty();
    }
    protected boolean passwordEqualsConfirmPass(String password, String ConfirmPassword) {
        return password.equals(ConfirmPassword);
    }
    protected boolean isValidEmailAddress(String emailAddress) {
        return (emailAddress.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$") && !emailAddress.isEmpty());
    }
    protected boolean isAlphanumericUserName(String userName) {
        return (userName.matches("^[a-zA-Z0-9]*$") && !userName.isEmpty());
    }
    protected boolean isValidPassword(String password){
        return (password.matches("^[a-zA-Z0-9]*$") && !password.isEmpty());
    }
    protected void switch2Signin() {
        Intent switchIntent = new Intent(this, SignIn.class);
        startActivity(switchIntent);
    }
    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message);
    }
    protected boolean isEmptybox(){
        TextView statusLabel = findViewById(R.id.statusLabel);
        String temp = statusLabel.getText().toString();
        if(temp.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        String firstName = getFirstName();
        String LastName = getLastName();
        String userName = getUserName();
        String emailAddress = getEmailAddress();
        String password = getPassword();
        String confirmPassword = getConfirmPassword();
        int rating =0;

        String errorMessage = new String();
        if (v.getId() == R.id.signUp) {
            if (isEmptyFirstName(firstName)) {
                errorMessage = getResources().getString(R.string.EMPTY_FIRST_Name);
            }
            else {
                if (isEmptyLastName(LastName)) {
                    errorMessage = getResources().getString(R.string.EMPTY_Last_Name);
                } else {
                    if (!isValidEmailAddress(emailAddress)) {
                        errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS);
                    } else {
                        if (isEmptyUserName(userName)) {
                            errorMessage = getResources().getString(R.string.EMPTY_USER_NAME);
                        } else {
                            if (!isAlphanumericUserName(userName)) {
                                errorMessage = getResources().getString(R.string.NON_ALPHA_NUMERIC_USER_NAME);
                            } else {
                                if (isEmptyPassword(password)) {
                                    errorMessage = getResources().getString(R.string.EMPTY_Password);
                                } else {
                                    if (!isValidPassword(password)) {
                                        errorMessage = getResources().getString(R.string.Invalid_Password);
                                    } else {
                                        if (isEmptyConfirmPassword(confirmPassword)) {
                                            errorMessage = getResources().getString(R.string.EMPTY_Confirm_Password);
                                        } else {
                                            if (!passwordEqualsConfirmPass(password, confirmPassword)) {
                                                errorMessage = getResources().getString(R.string.Password_MissMatch);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            userAlreadyExists();

            if (errorMessage.isEmpty() && isEmptybox()) {
                User u = new User("false",emailAddress,firstName,LastName,userName,password,0);
                sDatabase.child(String.valueOf("User")).child(userName).setValue(u);
                setStatusMessage("");
                switch2Signin();
            } else {
                setStatusMessage(errorMessage);
            }
        }
        //  else if(v.getId()==R.id.Button2) {
        //       switch2Signin();
        //         }

    }

}