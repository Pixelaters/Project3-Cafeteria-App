package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.databinding.ActivityUpdatePasswordBinding;

public class UpdatePassword extends AppCompatActivity {
    ActivityUpdatePasswordBinding binding;
    DatabaseReference databaseReference;
    private TextView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cancel = findViewById(R.id.back);






        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String old = binding.oldPass.getText().toString();
                String nPass = binding.password.getText().toString();
                String con = binding.conPassword.getText().toString();

                updateData(nPass, con);




            }
        });



    }





    private void updateData(String nPass, String con) {
        HashMap user = new HashMap();
        user.put("PASSWORD",nPass);


        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(nPass).updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    binding.oldPass.setText("");
                    binding.password.setText("");
                    binding.conPassword.setText("");

                    Toast.makeText(UpdatePassword.this, "Password updated succcessfully", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(UpdatePassword.this, "Failed to update the password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}