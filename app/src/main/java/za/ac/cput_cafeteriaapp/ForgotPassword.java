package za.ac.cput_cafeteriaapp;

import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.EMAIL;
import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.PASSWORD;
import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.TABLE_1_NAME;
import static za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB.USER_NAME;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import za.ac.cput_cafeteriaapp.DatabaseHelper.RegistrationDB;

public class ForgotPassword extends AppCompatActivity {

    //variables
    private ProgressDialog progressDialog;
    EditText etUserMail, etPassword, etConfirmPassword;
    Button btnChangePassword;
    TextView txtBack, txtSignIn;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        // initialise Db
        openHelper = new RegistrationDB(this);
        db = openHelper.getReadableDatabase();

        //initialise progress progress dialog
        progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);


        //link variables with xml views
        etUserMail = findViewById(R.id.userMail);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirm_password);
        btnChangePassword = findViewById(R.id.change_password_btn);
        txtBack = findViewById(R.id.back);
        txtSignIn = findViewById(R.id.sign_in);

        btnChangePassword.setOnClickListener(view -> {
            /* check if user inputs are valid and display
            corresponding errors
             */
            if (!isValid() || !userExists()) {
                return;
            }
            progressDialog.setMessage("Changing password...");
            showDialog();
            changePassword();

        });

        txtBack.setOnClickListener(view -> {
           // close this activity and return to previous activity
            finish();
        });

        txtSignIn.setOnClickListener(view -> {
           // go to login page
            login();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private void showDialog() {
        // display progress dialog if not already shown
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        //hide the progress dialog if it is already showing
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    public boolean isValid() {
        boolean valid;

        String userMail = etUserMail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        // validate username / email field
        if (userMail.isEmpty()) {
            etUserMail.setError("Username / email must not be empty");
            valid = false;
        } else if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etPassword.setError("Password should be greater than 4 alphanumeric characters");
            valid = false;
        } else if (confirmPassword.isEmpty() || confirmPassword.length() < 4 || confirmPassword.length() > 10) {
            // set minimum requirements for password
            etConfirmPassword.setError("Password should be greater than 4 alphanumeric characters");
            valid = false;
        } else if (!confirmPassword.equals(password)) {
            // check if passwords match
            etConfirmPassword.setError("Passwords must match");
            valid = false;
        } else {
            etUserMail.setError(null);
            etPassword.setError(null);
            etConfirmPassword.setError(null);
            valid = true;
        }

        return valid;
    }

    public boolean userExists() {
        boolean userExists;
        String userMail = etUserMail.getText().toString();
        // check if user exists in db
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_1_NAME + " WHERE " +
                RegistrationDB.USER_NAME + "=? OR " + RegistrationDB.EMAIL + "=?", new String[]{userMail, userMail});

        if (cursor != null && cursor.getCount() > 0) {
            userExists = true;
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "User does not exist.Register Instead or Check the username /email and try again ", Toast.LENGTH_LONG);
            toast.show();
            userExists = false;
        }
        return userExists;
    }

    // code to change the password
    public void changePassword() {
        openHelper = new RegistrationDB(this);
        SQLiteDatabase db = openHelper.getReadableDatabase();
        String password = etPassword.getText().toString();
        String userMail = etUserMail.getText().toString();

        ContentValues values = new ContentValues();
        values.put(PASSWORD, password);
        try {
            int result = db.update(TABLE_1_NAME, values, USER_NAME + " = ? OR " + EMAIL + " = ?",
                    new String[]{userMail, userMail});

            if (result == 1) {
                new Handler().postDelayed(() -> {
                    hideDialog();
                    //  display toast message
                    Toast.makeText(getApplicationContext(), "Password Changed Successfully. Please login with new password", Toast.LENGTH_LONG).show();
                    login();
                }, 5000);

            } else {
                hideDialog();
                Toast.makeText(getApplicationContext(), "Failed to change password", Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {
            hideDialog();
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }finally {
            db.close();
        }

    }

    private void login() {
        //            go to login page
        Intent intent = new Intent(getApplicationContext(), LoginPage.class);
        startActivity(intent);
    }
}