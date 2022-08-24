package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class DeleteAccount extends AppCompatActivity {
    private Button btnDeleteAccount;
    private Button btnCancel;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        openHelper = new RegistrationDB(this);
        db = openHelper.getReadableDatabase();

        btnDeleteAccount = findViewById(R.id.finalDelete);
        btnCancel = findViewById(R.id.cancelDeletion);

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cursor = db.rawQuery("DELETE * FROM" + RegistrationDB.TABLE_1_NAME + "WHERE" +
                    RegistrationDB.USER_NAME "=?");
            }
        });

    }
}