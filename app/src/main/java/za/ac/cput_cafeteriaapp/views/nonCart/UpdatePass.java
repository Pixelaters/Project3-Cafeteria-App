package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;
import za.ac.cput_cafeteriaapp.R;

public class UpdatePass extends AppCompatActivity {
    private EditText oldPass,newPass,conPass;
    private Button btnUp,btnmist;
    SQLiteDatabase db;
    SQLiteOpenHelper help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass);

        oldPass=findViewById(R.id.oldPass);
        newPass=findViewById(R.id.newPass);
        conPass=findViewById(R.id.conPass);

        btnUp = findViewById(R.id.btnUpdate);
        btnmist = findViewById(R.id.btnMistake);
        help = new RegistrationDB(this);
        db = help.getWritableDatabase();


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = oldPass.getText().toString();
                String nw = newPass.getText().toString();
                String con = conPass.getText().toString();

                Cursor cursor1 = db.rawQuery(" SELECT " + RegistrationDB.PASSWORD + " from " + RegistrationDB.TABLE_1_NAME + " WHERE " +
                        RegistrationDB.PASSWORD +  " =? ", new String[]{old});

                if (cursor1.getCount()>0){
                    Cursor cursor2 = db.rawQuery(" Insert " + newPass +" AND "+ conPass + " INTO " + RegistrationDB.TABLE_1_NAME + " WHERE " +
                            RegistrationDB.PASSWORD + "=? AND" + RegistrationDB.CONFIRMED_PASSWORD + "=?", new String[]{nw,con});
                } else{
                    Toast.makeText(UpdatePass.this, "This password could not be found", Toast.LENGTH_SHORT).show();
                    Intent unacceptable = new Intent(UpdatePass.this,UpdatePass.class);
                    startActivity(unacceptable);
                }
            }
            }
        );

        btnmist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent almost = new Intent(UpdatePass.this,UserSettings.class);
                startActivity(almost);
            }
        });

    }
}