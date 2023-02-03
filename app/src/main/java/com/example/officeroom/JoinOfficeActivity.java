package com.example.officeroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class JoinOfficeActivity extends AppCompatActivity {
    private EditText office_id;
    private EditText post_id;

    private Button join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_office);

        office_id = findViewById(R.id.office_id);
        post_id = findViewById(R.id.post_id);
        join = findViewById(R.id.join);
    }
}