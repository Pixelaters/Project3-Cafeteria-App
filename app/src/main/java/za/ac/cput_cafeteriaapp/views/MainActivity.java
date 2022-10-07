package za.ac.cput_cafeteriaapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.models.CartItem;
import za.ac.cput_cafeteriaapp.viewmodels.ShopViewModel;
import za.ac.cput_cafeteriaapp.views.nonCart.Homepage;
import za.ac.cput_cafeteriaapp.views.nonCart.RateUsPage;
import za.ac.cput_cafeteriaapp.views.nonCart.UserSettings;
import za.ac.cput_cafeteriaapp.views.nonCart.WelcomePage;

//VIDEO ENDED AT EP 12 MID WAY
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    NavController navController;
    ShopViewModel shopViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //potentially broken code here check https://stackoverflow.com/questions/50502269/illegalstateexception-link-does-not-have-a-navcontroller-set for more info
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        shopViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        shopViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                Log.d(TAG, "onChanged: "+ cartItems.size());
            }
        });

//        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this,navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.navLogOut:
                homePage();
                return true;

            case R.id.navRateUs:
                rateUsPage();
                return true;

            case R.id.navAccount:
                accountPage();
                return true;

            default:
                return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
        }
    }

    public void homePage(){
        Intent goBack = new Intent(this, WelcomePage.class);
        startActivity(goBack);
    }

    public void rateUsPage(){
        Intent rateUs = new Intent(this, RateUsPage.class);
        startActivity(rateUs);
    }

    public void accountPage(){
        Intent userAccount = new Intent(this, UserSettings.class);
        startActivity(userAccount);
    }
}