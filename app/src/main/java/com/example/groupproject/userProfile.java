package com.example.groupproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.BreakIterator;
import java.util.ResourceBundle;
public class userProfile extends AppCompatActivity implements View.OnClickListener {
    private Button logout,update;
    DatabaseReference sDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        logout= findViewById(R.id.logOutBtn);
        update = findViewById(R.id.updateUserInfo);
        update.setOnClickListener(this);
        logout.setOnClickListener(this);
        sDatabase = FirebaseDatabase.getInstance().getReference();
    }
    protected String getFirstName() {
        EditText newFirstName = findViewById(R.id.newFirstName);
        return newFirstName.getText().toString().trim();
    }
    protected String getLastName() {
        EditText newLastName = findViewById(R.id.newLastName);
        return newLastName.getText().toString().trim();
    }
    protected String getPassword() {
        EditText newPassword = findViewById(R.id.newPassword);
        return newPassword.getText().toString().trim();
    }
    protected String getConfirmPassword() {
        EditText newPasswordConfirmation = findViewById(R.id.newPasswordConfirmation);
        return newPasswordConfirmation.getText().toString().trim();
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
    protected boolean isEmptyFirstName(String firstName) {
        return firstName.isEmpty();
    }

    protected boolean isEmptyLastName(String LastName) {
        return LastName.isEmpty();
    }

    protected void switch2Signin() {
        Intent switchIntent = new Intent(this, SignIn.class);
        startActivity(switchIntent);
    }
    protected void switch2homepage() {
        Intent switchIntent = new Intent(this, Homepage.class);
        startActivity(switchIntent);
    }



    public String getUserName(){
        Intent intent=getIntent();
        String currentuserName=intent.getStringExtra("USER_NAME");
        return currentuserName;
    }


    @Override
    public void onClick(View v) {

        String userName=getUserName();
        String password = getPassword();
        String confirmPassword = getConfirmPassword();
        String firstname = getFirstName();
        String Lastname = getLastName();

        if (v.getId() == R.id.updateUserInfo) {
            if(isEmptyFirstName(firstname)){
                Toast.makeText(getApplicationContext(),"first name not entered remains unchanged",Toast.LENGTH_LONG).show();
            }
            if(isEmptyLastName(Lastname)){
                Toast.makeText(getApplicationContext(),"last name not entered remains unchanged",Toast.LENGTH_LONG).show();
            }
            if(isEmptyPassword(password) || isEmptyConfirmPassword(confirmPassword) || !passwordEqualsConfirmPass(password,confirmPassword)){
                Toast.makeText(getApplicationContext(),"password unchanged",Toast.LENGTH_LONG).show();
            }


            if(!isEmptyFirstName(firstname)){
                sDatabase.child(String.valueOf("User")).child(userName).child("firstName").setValue(firstname);
                Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_LONG).show();

            }
            if(!isEmptyLastName(Lastname)){
                sDatabase.child(String.valueOf("User")).child(userName).child("lastName").setValue(Lastname);
                Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_LONG).show();
            }
            if(!isEmptyPassword(password) && !isEmptyConfirmPassword(confirmPassword) && passwordEqualsConfirmPass(password,confirmPassword)  ){
                sDatabase.child(String.valueOf("User")).child(userName).child("password").setValue(password);
                Toast.makeText(getApplicationContext(),"Profile Updated",Toast.LENGTH_LONG).show();
            }
            switch2homepage();

        }
        else if(v.getId() == R.id.logOutBtn){
            switch2Signin();

        }
    }
}