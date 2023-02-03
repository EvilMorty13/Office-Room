package com.example.officeroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateOfficeActivity extends AppCompatActivity {

    private EditText office_name;
    private EditText office_id;
    private EditText office_type;
    private EditText posts;

    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_office);

        office_name = findViewById(R.id.office_name);
        office_id = findViewById(R.id.office_id);
        office_type = findViewById(R.id.office_type);
        posts = findViewById(R.id.posts);
        create = findViewById(R.id.create);

    }
}