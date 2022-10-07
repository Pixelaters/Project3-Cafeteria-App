package za.ac.cput_cafeteriaapp.repositories;
//Ziyaad
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput_cafeteriaapp.models.CartItem;
import za.ac.cput_cafeteriaapp.models.Product;

public class CartRepo {

    private static final String TAG = "Cart repo";
    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();

    private MutableLiveData<Double> mutableTotalPrice= new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if (mutableCart.getValue() == null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }

    public boolean addItemToCart(Product product){
        if (mutableCart.getValue()== null){
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for (CartItem cartItem: cartItemList){
            if (cartItem.getProduct().getId().equals(product.getId())){
                if (cartItem.getQuantity() == 5){
                    return false;
                }

                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);

                mutableCart.setValue(cartItemList);
                calculateCartTotal();
                return true;
            }
        }

        CartItem cartItem = new CartItem(product, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItem cartItem){
        if (mutableCart.getValue()== null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    public void changeQuantity(CartItem cartItem, int quantity){
        if (mutableCart.getValue()== null) return;

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        CartItem updatedItem = new CartItem(cartItem.getProduct(), quantity);
        cartItemList.set(cartItemList.indexOf(cartItem), updatedItem);

        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    private void calculateCartTotal(){
        if (mutableCart.getValue()== null) return;
        double total=0.0;
        List<CartItem> cartItemList = mutableCart.getValue();
        for (CartItem cartItem: cartItemList){
            total+= cartItem.getProduct().getPrice()* cartItem.getQuantity();

        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Double> getTotalPrice(){
        if (mutableTotalPrice.getValue()==null){
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }

    public List listCartItems(){
        ArrayList<String> products = new ArrayList<String>();
        List<CartItem> cartItemList = mutableCart.getValue();
        for (CartItem cartItem: cartItemList){
            products.add(cartItem.getProduct().getName());
        }
        return products;

    }

}

