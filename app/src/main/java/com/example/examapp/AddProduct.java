package com.example.examapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.examapp.Models.ProductModel;
import com.example.examapp.SqlLiteDatabase.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddProduct extends AppCompatActivity {
    private static final String TAG = "AddProduct";
    ProductModel productModel;
    Button btn_add, btn_cancel,btn_expirydate,btn_upload;
    TextInputEditText tbi_productName,tbi_price,tbi_unit,tbi_availInvent,tbi_dateexpire;
    TextInputLayout tbl_dateexpire;
    ImageView IMG_productIMg;
    float price;
    int availInvent;
    String IMAGEuri ="";
    String productnamey ,unit,expiriydate,pricy,avail,unity;

    private  DatePickerDialog.OnDateSetListener mDateSetListener;
    InputStream inputStream;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        btn_add = (Button)findViewById(R.id.btn_addproduct);
        btn_upload = (Button)findViewById(R.id.btn_upload);
        btn_expirydate = (Button)findViewById(R.id.btn_expirydate);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        tbi_productName = (TextInputEditText)findViewById(R.id.tbi_productName);
        tbi_availInvent = (TextInputEditText)findViewById(R.id.tbi_availInvent);
        tbi_price = (TextInputEditText)findViewById(R.id.tbi_price);
        tbi_unit = (TextInputEditText)findViewById(R.id.tbi_unit);
        IMG_productIMg = (ImageView) findViewById(R.id.IMG_productIMg);
        tbi_dateexpire = (TextInputEditText)findViewById(R.id.tbi_dateexpire);
        tbl_dateexpire = (TextInputLayout)findViewById(R.id.tbl_dateexpire);

        btn_expirydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddProduct.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });


        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ActivityCompat.requestPermissions(
                      AddProduct.this,
                      new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                       REQUEST_CODE_GALLERY
              );
            }
        });



        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;

                Log.d(TAG, "onDateSet: ");
                String date = month + "/" + day + "/" + year;

                tbi_dateexpire.setText(date);
            }
        };



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                     productnamey = tbi_productName.getText().toString();
                     unity = tbi_unit.getText().toString();
                     expiriydate = tbi_dateexpire.getText().toString();
                     pricy = tbi_price.getText().toString();
                     avail = tbi_availInvent.getText().toString();


                    if (TextUtils.isEmpty(productnamey) && TextUtils.isEmpty(unity) && TextUtils.isEmpty(expiriydate) && TextUtils.isEmpty(pricy)
                            && TextUtils.isEmpty(avail) && IMAGEuri.equals("")) {
                        if (TextUtils.isEmpty(productnamey)) {
                            tbi_productName.setError("Product Name Cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(pricy)) {
                            tbi_price.setError("Price Cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(avail)) {
                            tbi_availInvent.setError("Available Inventory  Cannot be empty");
                            return;
                        }
                        if (TextUtils.isEmpty(expiriydate)) {
                            tbi_dateexpire.setError("Most select Expiry Date");
                            return;
                        }
                        if (TextUtils.isEmpty(unity)) {
                            tbi_unit.setError("Unit Cannot be empty");
                            return;
                        }
                        if (IMAGEuri.isEmpty()){
                            Toast.makeText(AddProduct.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        productModel = new ProductModel(tbi_productName.getText().toString(),
                                tbi_unit.getText().toString(),
                                tbi_dateexpire.getText().toString(),
                                Float.parseFloat(tbi_price.getText().toString()),
                                Integer.parseInt(tbi_availInvent.getText().toString()), null, IMAGEuri


                        );





                    }
                    }catch( Exception e){
                     productnamey = tbi_productName.getText().toString();
                     unity = tbi_unit.getText().toString();
                    expiriydate = tbi_dateexpire.getText().toString();
                     pricy = tbi_price.getText().toString();
                     avail = tbi_availInvent.getText().toString();


                    if (TextUtils.isEmpty(productnamey) && TextUtils.isEmpty(unity) && TextUtils.isEmpty(expiriydate) && TextUtils.isEmpty(pricy)
                            && TextUtils.isEmpty(avail) && IMAGEuri.isEmpty()) {


                    } else {

                        Toast.makeText(AddProduct.this, TAG + "Error Adding Product", Toast.LENGTH_SHORT).show();
                        productModel = new ProductModel("Error_productName", "ERROR_unit", "Error_dateexpire", price = 0, 0, null, IMAGEuri);

                        productnamey = "";
                        unit = "";
                        expiriydate = "";
                        pricy = "";
                        avail = "";
                        IMAGEuri = "";
                    }



                    }

                 productnamey = tbi_productName.getText().toString();
                 unit = tbi_unit.getText().toString();
                 expiriydate = tbi_dateexpire.getText().toString();
                 pricy = tbi_price.getText().toString();
                 avail = tbi_availInvent.getText().toString();


                if (TextUtils.isEmpty(productnamey) && TextUtils.isEmpty(unit) && TextUtils.isEmpty(expiriydate) && TextUtils.isEmpty(pricy)
                        && TextUtils.isEmpty(avail) && IMAGEuri.isEmpty()) {


                } else {

                    DatabaseHelper databaseHelper = new DatabaseHelper(AddProduct.this);

                    boolean success = databaseHelper.addOne(productModel);

                    Toast.makeText(AddProduct.this, "Product Successfully Added " , Toast.LENGTH_SHORT).show();

                    tbi_availInvent.setText("");
                    tbi_price.setText("");
                    tbi_dateexpire.setText("");
                    tbi_unit.setText("");
                    tbi_productName.setText("");

                    productnamey = "";
                    unit = "";
                    expiriydate = "";
                    pricy = "";
                    avail = "";
                    IMAGEuri = "";


                }





                }



        });
    }

    public void viewMainActivity(View view) {

        startActivity(new Intent(AddProduct.this, MainActivity.class));

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
               startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }else{
                Toast.makeText(this, "You dont have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
         Uri uri = data.getData();
          IMAGEuri = uri.toString();
          try {
               inputStream = getContentResolver().openInputStream(uri);

              Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

              Log.d(TAG, "onActivityResult: " +uri);
              IMG_productIMg.setImageBitmap(bitmap);

          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
      }

        super.onActivityResult(requestCode, resultCode, data);
    }
}