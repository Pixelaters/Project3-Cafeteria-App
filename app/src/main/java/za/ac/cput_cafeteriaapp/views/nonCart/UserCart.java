package za.ac.cput_cafeteriaapp.views.nonCart;

import java.util.List;

public class UserCart {
    String userEmail;
    List product;

    public UserCart(String userEmail, List product) {
        this.userEmail = userEmail;
        this.product = product;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List getProduct() {
        return product;
    }

    public void setProduct(List product) {
        this.product = product;
    }


    @Override
    public String toString() {
        return "userCart{" +
                "userEmail='" + userEmail + '\'' +
                ", product=" + product +
                '}';
    }
}
