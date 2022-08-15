package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class UpdatePassword extends AppCompatActivity {
    SQLiteDatabase dbread;
    SQLiteDatabase dbwrite;
    SQLiteOpenHelper openHelper;
    Cursor cursor;

    private EditText oldPassword;
    private EditText newPassword;
    private EditText confirmPassword;
    private Button btnUpdatePassword;
    private TextView txtback;
    private TextView txtconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        openHelper = new RegistrationDB(this);
        dbwrite = openHelper.getWritableDatabase();
        dbread = openHelper.getReadableDatabase();

        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newpassword);
        confirmPassword = findViewById(R.id.confirm_password);
        btnUpdatePassword = findViewById(R.id.change_password_btn);
        txtback = findViewById(R.id.back);
        txtconfirm = findViewById(R.id.confirm);

        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = oldPassword.getText().toString();
                String nw = newPassword.getText().toString();
                String cpw = confirmPassword.getText().toString();


            }
        });
    }
}