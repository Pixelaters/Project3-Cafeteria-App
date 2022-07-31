package za.ac.cput_cafeteriaapp;
/*
    Breyton Ernstzen (217203027)
 */

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

import java.util.regex.Pattern;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class RegistrationPage extends AppCompatActivity {
    SQLiteOpenHelper db_OpenHelper;
    SQLiteDatabase myDB;

    private TextView txtLinkGoBack2;
    private EditText regUserName;
    private EditText regEmail;
    private EditText regPassword;
    private EditText regCnfrmPassword;
    private CheckBox cbRegPw;
    private CheckBox cbCnfrmRegPw;
    private Button btnRegister;

    public static final Pattern USERNAME_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=\\S+$)" +
            ".{9,9}" +
            "$");

    public static final Pattern EMAIL_PATTERN = Pattern.compile("^" +
            "(.+)@" +
            "(.+)" +
            "$");

    //Don't touch this code
    //Number of characters allowed when password is entered as well as required characters
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^" +
            "(?=.*[0-9])" +             //The pw requires at least one digit
            "(?=.*[a-z])" +             //The pw requires at least one small letter
            "(?=.*[A-Z])" +             //The pw requires at least one capital letter
            "(?=.*[@#$%^&+=!_])" +        //The pw requires at least one special character
            "(?=\\S+$)" +               //No white spaces allowed
            ".{8,15}" +                   //The password requires at least 8 characters
            "$");

    //@SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        db_OpenHelper = new RegistrationDB(this);

        txtLinkGoBack2 = findViewById(R.id.goBack2);
        regUserName = findViewById(R.id.regUserName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regCnfrmPassword = findViewById(R.id.regConfirmPW);
        cbRegPw = findViewById(R.id.regpw);
        cbCnfrmRegPw = findViewById(R.id.regconfrmpw);
        btnRegister = findViewById(R.id.registerBtn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = regUserName.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();
                String confirmedPassword = regCnfrmPassword.getText().toString();

                //bug needs to be fixed below \/
                myDB = db_OpenHelper.getWritableDatabase();
                Cursor usernameCursor = myDB.rawQuery("SELECT * FROM " + RegistrationDB.TABLE_1_NAME +
                        " WHERE " + RegistrationDB.USER_NAME + "=?", new String[]{username});
                Cursor emailCursor = myDB.rawQuery("SELECT * FROM " + RegistrationDB.TABLE_1_NAME +
                        " WHERE " + RegistrationDB.EMAIL + "=?", new String[]{email});

                if(TextUtils.isEmpty(regUserName.getText().toString()) || TextUtils.isEmpty(regEmail.getText().toString())
                        || TextUtils.isEmpty(regPassword.getText().toString()) || TextUtils.isEmpty(regCnfrmPassword.getText().toString())){
                    Toast.makeText(RegistrationPage.this,"Please complete all fields", Toast.LENGTH_LONG).show();

                }else if(usernameCursor.getCount() > 0){
                    Toast.makeText(RegistrationPage.this,"Username already in use. Try a different username", Toast.LENGTH_LONG).show();

                }else if(emailCursor.getCount() > 0){
                    Toast.makeText(RegistrationPage.this,"Email already exists",Toast.LENGTH_LONG).show();

                }else if(USERNAME_PATTERN.matcher(username).matches() && EMAIL_PATTERN.matcher(password).matches() &&
                        PASSWORD_PATTERN.matcher(password).matches() && PASSWORD_PATTERN.matcher(confirmedPassword).matches()) {
                    insertData(username,email,password,confirmedPassword);
                    Toast.makeText(RegistrationPage.this, "Registration success", Toast.LENGTH_LONG).show();
                    login();

                }else if(!USERNAME_PATTERN.matcher(username).matches()) {
                    regUserName.setError("Username should be your student/staff number");

                }else if(!EMAIL_PATTERN.matcher(email).matches()){
                    regEmail.setError("Invalid email type. Include '@' in email");

                }else if(!PASSWORD_PATTERN.matcher(password).matches()){
                    regPassword.setError("Password must be at least 8 characters long.Requires at least one digit, one lowercase letter, one uppercase, and one special character");

                }else if(!PASSWORD_PATTERN.matcher(confirmedPassword).matches()){
                    regCnfrmPassword.setError("Password must be at least 8 characters long.Requires at least one digit, one lowercase letter, one uppercase, and one special character");
                }

            }
        });

                txtLinkGoBack2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent goToHome = new Intent(RegistrationPage.this, WelcomePage.class);
                        startActivity(goToHome);
                    }
                });

                cbRegPw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            regPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                        } else {
                            regPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }
                    }
                });

                cbCnfrmRegPw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            regCnfrmPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                        } else {
                            regCnfrmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }
                    }
                });
            }

            public void insertData(String userName, String email, String password, String confirmedPassword) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(RegistrationDB.USER_NAME, userName);
                contentValues.put(RegistrationDB.EMAIL, email);
                contentValues.put(RegistrationDB.PASSWORD, password);
                contentValues.put(RegistrationDB.CONFIRMED_PASSWORD, confirmedPassword);
                long id = myDB.insert(RegistrationDB.TABLE_1_NAME, null, contentValues);

            }

    public void login(){
        Intent loginPage = new Intent(this, LoginPage.class);
        startActivity(loginPage);

        }
    }

