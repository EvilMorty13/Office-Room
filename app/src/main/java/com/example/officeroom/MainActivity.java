package com.example.officeroom;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button sign_in;
    private Button sign_up;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this is a test comment
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sign_in = findViewById(R.id.sign_in);
        sign_up = findViewById(R.id.sign_up);


        auth = FirebaseAuth.getInstance();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignUpActivity.class));
                finish();
            }
        });


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                signinUser(txt_email,txt_password);
            }
        });
    }

    private void signinUser(String txt_email, String txt_password) {
        auth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }
    }
}