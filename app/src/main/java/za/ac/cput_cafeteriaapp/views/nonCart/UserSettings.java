package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import za.ac.cput_cafeteriaapp.databinding.ActivityUserSettingsBinding;
import za.ac.cput_cafeteriaapp.views.ShopFragment;

public class UserSettings extends AppCompatActivity {
    private EditText name;
    private Button btnSave, btnChange, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        final EditText name = findViewById(R.id.nameUpdate);
        
        btnSave = findViewById(R.id.detailsUpdate);
        btnChange = findViewById(R.id.passwordChange);
        btnDelete = findViewById(R.id.deleteAccount);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                updateUser(name.getText().toString());
            }
        });


        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change = new Intent(UserSettings.this, UpdatePassword.class);
                startActivity(change);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserSettings.this,DeleteAccount.class));
            }
        });





    }

    private void updateUser(String name) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                        .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.d(name, "user profile updated");
                        }
                    }
                });

    }


}