package com.example.examapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examapp.Models.ProductModel;
import com.example.examapp.R;

import java.io.InputStream;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AllProductsAdapter extends ArrayAdapter<ProductModel> {
    private static final String TAG = "AllProductsAdapter";
        private Context context;
        private List<ProductModel> productModelList;

    public AllProductsAdapter(Context context, List<ProductModel> productModelList) {
        super(context , R.layout.allproducts, productModelList);
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allproducts,null,true);
        String IMAGEUri;
        TextView tvproductName = view.findViewById(R.id.tv_allproductname);
        TextView tvproductPrice= view.findViewById(R.id.tv_allproductprice);
        TextView tv_imageurl= view.findViewById(R.id.tv_imageurl);
        TextView tv_unitList= view.findViewById(R.id.tv_unitList);
        TextView tv_availInventList= view.findViewById(R.id.tv_availInventList);
        TextView tv_expiryDateList= view.findViewById(R.id.tv_expiryDateList);
        ImageView img_productList = view.findViewById(R.id.img_productList);
        TextView tv_id = view.findViewById(R.id.tv_id);

        IMAGEUri = productModelList.get(position).getImageUri();








        tvproductName.setText(productModelList.get(position).getProductName());
        tv_imageurl.setText(productModelList.get(position).getImageUri());
        tvproductPrice.setText(productModelList.get(position).getPrice().toString());
        tv_id.setText(productModelList.get(position).getProductId().toString());
        tv_unitList.setText(productModelList.get(position).getUnit().toString());
        tv_availInventList.setText(productModelList.get(position).getAvailInven().toString());
        tv_expiryDateList.setText(productModelList.get(position).getExpDate());





        String test = productModelList.get(position).getProductName();

        Log.d(TAG, "getView: "+test) ;
        return view;
    }
}
