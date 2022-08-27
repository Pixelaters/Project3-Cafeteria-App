package za.ac.cput_cafeteriaapp.views.nonCart;
//RateUs Page.

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import za.ac.cput_cafeteriaapp.R;

public class RateUsPage extends AppCompatActivity implements View.OnClickListener{

    private RatingBar ratingBar;

    private EditText feedBack;

    private Button btnSubmit;
    private Switch mSwitch;
//    private myTask task;

    SQLiteOpenHelper db_OpenHelper;
    SQLiteDatabase rateUsPageDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateuspage);

        btnSubmit = (Button) findViewById(R.id.btn_succeed);

        btnSubmit.setOnClickListener(this);


    }

    private void addListenerOnButton() {

        //feedBack=(EditText) findViewById(R.id.feedBack);
        btnSubmit = (Button) findViewById(R.id.button);

        btnSubmit.setOnClickListener(this);

        btnSubmit.setOnClickListener(new View.OnClickListener(){

            private Integer integer;

            @Override
            public void onClick(View view) {

                EditText editText = (EditText) findViewById(R.id.textInputLayout);

                Log.i("Info" , editText.getText().toString());

                String value1 = feedBack.getText().toString();

                int feedback1 = integer.parseInt(value1);

                int a =integer.parseInt(value1);
                Toast.makeText(getApplicationContext(),String.valueOf(feedback1),Toast.LENGTH_LONG);
            }
        });
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_succeed:
                Toast.makeText(this, "Success" , Toast.LENGTH_SHORT).show();
                break;
        }

    }

//    @Override
//    protected void onProgressUpdate(Integer... values){
//        btnSubmit.setOnClickListener(value[0]);
//    }

}
