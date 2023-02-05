package com.example.officeroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateOfficeActivity extends AppCompatActivity {

    private EditText officeName;
    public static EditText officeId;
    private EditText Rank;
    private EditText RankId;

    private Button addButton;
    private Button createButton;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_office);

        findAllId();
    }
    private void findAllId() {
        officeName = (EditText) findViewById(R.id.office_name);
        officeId = (EditText) findViewById(R.id.office_id);
        Rank = (EditText) findViewById(R.id.rank);
        RankId = (EditText) findViewById(R.id.rankId);

        addButton = (Button) findViewById(R.id.addRank);
        createButton = (Button) findViewById(R.id.create);
    }

    public void addRank(View v){
        String text_officeName = officeName.getText().toString();
        String text_officeId = officeId.getText().toString();
        String text_rank = Rank.getText().toString();
        String text_rank_Id = RankId.getText().toString();

        Map<String,Object> Intro = new HashMap<>();
        Intro.put(text_rank,text_rank_Id);

        db.collection(text_officeId).document(text_rank_Id).set(Intro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CreateOfficeActivity.this, "Successfully "+text_rank+" added!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateOfficeActivity.this, "Sorry! there is some problem.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void createRoom(View v){
        String text_officeName = officeName.getText().toString();
        String text_officeId = officeId.getText().toString();
        String text_rank = Rank.getText().toString();
        String text_rank_Id = RankId.getText().toString();

        Map<String,Object> Intro = new HashMap<>();
        Intro.put("Office Name",text_officeName);
        Intro.put(text_rank,text_rank_Id);

        db.collection(text_officeId).document(text_rank_Id).set(Intro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(CreateOfficeActivity.this,JoinOfficeActivity.class));
                        Toast.makeText(CreateOfficeActivity.this, "Successfully room created!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateOfficeActivity.this, "Sorry! there is some problem.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}