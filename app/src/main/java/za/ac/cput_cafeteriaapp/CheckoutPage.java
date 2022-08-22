package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class CheckoutPage extends AppCompatActivity {

    CardForm cardForm;
    Button btnPay;
    AlertDialog.Builder alertBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);


        cardForm = findViewById(R.id.cardform);
        btnPay = findViewById(R.id.btnPay);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(CheckoutPage.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cardForm.isValid()){
                    alertBuilder = new AlertDialog.Builder(CheckoutPage.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Caed number: "+ cardForm.getCardNumber() +"\n"+
                            "Crd expiry date: "+ cardForm.getExpirationDateEditText().getText().toString()+ "\n"+
                            "Crd Cvv: "+ cardForm.getCvv() + "\n" +
                            "Postal code: " + cardForm.getPostalCode()+ "\n"+
                            "Phone number: " + cardForm.getMobileNumber());

                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(CheckoutPage.this,"Thank you for purchase",Toast.LENGTH_LONG).show();

                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog=alertBuilder.create();
                    alertDialog.show();
                }else{
                    Toast.makeText(CheckoutPage.this,"Please complete the form",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
