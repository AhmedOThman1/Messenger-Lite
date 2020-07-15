package com.example.messengerlite.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.messengerlite.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputLayout user, password;
    Button login;
    TextView signup;
//    public static FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        // Initialize Firebase Auth
//        mAuth = FirebaseAuth.getInstance();

        user = findViewById(R.id.user_layout);
        password = findViewById(R.id.pass_layout);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String User = user.getEditText().getText().toString().trim(),
                        Pass = password.getEditText().getText().toString().trim();

                if (confirm_email(User) && confirm_password(Pass)) {

                    login(User,Pass);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent sign = new Intent(Login.this , SignUp.class);
//                startActivity(sign);
            }
        });

    }

    private boolean confirm_password(String pass) {
        if (pass.isEmpty()) {
            password.setError(getString(R.string.empty));
            password.requestFocus();
            return false;
        }
        password.setError("");
        return true;
    }

    private boolean confirm_email(String username) {
        if (username.isEmpty()) {
            user.setError(getString(R.string.empty));
            user.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            user.setError(getString(R.string.not_valid));
            user.requestFocus();
            return false;
        }
        user.setError("");
        return true;
    }

    void login( String email , String password)
    {
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
//                            SharedPreferences.Editor editor=sharedPreferences.edit();
//                            editor.putInt(ID,1);
//                            editor.apply();
//                            DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("allMessages").child(user.getUid());
//                                myRef.child("myId").setValue(user.getUid());
//                                myRef.child("name").setValue(user.getDisplayName());
//                                myRef.child("photo").setValue(user.getPhotoUrl());
//
//                            Intent home = new Intent(Login.this, Home.class);
//                            startActivity(home);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(Login.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // ...
//                    }
//                });
    }
}
