package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Homepage extends AppCompatActivity {
    //comment to explain
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void order(View view) {
        Intent intent = new Intent(getApplicationContext(), OrderTakeAway.class);
        startActivity(intent);
    }
}