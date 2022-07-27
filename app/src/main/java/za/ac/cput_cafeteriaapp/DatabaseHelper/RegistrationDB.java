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
    private static final String DB_NAME = "cafeteriaDB.db";

    private static final String TABLE_1_NAME = "registration";
    private static final String USER_NAME = "user_name";
    private static final String EMAIL = "email_address";
    private static final String PASSWORD = "password";
    private static final String CONFIRMED_PASSWORD = "confirm_password";

    private static final String TABLE_2_NAME = "customer_order";
    private static final String MEAL_CODE = "code";
    private static final String EMAIL_ADDRESS = "email_address";//Foreign key
    private static final String MEAL_NAME = "meal_name";
    private static final String MEAL_DESCRIPTION = "description";
    private static final String MEAL_PRICE = "price";

public RegistrationDB(Context context){
    super(context,DB_NAME,null,1);
}

// create database table
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_1_NAME + " (user_name TEXT, email_address TEXT PRIMARY KEY, password TEXT, confirm_password)");
    db.execSQL("CREATE TABLE " + TABLE_2_NAME + " (code INTEGER PRIMARY KEY AUTOINCREMENT, meal_name TEXT, description TEXT, price TEXT, FOREIGN KEY('email_address') REFERENCES " +
            TABLE_1_NAME + "('id'))");

    }

    //drops the old table and creates the latest table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_1_NAME);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_2_NAME);
    onCreate(db);

    }
}
