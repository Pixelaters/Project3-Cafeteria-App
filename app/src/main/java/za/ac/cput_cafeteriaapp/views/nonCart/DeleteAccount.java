package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.databinding.ActivityDeleteAccountBinding;

public class DeleteAccount extends AppCompatActivity implements View.OnClickListener {
//    private EditText deleteEmail,deletePassword;
//    private Button deleteBtn,cancelBtn;
//
//    private FirebaseAuth mAuth;
//    private ProgressBar progressBar7;


    ActivityDeleteAccountBinding binding;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        binding = ActivityDeleteAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//    deleteBtn = (Button) findViewById(R.id.finalDelete);
//    deleteBtn.setOnClickListener(this);
//
//    cancelBtn = (Button) findViewById(R.id.cancelDeletion);
//    cancelBtn.setOnClickListener(this);
//
//    deleteEmail = (EditText) findViewById(R.id.deleteEmail);
//    deletePassword = (EditText)  findViewById(R.id.deletePassword);
//
//    progressBar7 = (ProgressBar) findViewById(R.id.progressBar);
//
//    mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view){

        String email = binding.deleteEmail.getText().toString();
        String password = binding.deletePassword.getText().toString();
//        if (email.isEmpty()) {
//            deleteEmail.setError("Email is required");
//            deleteEmail.requestFocus();
//            return;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            deleteEmail.setError("Please use the correct email");
//            deleteEmail.requestFocus();
//            return;
//        }
//
//        if (password.isEmpty()){
//            deletePassword.setError("Password is required");
//            deletePassword.requestFocus();
//            return;
//        }
//
//        if (password.length()<6){
//            deletePassword.setError("Password should be at least 6 characters");
//            deletePassword.requestFocus();
//            return;
//        }

        if (!email.isEmpty() && !password.isEmpty()){
            deleteUser(email);
        } else{
            Toast.makeText(this, "Email and Username cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }

    private void deleteUser(String email) {
    reference = FirebaseDatabase.getInstance().getReference("Users");
    reference.child(email).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()){
                Toast.makeText(DeleteAccount.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                binding.deleteEmail.setText("");
            }else{
                Toast.makeText(DeleteAccount.this, "Failed to delete Account", Toast.LENGTH_SHORT).show();
            }
        }
    });


    }

//    private void userDeletion() {
//        String email = deleteEmail.getText().toString();
//        String password = deletePassword.getText().toString();
//
//        if (email.isEmpty()) {
//            deleteEmail.setError("Email is required");
//            deleteEmail.requestFocus();
//            return;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            deleteEmail.setError("Please use the correct email");
//            deleteEmail.requestFocus();
//            return;
//        }
//
//        if (password.isEmpty()){
//            deletePassword.setError("Password is required");
//            deletePassword.requestFocus();
//            return;
//        }
//
//        if (password.length()<6){
//            deletePassword.setError("Password should be at least 6 characters");
//            deletePassword.requestFocus();
//            return;
//        }
//
//        progressBar7.setVisibility(View.VISIBLE);
//
//        mAuth.deleteWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
//          @Override
//          public void OnComplete(@NonNull Task<AuthResult> task){
//              //Delete the user account and returns to Login page
//              if (task.isSuccessful()){
//                  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//
//              }
//          }
//
//
//
//        });
//
//
//
//
//
//
//
//
//
//
//
//        }
    }


