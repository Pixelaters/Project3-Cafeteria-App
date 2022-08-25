package za.ac.cput_cafeteriaapp.views.nonCart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import za.ac.cput_cafeteriaapp.R;

public class DeleteAccount extends AppCompatActivity {
    private TextView txtLinkDelete;
    private TextView txtLinkCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        txtLinkDelete = findViewById(R.id.finalDelete);
        txtLinkCancel = findViewById(R.id.cancelDeletion);

        txtLinkDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(DeleteAccount.this,LoginPage.class);
                startActivity(delete);
            }
        });

        txtLinkCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(DeleteAccount.this, UserSettings.class);
                startActivity(cancel);
            }
        });
    }
}