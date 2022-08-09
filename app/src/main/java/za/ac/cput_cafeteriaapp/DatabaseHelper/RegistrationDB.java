/*
    Breyton Ernszten - 217203027
    25 July 2022
 */
package za.ac.cput_cafeteriaapp.DatabaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RegistrationDB extends SQLiteOpenHelper {
    //set up the "Registration" database + table, attributes
    public static final String DB_NAME = "cafeteriaDB.db";

    public static final String TABLE_1_NAME = "registration";
    public static final String USER_NAME = "user_name";
    public static final String EMAIL = "email_address";
    public static final String PASSWORD = "password";
    public static final String CONFIRMED_PASSWORD = "confirm_password";

    public static final String TABLE_2_NAME = "customer_order";
    public static final String MEAL_CODE = "code";
    public static final String EMAIL_ADDRESS = "email_address";//Foreign key
    public static final String MEAL_NAME = "meal_name";
    public static final String MEAL_DESCRIPTION = "description";
    public static final String MEAL_PRICE = "price";

public RegistrationDB(Context context){
    super(context,DB_NAME,null,1);
}

// create database table
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_1_NAME + " (user_name TEXT, email_address TEXT PRIMARY KEY, password TEXT, confirm_password TEXT)");
    db.execSQL("CREATE TABLE " + TABLE_2_NAME + " (code INTEGER PRIMARY KEY AUTOINCREMENT, user_email TEXT, meal_name TEXT, description TEXT, price TEXT, FOREIGN KEY('user_email') REFERENCES " +
            TABLE_1_NAME + "('email_address'))");

    }

    //drops the old table and creates the latest table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_1_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_2_NAME);
    onCreate(db);

    }
}
