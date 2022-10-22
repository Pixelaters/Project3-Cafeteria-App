package za.ac.cput_cafeteriaapp.views.nonCart;
/*
    Breyton Ernstzen (217203027)
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import za.ac.cput_cafeteriaapp.R;

public class WelcomePage extends AppCompatActivity {
    private Button btnCreateAccount;
    private Button btnSignIn;
    private TextView txtForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

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

        btnCreateAccount = findViewById(R.id.create);
        btnSignIn = findViewById(R.id.goToLogin);
        txtForgotPassword = findViewById(R.id.forgot_password);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePage.this, RegistrationPage.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(WelcomePage.this, LoginPage.class);
                startActivity(login);
            }
        });

        txtForgotPassword.setOnClickListener(view -> {
            Intent forgotPassword = new Intent(getApplicationContext(), ForgotPassword.class);
            startActivity(forgotPassword);
        });
    }
}