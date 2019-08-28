package com.example.lenovo.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView profileText;
    private EditText nameEditText, priceEditText, quantityEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth = FirebaseAuth.getInstance();
        profileText = findViewById(R.id.profileTextView);
        user = auth.getCurrentUser();

        profileText.setText(user.getEmail());

        nameEditText = findViewById(R.id.nameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addItemButton:
                addProduct();
                break;
        }
    }

    public void signOut (View v){
        auth.signOut();
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void addProduct() {
        String name = nameEditText.getText().toString();
        String price = priceEditText.getText().toString();
            String quantity = quantityEditText.getText().toString();

        Product product = new Product(name, price, quantity);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products");

        String id = databaseReference.push().getKey();

        databaseReference.child(id).setValue(product);

        Toast.makeText(this, "Item inserted", Toast.LENGTH_SHORT).show();
    }
}
