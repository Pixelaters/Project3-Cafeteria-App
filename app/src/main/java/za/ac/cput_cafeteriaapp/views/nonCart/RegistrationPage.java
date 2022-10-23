package za.ac.cput_cafeteriaapp.views.nonCart;
/*
    Breyton Ernstzen (217203027)
 */

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;
import za.ac.cput_cafeteriaapp.R;

public class RegistrationPage extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;

    private TextView register, goBack2;
    private EditText regUserName, regEmail, regPassword, regConfirmPW;
    private ProgressBar progressBar;
    private CheckBox passwordCb, cnfrmPwCb;



    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +             //The pw requires at least one digit
            "(?=.*[a-z])" +             //The pw requires at least one small letter
            "(?=.*[A-Z])" +             //The pw requires at least one capital letter
            "(?=.*[@#$%^&+=!_])" +        //The pw requires at least one special character
            "(?=\\S+$)" +               //No white spaces allowed
            ".{8,15}" +                   //The password requires at least 8 characters
            "$");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#22378d"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        passwordCb = findViewById(R.id.regpw);
        cnfrmPwCb = findViewById(R.id.regconfrmpw);

        mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.registerBtn);
        register.setOnClickListener(this);

        goBack2= (TextView) findViewById(R.id.goBack2);
        goBack2.setOnClickListener(this);

        regUserName= (EditText) findViewById(R.id.regUserName);
        regEmail= (EditText) findViewById(R.id.regEmail);
        regPassword= (EditText) findViewById(R.id.regPassword);
        regConfirmPW= (EditText) findViewById(R.id.regConfirmPW);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        passwordCb.setOnCheckedChangeListener((compoundButton, isChecked) -> {

            if(isChecked){
                regPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            }else{
                regPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });

        cnfrmPwCb.setOnCheckedChangeListener((compoundButton, isChecked) -> {

            if(isChecked){
                regConfirmPW.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            }else{
                regConfirmPW.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.goBack2:
                startActivity(new Intent(this, LoginPage.class));
                break;
            case R.id.registerBtn:
                makeAccount();
                break;
        }
    }

    private void makeAccount(){
        String username = regUserName.getText().toString().trim();
        String email = regEmail.getText().toString().trim();
        String password = regPassword.getText().toString().trim();
        String confirmPassword = regConfirmPW.getText().toString().trim();

//        if(username.isEmpty()||email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()) {
//            Toast.makeText(RegistrationPage.this, "Please complete all fields", Toast.LENGTH_LONG).show();
//
//        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            regEmail.setError("Please provide a valid email");
//
//        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
//            regPassword.setError("Password must be at least 8 characters. Requires at least one digit, " +
//                    "one lowercase letter, one uppercase, and one special character");
//
//        }else if(!PASSWORD_PATTERN.matcher(confirmPassword).matches()){
//            regConfirmPW.setError("Password must be at least 8 characters. Requires at least one digit, " +
//                    "one lowercase letter, one uppercase, and one special character");
//
//        }else if(!password.equals(confirmPassword)){
//            regConfirmPW.setError("Password does not match with initial password");
//        }

        if (username.isEmpty()){
            regUserName.setError("Username is required");
            regUserName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            regEmail.setError("Email is required");
            regEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regEmail.setError("Please provide a valid email");
            regEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            regPassword.setError("Password is required");
            regPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            regConfirmPW.setError("Confirm password is required");
            regConfirmPW.requestFocus();
            return;
        }

        if (password.length()< 6){
            regPassword.setError("Min password length should be 6 characters!");
            regPassword.requestFocus();
            return;
        }

        if(!password.equals(confirmPassword)){
            regConfirmPW.setError("Password does not match with initial password");
            regConfirmPW.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            final User user = new User(username,email);

                           FirebaseDatabase.getInstance("https://project-3-cafeteria-app-default-rtdb.europe-west1.firebasedatabase.app").getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()){
                                            Toast.makeText(RegistrationPage.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            //redirect to login
                                        }else{
                                            Toast.makeText(RegistrationPage.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });
                        }else{
                            Toast.makeText(RegistrationPage.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                });

//        if (confirmPassword.isEmpty()){
//            regConfirmPW.setError("Confirm password is required");
//            regConfirmPW.requestFocus();
//            return;
//        }

//        if (confirmPassword != password){
//            regConfirmPW.setError("Password does not match confirm password");
//            regConfirmPW.requestFocus();
//            return;
//        }
    }
}

