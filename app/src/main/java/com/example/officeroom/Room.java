package com.example.officeroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Room extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private ImageView imageMenu;

    private EditText writeTitle;
    private EditText writeSomething;
    private EditText announceTo;
    private Button post;
    private TextView publishedAnnouncement;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        findAllId();

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_title = writeTitle.getText().toString();
                String text_announcement = writeSomething.getText().toString();
                String text_announceTo = announceTo.getText().toString();

                Map<String,Object> note = new HashMap<>();
                note.put(text_title,text_announcement);

                db.collection("cse").document("teacher").set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Room.this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Room.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                db.collection("cse").document("teacher").get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if(documentSnapshot.exists()){
                                    Map<String,Object> note = documentSnapshot.getData();

                                    for(String key : note.keySet()){
                                        publishedAnnouncement.setText("\n"+key+"\n"+note.get(key));
                                    }
                                }else{
                                    Toast.makeText(Room.this, "doc not exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Room.this, "Sorry", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        toggle = new ActionBarDrawerToggle(Room.this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_bar_home_id:
                        Toast.makeText(Room.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_bar_profile_id:
                        Toast.makeText(Room.this, "Profile Clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_bar_help_id:
                        Toast.makeText(Room.this, "Help Clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.nav_bar_logout_id:
                        Toast.makeText(Room.this, "log out clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                }

                return false;
            }
        });

        imageMenu = findViewById(R.id.imageMenu);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }



    private void findAllId() {
        db = FirebaseFirestore.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);

        writeTitle = findViewById(R.id.titleId);
        writeSomething = findViewById(R.id.writeAnnounceId);
        announceTo = findViewById(R.id.announceToId);
        publishedAnnouncement = findViewById(R.id.showAnnouncementId);
        post = findViewById(R.id.postAnnounceId);
    }
}
