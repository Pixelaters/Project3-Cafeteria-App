package za.ac.cput_cafeteriaapp.views.nonCart;
//RateUs Page.
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.views.CartFragment;
import za.ac.cput_cafeteriaapp.views.MainActivity;

public class RateUsPage extends AppCompatActivity{
    DatabaseReference userRating;
    LoginPage loginPage;
    UserRating rating;

    AppCompatButton rateUsBtn;
    AppCompatButton dontRateBtn;
    public RatingBar ratingBar;
    ImageView emojiPic;

     float userRate = 0;

    public RateUsPage() {
        //super();
    }

    public RateUsPage(String email,float rating){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateuspage);

        userRating = FirebaseDatabase.getInstance("https://project-3-cafeteria-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("UserRating");
        loginPage = new LoginPage();

        rateUsBtn = findViewById(R.id.rateUs);
        dontRateBtn = findViewById(R.id.dontRateUs);
        ratingBar = findViewById(R.id.rateUsBar);
        emojiPic = findViewById(R.id.emoji);

        rateUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float countUserRate = ratingBar.getRating();

                rating = new UserRating(LoginPage.user.getEmail(),countUserRate);
                userRating.push().setValue(rating);
                Toast.makeText(RateUsPage.this,"Thank you for rating us",Toast.LENGTH_LONG).show();
                goToCartFragment();
                //code for database
            }
        });

        dontRateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if(rating <= 1){
                    emojiPic.setImageResource(R.drawable.one_star);

                }else if(rating <= 2){
                    emojiPic.setImageResource(R.drawable.two_star);

                }else  if(rating <= 3){
                    emojiPic.setImageResource(R.drawable.three_star);

                }else if(rating <= 4){
                    emojiPic.setImageResource(R.drawable.four_star);

                }else if(rating == 5){
                    emojiPic.setImageResource(R.drawable.five_star);
                }

                emoji(emojiPic);
                userRate = rating;

            }
        });

    }

    private void emoji(ImageView ratingEmoji){
        ScaleAnimation animation = new ScaleAnimation(0,1f,0, 1f ,
                Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        animation.setFillAfter(true);
        animation.setDuration(200);
        ratingEmoji.startAnimation(animation);
    }

    public void logOut(){
        Intent logOut = new Intent(this,WelcomePage.class);
        startActivity(logOut);
    }

    public void goToCartFragment(){
        Intent intent = new Intent(this, CartFragment.class);
        startActivity(intent);

    }

}

