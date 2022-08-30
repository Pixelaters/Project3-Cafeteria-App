package za.ac.cput_cafeteriaapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.List;

import za.ac.cput_cafeteriaapp.adapters.CartListAdapter;
import za.ac.cput_cafeteriaapp.databinding.FragmentCartBinding;
import za.ac.cput_cafeteriaapp.models.CartItem;
import za.ac.cput_cafeteriaapp.viewmodels.ShopViewModel;
import za.ac.cput_cafeteriaapp.views.nonCart.CheckoutPage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment #newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG = "CartFragment";
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;
    Double totalPrice;

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


        CartListAdapter cartListAdapter = new CartListAdapter(this);

        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        shopViewModel= new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
            }
        });

        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                totalPrice = aDouble;
                fragmentCartBinding.orderTotalTextView.setText("Total: R" + aDouble.toString());
            }
        });
        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
    }
}