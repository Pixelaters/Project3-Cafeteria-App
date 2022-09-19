package za.ac.cput_cafeteriaapp.views.nonCart;

import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.EMAIL;
import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.PASSWORD;
import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.TABLE_1_NAME;
import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.USER_NAME;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;
import za.ac.cput_cafeteriaapp.R;

public class ForgotPassword extends AppCompatActivity {

    //variables
    private EditText emailEditText;
    private Button resetPasswordBtn;
    private ProgressBar progressBar;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText= (EditText) findViewById(R.id.emailForgotPassword);
        resetPasswordBtn=(Button) findViewById(R.id.ResetPasswordForgotPassword);
        progressBar= (ProgressBar) findViewById(R.id.progressBar6);

        auth= FirebaseAuth.getInstance();

        resetPasswordBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                resetPassword();
            }
        });

        }

    private void resetPassword(){
        String email= emailEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("Username is required");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("Please provide a valid email");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(ForgotPassword.this, "Try again! Something wrong happened!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}