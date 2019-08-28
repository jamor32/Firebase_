package com.example.lenovo.firebase;

public class Shop {

    public String shopName, shopDescription;
    public int radius;
    private double latitude;
    private double longitude;

    public Shop() {

    }

    public Shop(String shopName, String shopDescription, int radius, double latitude, double longitude){
        this.shopName = shopName;
        this.shopDescription = shopDescription;
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
