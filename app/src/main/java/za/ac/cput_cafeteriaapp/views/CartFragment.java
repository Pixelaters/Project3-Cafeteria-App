package za.ac.cput_cafeteriaapp.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.List;

import za.ac.cput_cafeteriaapp.R;
import za.ac.cput_cafeteriaapp.adapters.CartListAdapter;
import za.ac.cput_cafeteriaapp.databinding.FragmentCartBinding;
import za.ac.cput_cafeteriaapp.models.CartItem;
import za.ac.cput_cafeteriaapp.repositories.CartRepo;
import za.ac.cput_cafeteriaapp.viewmodels.ShopViewModel;
import za.ac.cput_cafeteriaapp.views.nonCart.CheckoutPage;
import za.ac.cput_cafeteriaapp.views.nonCart.LoginPage;
import za.ac.cput_cafeteriaapp.views.nonCart.User;
import za.ac.cput_cafeteriaapp.views.nonCart.UserCart;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;
    LoginPage loginPage;
    DatabaseReference studentMealDbRef;
    UserCart userCart;
    Double totalPrice;



    public CartFragment(String email, String cartItem){

    }

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = FragmentCartBinding.inflate(inflater, container, false);
        return fragmentCartBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        CartListAdapter cartListAdapter = new CartListAdapter(this);

        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        loginPage= new LoginPage();

        studentMealDbRef = FirebaseDatabase.getInstance("https://project-3-cafeteria-app-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("Orders");





        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size()> 0);
            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                totalPrice = aDouble;
                fragmentCartBinding.orderTotalTextView.setText("Total: R" + aDouble.toString());
            }
        });

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                Log.d(TAG, "User" + LoginPage.user.getEmail());
//                Log.d(TAG, "onClick: " + shopViewModel.listCartItems());
                //studentMealDbRef.push().setValue(LoginPage.user.getEmail() + shopViewModel.listCartItems());
//                userCart= new UserCart(LoginPage.user.getEmail(),shopViewModel.listCartItems() );
//                studentMealDbRef.push().setValue(userCart);
//                navController.navigate(R.id.action_cartFragment_to_orderFragment);
                      Intent intent = new Intent(getActivity(), CheckoutPage.class);
                intent.putExtra("amount", String.format("%.2f", totalPrice));
                startActivity(intent);

            }
        });
    }

    @Override
    public void deleteItem(CartItem cartItem) {
        shopViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem, int quantity) {
        shopViewModel.changeQuantity(cartItem, quantity);
        Log.d(TAG, "changeQuantity: " + cartItem.getProduct());
    }

    private void SendCartData(){

    }



}