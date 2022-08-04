package za.ac.cput_cafeteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderTakeAway extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private TextView txtQuantity, txtTotalPrice;
    private ImageView btnAdd, btnMinus;
    private Button btnAddToCart;
    private Double ITEM_PRICE = 100.0;
    private Double totalPrice;
    private int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_take_away);

        //initialise progress progress dialog
        progressDialog = new ProgressDialog(this, ProgressDialog.THEME_HOLO_LIGHT);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);

        //link variables with xml views
        txtQuantity = findViewById(R.id.quantity);
        btnAdd = findViewById(R.id.plusCardBtn);
        btnMinus = findViewById(R.id.minusCardBtn);
        txtTotalPrice = findViewById(R.id.totalPriceTxt);
        btnAddToCart = findViewById(R.id.addToCartBtn);

        //    initialise total price
        String initialTotalPrice = "R " + String.format("%.2f", ITEM_PRICE);
        txtTotalPrice.setText(initialTotalPrice);

        //change order quantity
        changeOrderQuantity();
        //checkout order
        checkoutOrder();
    }

    private void changeOrderQuantity() {

        //listen to clicks on add button
        btnAdd.setOnClickListener(view -> {
            quantity++;
            //update quantity textfield
            txtQuantity.setText(Integer.toString(quantity));
            totalPrice = ITEM_PRICE * quantity;
            String totalPriceText = "R " + String.format("%.2f", totalPrice);
            //display total price
            txtTotalPrice.setText(totalPriceText);

        });

        //listen to clicks on minus button
        btnMinus.setOnClickListener(view -> {
            if (quantity != 1) {
                quantity--;
                //update quantity textfield
                txtQuantity.setText(Integer.toString(quantity));
                totalPrice = ITEM_PRICE * quantity;
                String displayText = "R " + String.format("%.2f", totalPrice);
                //display total price
                txtTotalPrice.setText(displayText);
            }

        });

    }

    private void checkoutOrder() {
        btnAddToCart.setOnClickListener(view -> {
            progressDialog.setMessage("Adding order to cart...");
            showDialog();
            // TODO Logic for adding to cart and checking out
            hideDialog();

        });
    }

    private void showDialog() {
        // display progress dialog if not already shown
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        //hide the progress dialog if it is already showing
        if (progressDialog.isShowing()) {
            // handler to delay hiding the progress dialog
            //  so as to simulate adding to cart
            new Handler().postDelayed(() -> {
                progressDialog.dismiss();

                //  display toast message
                Toast toast = Toast.makeText(getApplicationContext(), "Order added to cart", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }, 5000);
        }
    }
}