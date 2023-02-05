package com.example.officeroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class JoinOfficeActivity extends AppCompatActivity {
    private EditText office_id;
    private EditText post_id;
    private Button join;

    public static String text_office_id;

    boolean intoTheRoom=false;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_office);

        findAllId();

    }

    private void findAllId(){
        office_id = findViewById(R.id.office_id);
        post_id = findViewById(R.id.post_id);
        join = findViewById(R.id.join);
    }



    public void joinInRoom(View view){
        text_office_id = office_id.getText().toString();
        String text_post_id = post_id.getText().toString();
        db.collection(text_office_id).document(text_post_id).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            startActivity(new Intent(JoinOfficeActivity.this,Room.class));
                            finish();
                        }else{
                            Toast.makeText(JoinOfficeActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}