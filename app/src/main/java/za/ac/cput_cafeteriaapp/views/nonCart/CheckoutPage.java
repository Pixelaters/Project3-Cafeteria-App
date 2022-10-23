package za.ac.cput_cafeteriaapp.views.nonCart;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.views.ProductDetailFragment;

public class CheckoutPage extends AppCompatActivity {


    EditText etCardNumber, etExpirationDate, etCVV, etPostalCode, etCountryCode, etMobileNumber;
    TextView displayAmount;
    Button btnPay;
    AlertDialog.Builder alertBuilder;
    String amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);
              // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
            = new ColorDrawable(Color.parseColor("#22378d"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            amount = extras.getString("amount");
            System.out.println(amount);
        }


//        find views
        etCardNumber = findViewById(R.id.card_number);
        etExpirationDate = findViewById(R.id.expiration_date);
        etCVV = findViewById(R.id.cvv);
        etPostalCode = findViewById(R.id.postal_code);
        etCountryCode = findViewById(R.id.country_code);
        etMobileNumber = findViewById(R.id.mobile_number);
        displayAmount = findViewById(R.id.amount);
        btnPay = findViewById(R.id.pay_button);

        displayAmount.setText('R' + amount);


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (infoIsValid()) {
                    alertBuilder = new AlertDialog.Builder(CheckoutPage.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number: " + etCardNumber.getText().toString() + "\n" +
                            "Card expiry date: " + etExpirationDate.getText().toString() + "\n" +
                            "Card Cvv: " + etCVV.getText().toString() + "\n" +
                            "Postal code: " + etPostalCode.getText().toString() + "\n" +
                            "Phone number: " + etCountryCode.getText().toString() + etMobileNumber.getText().toString() + "\n" +
                            "Amount to pay: R" + amount

                    );


                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent inten = new Intent(CheckoutPage.this, ProductDetailFragment.class);
                            startActivity(inten);
                            Toast.makeText(CheckoutPage.this, "Thank you for the purchase", Toast.LENGTH_LONG).show();

                        }
                    });
                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                } else {
                    Toast.makeText(CheckoutPage.this, "Please complete the form", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean infoIsValid() {
        boolean valid;

        String cardNumber = etCardNumber.getText().toString();
        String expirationDate = etExpirationDate.getText().toString();
        String cvv = etCVV.getText().toString();
        String postalCode = etPostalCode.getText().toString();
        String countryCode = etCountryCode.getText().toString().replaceAll("[^\\d]", "");
        String mobileNumber = etMobileNumber.getText().toString();

        // validate username / email field
        if (cardNumber.isEmpty() || expirationDate.isEmpty() || cvv.isEmpty() || postalCode.isEmpty() || countryCode.isEmpty() || mobileNumber.isEmpty()) {
            etCardNumber.setError("Field must not be empty");
            etExpirationDate.setError("Field must not be empty");
            etCVV.setError("Field must not be empty");
            etPostalCode.setError("Field must not be empty");
            etCountryCode.setError("Field must not be empty");
            etMobileNumber.setError("Field must not be empty");
            valid = false;
        } else if (!cardNumberIsValid(cardNumber)) {
            etCardNumber.setError("Enter a valid card number");
            valid = false;
        } else if (expirationDate.isEmpty()) {
            etExpirationDate.setError("Enter a valid date");
            valid = false;
        } else if (cvv.isEmpty()) {
            etCVV.setError("Enter valid cvv");
            valid = false;
        } else if (postalCode.isEmpty()) {
            etCVV.setError("Enter valid postal code");
            valid = false;
        } else if (countryCode.isEmpty()) {
            etCountryCode.setError("Enter valid postal code");
            valid = false;
        } else if (mobileNumber.isEmpty() || mobileNumber.length() < 8 || !TextUtils.isDigitsOnly(mobileNumber)) {
            etCVV.setError("Enter valid mobile number");
            valid = false;
        } else {
            etCardNumber.setError(null);
            etExpirationDate.setError(null);
            etCVV.setError(null);
            etPostalCode.setError(null);
            etCountryCode.setError(null);
            etMobileNumber.setError(null);
            valid = true;
        }


        return valid;
    }

    public boolean cardNumberIsValid(String cardNumber) {
        int minCardLength = 16;
        int maxCardLength = 19;
        if (TextUtils.isEmpty(cardNumber)) {
            return false;
        } else if (!TextUtils.isDigitsOnly(cardNumber)) {
            return false;
        } else {
            int numberLength = cardNumber.length();
            return numberLength >= minCardLength && numberLength <= maxCardLength;

        }
    }


}
