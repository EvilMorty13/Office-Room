package com.example.officeroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText password;
    private EditText password2;

    private Button sign_up;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        sign_up = findViewById(R.id.sign_up);

        auth = FirebaseAuth.getInstance();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_password2 = password2.getText().toString();

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(SignUpActivity.this,"Empty credentials",Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length()<6){
                    Toast.makeText(SignUpActivity.this,"Password too short",Toast.LENGTH_SHORT).show();
                }
                else if (!txt_password.equals(txt_password2)) {
                    Toast.makeText(SignUpActivity.this,"Password doesn't match. Please try again",Toast.LENGTH_SHORT).show();
                }
                else{
                    registerUser(txt_email,txt_password);
                }
            }
        });
    }

    private void registerUser(String txt_email, String txt_password) {
        auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(SignUpActivity.this , new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Registration successfully completed",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}