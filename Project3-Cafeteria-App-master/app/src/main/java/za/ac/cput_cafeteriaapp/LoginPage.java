package za.ac.cput_cafeteriaapp;
/*
    Breyton Ernstzen (217203027)
 */

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {
    private EditText userName;
    private EditText userPassword;
    private TextView txtLinkBack;
    private TextView txtLinkCreateAccount;
    private CheckBox checkBoxLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPW);
        txtLinkBack = findViewById(R.id.goBack);
        txtLinkCreateAccount = findViewById(R.id.makeAccount);
        checkBoxLoginPassword = findViewById(R.id.chk3);

        txtLinkBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previous = new Intent(LoginPage.this,WelcomePage.class);
                startActivity(previous);
            }
        });

        txtLinkCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCreateAccount = new Intent(LoginPage.this,RegistrationPage.class);
                startActivity(goCreateAccount);
            }
        });

        checkBoxLoginPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    userPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                } else {
                    userPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }
}