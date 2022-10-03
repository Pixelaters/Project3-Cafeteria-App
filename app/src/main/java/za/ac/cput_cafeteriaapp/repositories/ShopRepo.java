package za.ac.cput_cafeteriaapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import za.ac.cput_cafeteriaapp.models.Product;

public class ShopRepo {

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts(){
        if (mutableProductList== null){
            mutableProductList= new MutableLiveData<>();
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(), "Bread with egg", "Toasted bread with a well cooked egg", 59.99 ,true, "https://images.unsplash.com/photo-1525351484163-7529414344d8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880&q=80" ));
        productList.add(new Product(UUID.randomUUID().toString(), "Blueberry Toast", "Toasted bread with blueberry toppings covered in almond butter",69.99, true, "https://images.unsplash.com/photo-1484723091739-30a097e8f929?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=749&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Vegetable Salad", "For the vegetarians and healthy eaters, contains lettuce, broccoli onion and cauliflower ",89.99, true, "https://images.unsplash.com/photo-1513442542250-854d436a73f2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=947&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Egg Sandwich", "Toasted bread with a well cooked egg sliced up to your liking",59.99, false, "https://images.unsplash.com/photo-1482049016688-2d3e1b311543?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=710&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Berry pancake", "For those morning meals, a pancake with strawberries, blueberries and honey",99.99, true, "https://images.unsplash.com/photo-1506084868230-bb9d95c24759?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Waffles", "Perfectly circular waffles covered in chocolate syrup and strawberries",99.99, true, "https://images.unsplash.com/photo-1594627882045-57465104050f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Pancakes","Perfectly circular pancakes covered in chocolate syrup and strawberries", 99.99, true, "https://images.unsplash.com/photo-1554520735-0a6b8b6ce8b7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=764&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Vegetable salad", "For the vegetarians and healthy eaters, contains lettuce, broccoli onion and cauliflower",119.99, true, "https://images.unsplash.com/photo-1546793665-c74683f339c1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Chicken salad", "For the hardworking gymnist, you cannot go wrong with the protein filled in this chicken salad",129.99, true, "https://images.unsplash.com/photo-1594834749740-74b3f6764be4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=691&q=80"));
        productList.add(new Product(UUID.randomUUID().toString(), "Steak", "Well cooked steak with vegetables on the side, and the steak sauce being the cherry on top ",149.99, true, "https://images.unsplash.com/photo-1601356616077-695728ae17cb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));

        mutableProductList.setValue(productList);
    }
}

