package com.example.lenovo.firebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loginEditText, passwordEditText;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = findViewById(R.id.emailLoginEditText);
        passwordEditText = findViewById(R.id.passwordLoginEditText);
        auth = FirebaseAuth.getInstance();
    }

   public void login(View v) {
        if (loginEditText.getText().toString().equals("") && passwordEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Put sth in", Toast.LENGTH_SHORT).show();
        } else {
            String emailLogin = loginEditText.getText().toString();
            String passwordLogin = passwordEditText.getText().toString();


            auth.signInWithEmailAndPassword(emailLogin, passwordLogin)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User logged in", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
