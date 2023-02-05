package com.example.officeroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class JoinOfficeActivity extends AppCompatActivity {
    private EditText office_id;
    private EditText post_id;
    private Button join;

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
        String text_office_id = office_id.getText().toString();
        String text_post_id = post_id.getText().toString();
        db.collection(text_office_id).document(text_post_id).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            Toast.makeText(JoinOfficeActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(JoinOfficeActivity.this, "Provided id is wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("JoinOfficeActivity",e.toString());
                    }
                });
    }
}