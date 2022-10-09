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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.views.MainActivity;

public class RateUsPage extends AppCompatActivity{
    AppCompatButton rateUsBtn;
    AppCompatButton dontRateBtn;
    public RatingBar ratingBar;
    ImageView emojiPic;

     float userRate = 0;

    public RateUsPage() {
        //super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rateuspage);

        rateUsBtn = findViewById(R.id.rateUs);
        dontRateBtn = findViewById(R.id.dontRateUs);
        ratingBar = findViewById(R.id.rateUsBar);
        emojiPic = findViewById(R.id.emoji);

        rateUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code for database
            }
        });

        dontRateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss();
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

}

