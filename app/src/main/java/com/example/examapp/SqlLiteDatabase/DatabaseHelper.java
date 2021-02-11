package com.example.examapp.SqlLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.examapp.Models.ProductModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

import static android.os.Build.ID;
import static com.example.examapp.MainActivity.KEY_AVILVENT;
import static com.example.examapp.MainActivity.KEY_IMAGEURL;
import static com.example.examapp.MainActivity.KEY_PRICE;
import static com.example.examapp.MainActivity.KEY_PRODUCTDATE;
import static com.example.examapp.MainActivity.KEY_PRODUCTID;
import static com.example.examapp.MainActivity.KEY_PRODUCTNAME;
import static com.example.examapp.MainActivity.KEY_PRODUCT_UNIT;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final String PRODUCTS_TABLE = "PRODUCTS_TABLE2";
    public static final String IMAGE_TABLE = "PRODUCTS_IMAGE";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_UNIT = "UNIT";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_AVAIL_INVEN = "AVAIL_INVEN";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_EXPIRYDATE = "EXPIRYDATE";
    public static final String COLOUM_IMAGE = "IMAGE";
    public static final String COLUMN_IMAGEURI = "IMAGEuri";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "products2.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String createTableStatment = "CREATE TABLE " + PRODUCTS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_UNIT + " TEXT, " + COLUMN_PRICE + " DECIMAL, " + COLUMN_AVAIL_INVEN + " INT, " + COLUMN_EXPIRYDATE + " DATE, " + COLUMN_IMAGEURI + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatment);

        String createTableStatmentImage = "CREATE TABLE " + IMAGE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, img blob not null)";
        sqLiteDatabase.execSQL(createTableStatmentImage);
    }




    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists images");
    }

    public boolean insertImage(String x, Integer i){
        // x = image location
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            FileInputStream fs = new FileInputStream(x);
            byte[] imgbyte = new byte[fs.available()];
            fs.read(imgbyte);
            ContentValues contentValues = new ContentValues();

            contentValues.put("img",imgbyte);
            db.insert(IMAGE_TABLE,null,contentValues);
            fs.close();
            return true;

        }
        catch (IOException e) {
            e.printStackTrace();
            return  false;
        }
    }

    public boolean addOne(ProductModel productModel){
         SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT_NAME, productModel.getProductName());
        cv.put(COLUMN_PRICE, productModel.getPrice());
        cv.put(COLUMN_AVAIL_INVEN, productModel.getAvailInven());
        cv.put(COLUMN_UNIT, productModel.getUnit());
        cv.put(COLUMN_EXPIRYDATE, productModel.getExpDate());
        cv.put(COLUMN_IMAGEURI, productModel.getImageUri());


        long insert = db.insert(PRODUCTS_TABLE, null, cv);
            if(insert == -1){
                return  false;

            }else {
                return true;
            }


    }

    public List<ProductModel> selectAllProduct(){

        List<ProductModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + PRODUCTS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            // loop throught the cursor (result set) and create new Customer object put them into return list

            do{
                int productId = cursor.getInt(0);
                String productName = cursor.getString(1);
                String unit = cursor.getString( 2);
                Float price = cursor.getFloat(3);
                Integer availInven = cursor.getInt(4);
                String expirydate = cursor.getString(5);
                String imageUri = cursor.getString(6);




                ProductModel newProduct = new ProductModel(productName, unit,expirydate,price,availInven,productId,imageUri);
                Log.d(TAG, "selectAllProduct: " +productName +"/"+unit+" "+price+"/"+availInven+"/"+expirydate+newProduct.toString());
                returnList.add(newProduct);

            }while (cursor.moveToNext());




        }else{
            // failure do not add anything to the list

        }
        // close the cursor and database when done.
        cursor.close();
        db.close();

        return returnList;

    }

    public boolean deleteOne(String productModel){
        // find product model in the database. if it found delete it and return true.
        // if its not found , return false

        SQLiteDatabase db = this.getWritableDatabase();
        String queryStringDelete ="DELETE FROM " + PRODUCTS_TABLE + " WHERE " + COLUMN_ID + " = " + productModel;

        Cursor cursor = db.rawQuery(queryStringDelete, null);

        if(cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }

    public boolean updateDate(ProductModel productModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT_NAME, productModel.getProductName());
        cv.put(COLUMN_PRICE, productModel.getPrice());
        cv.put(COLUMN_AVAIL_INVEN, productModel.getAvailInven());
        cv.put(COLUMN_UNIT, productModel.getUnit());
        cv.put(COLUMN_EXPIRYDATE, productModel.getExpDate());
        cv.put(COLUMN_IMAGEURI, productModel.getImageUri());

        db.update(PRODUCTS_TABLE,cv,"ID = ?",new String[]{ KEY_PRODUCTID } );

        return true;
    }

//    INSERTING IMG IN OTHER DB
/*
    public Bitmap getimage(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("select * from  " + IMAGE_TABLE + " where id = ? ", new String[] {"1"});
        if(cursor.moveToNext()){
            byte[] img = cursor.getBlob(1);
            bt = BitmapFactory.decodeByteArray(img,0,img.length);
        }
        return bt;
    }
*/

}
