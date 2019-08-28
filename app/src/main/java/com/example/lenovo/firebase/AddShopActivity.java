package com.example.lenovo.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddShopActivity extends AppCompatActivity implements View.OnClickListener {

    LocationManager locationManager;
    private EditText nameEditText, descriptionEditText, radiusEditText, latitudeEditText, longitudeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        nameEditText = findViewById(R.id.nameEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        radiusEditText = findViewById(R.id.radiusEditText);
        latitudeEditText = findViewById(R.id.latitudeEditText);
        longitudeEditText = findViewById(R.id.longitudeEditText);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addShopButton:
                addShop1();
                break;
        }
    }

        private void addShop1() {
            String shopName = nameEditText.getText().toString();
            String shopDescription = descriptionEditText.getText().toString();
            Integer radius = Integer.parseInt(radiusEditText.getText().toString());
            Double latitude = Double.parseDouble(latitudeEditText.getText().toString());
            Double longitude = Double.parseDouble(longitudeEditText.getText().toString());

            Shop shop = new Shop(shopName, shopDescription, radius, latitude, longitude);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("shops");

            String id = databaseReference.push().getKey();

            databaseReference.child(id).setValue(shop);

            Toast.makeText(this, "Shop inserted", Toast.LENGTH_SHORT).show();
    }



}
