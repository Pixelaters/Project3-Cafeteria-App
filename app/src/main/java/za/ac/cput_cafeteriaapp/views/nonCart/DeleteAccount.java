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

public class DeleteAccount extends AppCompatActivity {
    private EditText email,password;
    private Button btnVal;
    private Button btnNah;

    SQLiteDatabase db;
    SQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        email = findViewById(R.id.Eman);
        password = findViewById(R.id.oGPass);

        btnVal = findViewById(R.id.btnBye);
        btnNah = findViewById(R.id.btnNope);
        helper = new RegistrationDB(this);
        db = helper.getWritableDatabase();

        btnVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_address = email.getText().toString();
                String pass = password.getText().toString();

                Cursor cursor = db.rawQuery("SELECT * FROM " + RegistrationDB.TABLE_1_NAME + " WHERE " +
                        RegistrationDB.EMAIL + "=? AND " + RegistrationDB.PASSWORD + "=?", new String[]{email_address,pass});

                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(DeleteAccount.this, "Please enter the proper details", Toast.LENGTH_SHORT).show();
                    Intent failed = new Intent(DeleteAccount.this,DeleteAccount.class);
                    startActivity(failed);
                } else if(cursor != null && cursor.getCount()>0){
                    Toast.makeText(DeleteAccount.this, "Details removed successfully", Toast.LENGTH_SHORT).show();
                    Intent deleted = new Intent(DeleteAccount.this,LoginPage.class);
                    startActivity(deleted);

                }

            }
        });

        btnNah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent no = new Intent(DeleteAccount.this, UserSettings.class);
                startActivity(no);
            }
        });
    }
}