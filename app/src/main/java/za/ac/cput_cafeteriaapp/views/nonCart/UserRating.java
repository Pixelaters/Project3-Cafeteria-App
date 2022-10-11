package za.ac.cput_cafeteriaapp.views.nonCart;

public class UserRating {

    private String email;
    private float rating;

    public UserRating(String email, float rating) {
        this.email = email;
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "email='" + email + '\'' +
                ", rating=" + rating +
                '}';
    }
}
