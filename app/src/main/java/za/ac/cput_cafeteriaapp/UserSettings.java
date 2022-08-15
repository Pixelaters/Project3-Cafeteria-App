package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class UserSettings extends AppCompatActivity {
    private TextView username;
    private TextView name;
    private TextView email;
    private Button change_Password;
    private Button save_Changes;
    private Button delete_Account;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        openHelper = new RegistrationDB(this);
        db = openHelper.getWritableDatabase();
        db = openHelper.getReadableDatabase();

        username = findViewById(R.id.usernameUpdate);
        name = findViewById(R.id.nameUpdate);
        email = findViewById(R.id.emailUpdate);
        change_Password = findViewById(R.id.detailsUpdate);
        save_Changes = findViewById(R.id.passwordChange);
        delete_Account = findViewById(R.id.deleteAccount);


    }


}