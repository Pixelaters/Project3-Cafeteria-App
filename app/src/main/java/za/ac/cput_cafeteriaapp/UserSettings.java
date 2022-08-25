package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class UserSettings extends AppCompatActivity {
    private TextView username;
    private TextView name;
    private TextView txtBack;
    private Button btnUpdatePassword;
    private Button btnsaveChanges;
    private Button btndeleteAccount;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        openHelper = new RegistrationDB(this);
        db = openHelper.getWritableDatabase();

        username = findViewById(R.id.usernameUpdate);
        txtBack = findViewById(R.id.Back);


        btnUpdatePassword = findViewById(R.id.detailsUpdate);
        btnsaveChanges = findViewById(R.id.passwordChange);
        btndeleteAccount = findViewById(R.id.deleteAccount);

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(UserSettings.this,UpdatePassword.class);
                startActivity(update);
            }
        });

        btnsaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = username.getText().toString();

                Cursor cursor = db.rawQuery(" INSERT " + usrname + " Into " + RegistrationDB.TABLE_1_NAME + " WHERE " +
                  RegistrationDB.USER_NAME  +"=?",new String[]{usrname});

                if(cursor.getCount()>0){
                    Toast.makeText(UserSettings.this, "Username has been changed successfully", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btndeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent deletePage = new Intent(UserSettings.this,DeleteAccount.class);
                startActivity(deletePage);
            }
        });

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent previous = new Intent(UserSettings.this,Homepage.class);
                startActivity(previous);
            }
        });




    }


}