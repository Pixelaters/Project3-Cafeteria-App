package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.databinding.ActivityUpdatePasswordBinding;

public class UpdatePassword extends AppCompatActivity {

    final EditText old = findViewById(R.id.oldPass);
    final EditText nPass = findViewById(R.id.password);
    final EditText con = findViewById(R.id.conPass);
    
    Button btnUpdate = findViewById(R.id.changePasswordBtn);
    Button btnCancel = findViewById(R.id.Cancel);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);








        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String password = old.getText().toString();
                String newPass = nPass.getText().toString();
                String conPass = con.getText().toString();
                UserProfileChangeRequest passwordchange = new UserProfileChangeRequest.Builder()
                        .setDisplayName(password)
                        .build();
                
                user.updateProfile(passwordchange)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(UpdatePassword.this, "User password updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                




            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });



    }

    private  void previous() {
        Intent back = new Intent(this,UserSettings.class);
        startActivity(back);


    }



    }




