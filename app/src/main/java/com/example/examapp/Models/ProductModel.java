package com.example.examapp.Models;

import android.net.Uri;

public class ProductModel {
     private  String productName,unit,expDate;
     private Float price;
     private Integer availInven, productId;
     private String imageUri;


    public ProductModel() {
    }

    public ProductModel(String productName, String unit, String expDate, Float price, Integer availInven, Integer productId, String imageUri) {
        this.productName = productName;
        this.unit = unit;
        this.expDate = expDate;
        this.price = price;
        this.availInven = availInven;
        this.productId = productId;
        this.imageUri = imageUri;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAvailInven() {
        return availInven;
    }

    public void setAvailInven(Integer availInven) {
        this.availInven = availInven;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
