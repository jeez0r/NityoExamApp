package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examapp.Adapter.AllProductsAdapter;
import com.example.examapp.Models.ProductModel;
import com.example.examapp.SqlLiteDatabase.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ListView listViewProductList;
    ArrayAdapter productAdapter;
    ListView listview_productList;
    ProductModel productModel;
    List<ProductModel> productlist = new ArrayList<>();
    AllProductsAdapter allProductsAdapter;
    DatabaseHelper databaseHelper;

    public static boolean KEY_UPDATE = false;
    public static  String KEY_PRODUCTNAME = "";
    public static  String KEY_PRODUCT_UNIT ="";
    public static  String KEY_PRODUCTDATE = "";
    public static  String KEY_IMAGEURL = "";
    public static  String KEY_PRODUCTID = "";

    public static  float KEY_PRICE=0;
    public static  Integer KEY_AVILVENT=0;

    Integer id,Productprice,productAvailInven;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewProductList = (ListView) findViewById(R.id.listview_productList);

// getting product ID and go to viewProduct
        listViewProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ViewProduct.class);
                TextView tv_id = view.findViewById(R.id.tv_id);
                TextView tv_unitList = view.findViewById(R.id.tv_unitList);
                TextView tv_allproductname = view.findViewById(R.id.tv_allproductname);
                TextView tv_allproductprice = view.findViewById(R.id.tv_allproductprice);
                TextView tv_expiryDateList = view.findViewById(R.id.tv_expiryDateList);
                TextView tv_availInventList = view.findViewById(R.id.tv_availInventList);
                TextView tv_imageurl = view.findViewById(R.id.tv_imageurl);
                id = Integer.parseInt(tv_id.getText().toString());

                KEY_PRODUCT_UNIT = tv_unitList.getText().toString();
                KEY_PRODUCTNAME = tv_allproductname.getText().toString();
                KEY_PRICE = Float.parseFloat(tv_allproductprice.getText().toString());
                KEY_PRODUCTDATE = tv_expiryDateList.getText().toString();
                KEY_AVILVENT = Integer.parseInt(tv_availInventList.getText().toString());
                KEY_IMAGEURL = tv_imageurl.getText().toString();
                KEY_PRODUCTID = tv_id.getText().toString();



                intent.putExtra("productID", id);
                intent.putExtra("position", i);
                startActivity(intent);

                Log.d(TAG, "onItemClick: " +id);
            }


        });
        setListView();
    }
    public void viewAddProduct(View view) {
        KEY_UPDATE = false;

        startActivity(new Intent(MainActivity.this, AddProduct.class));





    }

    public void  setListView(){


        databaseHelper = new DatabaseHelper(MainActivity.this);
        List<ProductModel>  allproduct = databaseHelper.selectAllProduct();

        allProductsAdapter = new AllProductsAdapter(MainActivity.this, allproduct);
        listViewProductList.setAdapter(allProductsAdapter);



    }
}