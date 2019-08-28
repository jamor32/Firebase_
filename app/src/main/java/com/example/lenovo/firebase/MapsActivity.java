package com.example.lenovo.firebase;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private HashMap<String, Shop> shopHashMap = new HashMap<>();
    private List<Shop> shopList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

  /*      Shop shop = new Shop("Fajny sklep", "Super fajny sklep", 10, 54.6, 23);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("shops");

        String id = databaseReference.push().getKey();

        databaseReference.child(id).setValue(shop);*/

        prepareShopsData();
    }

    private void prepareShopsData() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("shops");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                shopHashMap.clear();
                shopList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String id = postSnapshot.getKey();
                    Shop shop = postSnapshot.getValue(Shop.class);
                    shopHashMap.put(id, shop);
                }

                shopList.addAll(shopHashMap.values());

                updateGoogleMap();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Getting shops failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateGoogleMap() {
        googleMap.clear();

        for (Shop shop : shopList) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(shop.getLatitude(), shop.getLongitude()))
                    .title(shop.getShopName())
                    .snippet(shop.getShopDescription()));

            googleMap.addCircle(new CircleOptions()
                    .center(new LatLng(shop.getLatitude(), shop.getLongitude()))
                    .radius(shop.getRadius()));
        }

        if (!shopList.isEmpty()) {
            Shop firstShop = shopList.get(0);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(firstShop.getLatitude(), firstShop.getLongitude())));
        }
    }
}
