package za.ac.cput_cafeteriaapp.views.nonCart;

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
import za.ac.cput_cafeteriaapp.R;

public class UserSettings extends AppCompatActivity {
    private EditText username;
    private TextView txtback;
    private Button btnDelete;
    private Button btnSave;
    private Button btnUpdate;
    SQLiteDatabase db;
    SQLiteOpenHelper helper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        helper = new RegistrationDB(this);
        db = helper.getWritableDatabase();

        username = findViewById(R.id.usernameUpdate);
        txtback = findViewById(R.id.back);

        btnDelete = findViewById(R.id.deleteAccount);
        btnSave = findViewById(R.id.detailsUpdate);
        btnUpdate = findViewById(R.id.passwordChange);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(UserSettings.this,UpdatePass.class);
                startActivity(update);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(UserSettings.this,DeleteAccount.class);
                startActivity(delete);
            }
        });

        txtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(UserSettings.this,Homepage.class);
            }
        });

    }
}