package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.databinding.ActivityDeleteAccountBinding;
import za.ac.cput_cafeteriaapp.views.ShopFragment;

public class DeleteAccount extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#22378d"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);


        progressBar = findViewById(R.id.progressBar);



        Button delete = findViewById(R.id.finalDelete);
        Button cancel = findViewById(R.id.cancelDeletion);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DeleteAccount.this);
                dialog.setTitle("Are you sure");
                dialog.setMessage("You will really lose all your data?");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressBar.setVisibility(View.VISIBLE);
                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(DeleteAccount.this, "User account is deleted", Toast.LENGTH_LONG).show();
                                 Intent intent = new Intent(DeleteAccount.this, WelcomePage.class);
                                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                 startActivity(intent);
                                }   else{
                                    Toast.makeText(DeleteAccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });

                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

            }
        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                progressBar.setVisibility(View.VISIBLE);
////                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
////                    @Override
////                    public void onComplete(@NonNull Task<Void> task) {
////                        progressBar.setVisibility(View.GONE);
////                    if (task.isSuccessful()) {
////                        deleteuser(email.getText().toString(), password.getText().toString());
////                        Toast.makeText(DeleteAccount.this, "account deleted", Toast.LENGTH_LONG).show();
////
////                        Intent intent = new Intent(DeleteAccount.this, WelcomePage.class);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                        startActivity(intent);
////                    }else{
////                        Toast.makeText(DeleteAccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
////                    }
////                    }
////                });
//
//            }
//        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(DeleteAccount.this, UserSettings.class);
                startActivity(back);
            }
        });


    }

//    private void deleteuser(String email, String password) {
//        firebaseUser.getEmail();
//
//        //get auth credentials from the user re authentication
//        AuthCredential credential = EmailAuthProvider.getCredential(email, password);
//
//        if (firebaseUser !=null){
//            firebaseUser.reauthenticate(credential)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            firebaseUser.delete()
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Log.d("TAG", "User account deleted.");
//                                                startActivity(new Intent(DeleteAccount.this, WelcomePage.class));
//                                                Toast.makeText(DeleteAccount.this, "Deleted User successfully.", Toast.LENGTH_LONG).show();
//
//
//                                            } else{
//                                                Toast.makeText(DeleteAccount.this, "", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    });
//                        }
//                    });
    }








