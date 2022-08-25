package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class UpdatePassword extends AppCompatActivity {
    SQLiteDatabase DB;
    SQLiteOpenHelper Helper;

    private EditText oldPassword;
    private EditText newPassword;
    private EditText confirmPassword;
    private Button btnUpdatePassword;
    private TextView txtBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        Helper = new RegistrationDB(this);
        DB = Helper.getWritableDatabase();
        DB = Helper.getReadableDatabase();

        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newpassword);
        confirmPassword = findViewById(R.id.confirm_password);
        btnUpdatePassword = findViewById(R.id.change_password_btn);
        txtBack = findViewById(R.id.back);


        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = oldPassword.getText().toString();
                String nw = newPassword.getText().toString();
                String cpw = confirmPassword.getText().toString();

            Cursor cursor1 = DB.rawQuery(" SELECT " + RegistrationDB.PASSWORD + " from " + RegistrationDB.TABLE_1_NAME + " WHERE " +
                    RegistrationDB.PASSWORD +  " =? ", new String[]{old});

            if (cursor1.getCount()>0){
                Cursor cursor2 = DB.rawQuery(" Insert " + newPassword +" AND "+ confirmPassword + " INTO " + RegistrationDB.TABLE_1_NAME + " WHERE " +
                        RegistrationDB.PASSWORD + "=? AND" + RegistrationDB.CONFIRMED_PASSWORD + "=?", new String[]{nw,cpw});
            } else{
                Toast.makeText(UpdatePassword.this, "This password could not be found", Toast.LENGTH_SHORT).show();
            }
            }
        });

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previous = new Intent(UpdatePassword.this,UserSettings.class);
                startActivity(previous);
            }
        });
    }
}