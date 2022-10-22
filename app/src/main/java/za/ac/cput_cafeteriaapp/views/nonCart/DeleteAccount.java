package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        final EditText email = findViewById(R.id.deleteEmail);
        final EditText password = findViewById(R.id.deletePassword);

        Button delete = findViewById(R.id.finalDelete);
        Button cancel = findViewById(R.id.cancelDeletion);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteuser(email.getText().toString(),password.getText().toString());
                startActivity(new Intent(DeleteAccount.this,WelcomePage.class));
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(DeleteAccount.this, UserSettings.class);
                startActivity(back);
            }
        });


    }

    private void deleteuser(String email, String password) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //get auth credentials from the user re authentication
        AuthCredential credential = EmailAuthProvider.getCredential(email, password);

        if (user !=null){
            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            user.delete()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("TAG", "User account deleted.");
                                                startActivity(new Intent(DeleteAccount.this, UserSettings.class));
                                                Toast.makeText(DeleteAccount.this, "Deleted User successfully.", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }
                    });
        }

        user.delete();
    }


}


