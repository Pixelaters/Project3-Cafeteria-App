package za.ac.cput_cafeteriaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

public class OrdersPage extends AppCompatActivity {

//    public DrawerLayout drawerLayout;
//    public ActionBarDrawerToggle actionBartoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderspage);
//
//        drawerLayout= findViewById(R.id.drawerLayout);
//        actionBartoogle = new ActionBarDrawerToggle(this, drawerLayout,R.string.navOpen,R.string.navClose);
//
//        drawerLayout.addDrawerListener(actionBartoogle);
//        actionBartoogle.syncState();
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item){
//
//        if(actionBartoogle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
