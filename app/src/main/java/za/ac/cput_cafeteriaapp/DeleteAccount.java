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

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class DeleteAccount extends AppCompatActivity {

    private Button btnDeleteAccount;
    private Button btnCancel;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnDeleteAccount = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);


        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(DeleteAccount.this,DeleteValidation.class);
                startActivity(delete);
            }
        });

        btnCancel.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(DeleteAccount.this,UserSettings.class);
                startActivity(cancel);

            }
        }));
    }
}