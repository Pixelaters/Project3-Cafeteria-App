package za.ac.cput_cafeteriaapp;
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
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class LoginPage extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    private EditText userName;
    private EditText userPassword;
    private TextView txtLinkBack;
    private TextView txtLinkCreateAccount;
    private CheckBox checkBoxLoginPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        openHelper = new RegistrationDB(this);
        db = openHelper.getReadableDatabase();

        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPW);
        txtLinkBack = findViewById(R.id.goBack);
        txtLinkCreateAccount = findViewById(R.id.makeAccount);
        checkBoxLoginPassword = findViewById(R.id.chk3);
        btnLogin = findViewById(R.id.button4);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrName = userName.getText().toString();
                String password = userPassword.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " + RegistrationDB.TABLE_1_NAME + " WHERE " +
                        RegistrationDB.USER_NAME + "=? AND " + RegistrationDB.PASSWORD + "=?", new String[]{usrName, password});

                if (TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(userPassword.getText().toString())) {
                    Toast.makeText(LoginPage.this, "Fields Cannot Be Empty", Toast.LENGTH_LONG).show();

                } else if (cursor != null && cursor.getCount() > 0) {
                    //cursor.moveToNext();
                    Toast.makeText(LoginPage.this, "Welcome to CPUT-Cafeteria App!", Toast.LENGTH_LONG).show();
                    home();

                } else {
                    Toast.makeText(LoginPage.this, "Student does not exist.Register Instead?", Toast.LENGTH_LONG).show();
                }
            }
        });

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

    //takes user the homepage
    public void home(){
        Intent goToHome = new Intent(LoginPage.this,Homepage.class);
        startActivity(goToHome);
        finish();
    }
}