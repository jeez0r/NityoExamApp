package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examapp.Models.ProductModel;
import com.example.examapp.SqlLiteDatabase.DatabaseHelper;

import static com.example.examapp.MainActivity.KEY_AVILVENT;
import static com.example.examapp.MainActivity.KEY_IMAGEURL;
import static com.example.examapp.MainActivity.KEY_PRICE;
import static com.example.examapp.MainActivity.KEY_PRODUCTDATE;
import static com.example.examapp.MainActivity.KEY_PRODUCTID;
import static com.example.examapp.MainActivity.KEY_PRODUCTNAME;
import static com.example.examapp.MainActivity.KEY_PRODUCT_UNIT;

public class ViewProduct extends AppCompatActivity {

    ProductModel productModel;
    Button btn_add,btn_delete,btn_viewall;
    TextView tv_productName,tv_price,tv_unit,tv_expiryDate,tv_availInvent,tv_imageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_viewall = findViewById(R.id.btn_viewAll);
        tv_productName = (TextView)findViewById(R.id.tv_productName);
        tv_price = (TextView)findViewById(R.id.tv_price);
        tv_unit = (TextView)findViewById(R.id.tv_unit);
        tv_expiryDate = (TextView)findViewById(R.id.tv_expiryDate);
        tv_availInvent = (TextView)findViewById(R.id.tv_availInvent);
        tv_imageUrl = (TextView)findViewById(R.id.tv_imageUrl);


        tv_productName.setText(KEY_PRODUCTNAME);
        tv_price.setText(String.valueOf(KEY_PRICE));
        tv_unit.setText(KEY_PRODUCT_UNIT);
        tv_expiryDate.setText(KEY_PRODUCTDATE);
        tv_availInvent.setText(String.valueOf(KEY_AVILVENT));
        tv_imageUrl.setText(String.valueOf(KEY_IMAGEURL));



        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewProduct.this);

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure to Delete the " +KEY_PRODUCTNAME);

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(ViewProduct.this);

                        databaseHelper.deleteOne(KEY_PRODUCTID);

                        Toast.makeText(ViewProduct.this, "Deleted Successfully Product ID:"+KEY_PRODUCTID, Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        startActivity(new Intent(ViewProduct.this, MainActivity.class));
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();





            }
        });

        btn_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProduct.this, MainActivity.class));
            }
        });



    }


}