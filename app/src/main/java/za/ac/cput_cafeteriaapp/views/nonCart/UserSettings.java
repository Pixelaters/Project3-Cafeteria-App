package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.databinding.ActivityUserSettingsBinding;

public class UserSettings extends AppCompatActivity {
    ActivityUserSettingsBinding binding;
    DatabaseReference databaseReference;
    private Button btnSave, btnChange, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnSave = findViewById(R.id.detailsUpdate);
        btnChange = findViewById(R.id.passwordChange);
        btnDelete = findViewById(R.id.deleteAccount);


        binding.detailsUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.usernameUpdate.getText().toString();
                String name = binding.nameUpdate.getText().toString();

                updateData(name, username);
            }
        });



    }

    private void updateData(String name, String username) {

        HashMap user = new HashMap();
        user.put("Username", username);
        user.put("Name",name);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(name + username).updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    binding.usernameUpdate.setText("");
                    binding.nameUpdate.setText("");

                    Toast.makeText(UserSettings.this, "Name/username  updated successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserSettings.this, "Name/username could not be found or updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}