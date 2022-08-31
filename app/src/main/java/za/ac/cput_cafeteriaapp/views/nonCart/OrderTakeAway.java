package za.ac.cput_cafeteriaapp.views.nonCart;

import static za.ac.cput_cafeteriaapp.models.Product.loadImage;
import static za.ac.cput_cafeteriaapp.views.ShopFragment.shopViewModel;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.models.Product;

public class OrderTakeAway extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private TextView txtQuantity, txtPrice, txtTotalPrice, txtOrderName, txtAvailability;
    private ImageView ivFoodPic;
    private ImageView btnAdd, btnMinus;
    private Button btnAddToCart;
    private Double totalPrice;
    private int quantity = 1;
    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_take_away);
//         shopViewModel= new ViewModelProvider(this).get(ShopViewModel.class);
        product = shopViewModel.getProduct().getValue();

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
        ivFoodPic = findViewById(R.id.foodPic);
        txtOrderName = findViewById(R.id.orderNameTxt);
        txtAvailability = findViewById(R.id.availabilityTxt);
        txtPrice = findViewById(R.id.item_price);

        //    initialise order details
        String initialTotalPrice = "R " + String.format("%.2f", product.getPrice());
        txtTotalPrice.setText(initialTotalPrice);
        txtOrderName.setText(product.getName());
        txtAvailability.setText(product.isAvailable() ? "Available" : "Not Available");
        loadImage(ivFoodPic, product.getImageUrl());
        txtPrice.setText(initialTotalPrice);


        //change order quantity
        changeOrderQuantity();
        //add to cart
        addToCart();
    }

    private void changeOrderQuantity() {

        //listen to clicks on add button
        btnAdd.setOnClickListener(view -> {
            quantity++;
            //update quantity textfield
            txtQuantity.setText(Integer.toString(quantity));
            totalPrice = product.getPrice() * quantity;
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
                totalPrice = product.getPrice() * quantity;
                String displayText = "R " + String.format("%.2f", totalPrice);
                //display total price
                txtTotalPrice.setText(displayText);
            }

        });

    }

    private void addToCart() {
        btnAddToCart.setOnClickListener(view -> {

            progressDialog.setMessage("Adding order to cart...");
            showDialog();
            boolean isAdded = shopViewModel.addItemToCart(product);
            if (!isAdded) {
                new Handler().postDelayed(() -> {
                    hideDialog();
                    //  display toast message
                    Toast toast = Toast.makeText(getApplicationContext(), "Failed to  add order to cart", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }, 1000);

            } else {

                new Handler().postDelayed(() -> {
                    hideDialog();
                    Snackbar.make(view, product.getName() + " successfully added to cart.", Snackbar.LENGTH_LONG)
                            .setAction("Checkout", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    finish();
                                }
                            })
                            .show();
                }, 1000);
            }


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
            progressDialog.dismiss();
        }
    }
}