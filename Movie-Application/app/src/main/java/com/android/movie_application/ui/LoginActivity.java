package com.android.movie_application.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.movie_application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{

    private EditText editTextLogin, editTextPass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        editTextLogin = (EditText) findViewById(R.id.etEmail);
        editTextPass = (EditText) findViewById(R.id.etPassword);
    }

    public void onClickRegister(View view)
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view)
    {
        String email,pass;
        email = editTextLogin.getText().toString().trim();
        pass = editTextPass.getText().toString().trim();
        if (email.isEmpty())
        {
            editTextLogin.setError("Email is required!");
            editTextLogin.requestFocus();
            return;
        }
        if (pass.isEmpty())
        {
            editTextPass.setError("Password is required!");
            editTextPass.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextLogin.setError("Email type is invalid!");
            editTextLogin.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this,"Login successfully!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, StreamVideoActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login fail!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}