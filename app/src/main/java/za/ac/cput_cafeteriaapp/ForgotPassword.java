package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ForgotPassword extends AppCompatActivity {

    //variables
    private ProgressDialog progressDialog;
    EditText etPassword, etConfirmPassword;
    Button btnChangePassword;
    TextView txtBack, txtSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        //initialise progress progress dialog
        progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);


        //link variables with xml views
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirm_password);
        btnChangePassword = findViewById(R.id.change_password_btn);
        txtBack = findViewById(R.id.back);
        txtSignIn = findViewById(R.id.sign_in);

        btnChangePassword.setOnClickListener(view -> {
            /* check if user inputs are valid and display
            corresponding errors
             */
            if (!validate()) {
                return;
            }
            progressDialog.setMessage("Changing password...");
            showDialog();
            // TODO code for changing password

//            temporarily link to forgot order take away
            Intent orderTakeAway = new Intent(getApplicationContext(), OrderTakeAway.class);
            startActivity(orderTakeAway);
            hideDialog();

        });

        txtBack.setOnClickListener(view -> {
//            close this activity and return to previous activity
            finish();
        });

        txtSignIn.setOnClickListener(view -> {
//            go to login page
            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
            startActivity(intent);
        });

    }


    private void showDialog() {
        // display progress dialog if not already shown
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        //hide the progress dialog if it is already showing
        if (progressDialog.isShowing()) {
            // handler to delay hiding the progress dialog
            //  so as to simulate changing password
            new Handler().postDelayed(() -> {
                progressDialog.dismiss();

                //  display toast message
                Toast toast = Toast.makeText(getApplicationContext(), "Password Changed Successfully", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }, 5000);
        }
    }


    public boolean validate() {
        boolean valid = true;

        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        // set minimum requirements for password
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            etPassword.setError("Password should be greater than 4 alphanumeric characters");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        // set minimum requirements for password
        if (confirmPassword.isEmpty() || confirmPassword.length() < 4 || confirmPassword.length() > 10) {
            etConfirmPassword.setError("Password should be greater than 4 alphanumeric characters");
            valid = false;
        } else {
            etConfirmPassword.setError(null);
        }

        // check if passwords match
        if (!confirmPassword.equals(password)) {
            etConfirmPassword.setError("Passwords must match");
            valid = false;
        } else {
            etConfirmPassword.setError(null);
        }


        return valid;
    }
}