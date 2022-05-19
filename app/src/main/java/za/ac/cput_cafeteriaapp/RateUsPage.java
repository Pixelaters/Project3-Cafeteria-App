package za.ac.cput_cafeteriaapp;
//RateUs Page.
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RateUsPage extends AppCompatActivity implements View.OnClickListener{

    private RatingBar ratingBar;

    private EditText feedBack;

    private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateuspage);

        addListenerOnButton();
    }

    private void addListenerOnButton() {

        //feedBack=(EditText) findViewById(R.id.feedBack);
        btnSubmit = (Button) findViewById(R.id.button);

        btnSubmit.setOnClickListener(new View.OnClickListener(){

            private Integer integer;

            @Override
            public void onClick(View view) {
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

    }
}
