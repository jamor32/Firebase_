package com.example.lenovo.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText e1, e2;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        e1 = findViewById(R.id.editText3);
        e2 = findViewById(R.id.editText4);

        auth = FirebaseAuth.getInstance();
    }

    public void createUser(View v) {
        if (e1.getText().toString().equals("") && e2.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Put sth in", Toast.LENGTH_SHORT).show();
        } else {
            String email = e1.getText().toString();
            String password = e2.getText().toString();

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "User registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "User cannot be created", Toast.LENGTH_LONG).show();
                    }
                    }
                });
        }
    }
}
