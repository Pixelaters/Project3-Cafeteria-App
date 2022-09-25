package za.ac.cput_cafeteriaapp.views.nonCart;
/*
    Breyton Ernstzen (217203027)
 */

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;
import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.views.MainActivity;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Login page";
    private TextView makeAccount, forgotPassword;
    private EditText editTextEmail, editTextpassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    public static FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        makeAccount= (TextView) findViewById(R.id.makeAccount);
        makeAccount.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.button4);
        signIn.setOnClickListener(this);

        editTextEmail= (EditText) findViewById(R.id.email);
        editTextpassword= (EditText) findViewById(R.id.userPW);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        forgotPassword = (TextView) findViewById(R.id.forgotPasswordLogin);
        forgotPassword.setOnClickListener(this);

        mAuth= FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.makeAccount:
                startActivity(new Intent(this, RegistrationPage.class));
                break;
            case R.id.button4:
                userLogin();
                break;
            case R.id.forgotPasswordLogin:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Username is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextpassword.setError("Password is required");
            editTextpassword.requestFocus();
            return;
        }

        if (password.length()< 6){
            editTextpassword.setError("Min password length should be 6 characters!");
            editTextpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //redirect to user profile
                     user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(LoginPage.this, MainActivity.class));
                    }else{
                        progressBar.setVisibility(View.GONE);
                        user.sendEmailVerification();
                        Toast.makeText(LoginPage.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }




                }else{
                    Toast.makeText(LoginPage.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}