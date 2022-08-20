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
        productList.add(new Product(UUID.randomUUID().toString(), "iMac 21", 1299, true, "https://allthingsmacc.co.za/product/apple-mac-21-inch-3-0ghz-quad-core-i5/" ));
        productList.add(new Product(UUID.randomUUID().toString(), "iPad Air", 799, true, "https://www.istore.co.za/media/catalog/product/i/p/ipad_air_cellular_starlight_pdp_image_position-1b_5g__wwen_1_1.png?format=jpeg"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPad Pro", 999, true, "https://www.incredible.co.za/media/catalog/product/cache/7ce9addd40d23ee411c2cc726ad5e7ed/s/p/space_grey____position_1__dfe8.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11", 699, false, "https://firebasestorage.googleapis.com/v0/b/notes-16738.appspot.com/o/products%2Fiphone11.jpeg?alt=media&token=c6874af2-c81e-48eb-96e9-2f1f3fad617f"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11 Pro", 999, true, "https://www.istore.co.za/media/catalog/product/m/h/mhda3aaa_1.jpeg?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 11 Pro Max", 1099, true, "https://assets.swappie.com/cdn-cgi/image/width=600,height=600,fit=contain,format=auto/swappie-iphone-11-pro-max-space-gray.png?v=5"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone SE", 399, true, "https://www.istore.co.za/media/catalog/product/i/p/iphone_se_starlight_pure_back_iphone_se_starlight_pure_front_2-up_screen__usen.png?format=jpeg"));
        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Air", 999, true, "https://www.istore.co.za/media/catalog/product/m/g/mgnd3zea.jpeg?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700"));
        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Pro 13", 1299, true, "https://www.istore.co.za/media/catalog/product/m/w/mwp72zea.jpeg?optimize=medium&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700"));
        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Pro 16", 2399, true, "https://www.shopandship.co.za/images/virtuemart/product/NEWMBPSILVER%202CUST.jpg"));

        mutableProductList.setValue(productList);
    }
}

