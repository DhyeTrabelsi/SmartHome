package com.quintus.labs.smarthome.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quintus.labs.smarthome.R;

import java.util.Objects;

public class Activity1 extends AppCompatActivity implements View.OnClickListener{
    ImageButton image1,image2,image3,image4,image5,image6,logoutt;
    TextView user;
    String champ1;
    Bundle extras;
    DatabaseReference reff1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        logoutt = (ImageButton) findViewById(R.id.logout);
        image1 = (ImageButton) findViewById(R.id.image1);
        image2 = (ImageButton) findViewById(R.id.image2);
        image3 = (ImageButton) findViewById(R.id.image3);
        image4 = (ImageButton) findViewById(R.id.image4);
        image5 = (ImageButton) findViewById(R.id.image5);
        image6 = (ImageButton) findViewById(R.id.image6);
        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        user = findViewById(R.id.user);
        extras=getIntent().getExtras();
        if (extras!=null) {
            user.append(extras.getString("name"));
            champ1=extras.getString("champ");
        }

        reff1= FirebaseDatabase.getInstance().getReference().child("donnes");
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String gaz= Objects.requireNonNull(dataSnapshot.child(champ1).child("cuisine").child("gaz").getValue()).toString();


                if (gaz.equals("1")) {
                    gaz();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
       logout();

    }

    private void logout() {
        AlertDialog.Builder bulider= new AlertDialog.Builder(this);
        bulider.setMessage(extras.getString("name")+", étes vous sûr de quitter").setCancelable(false).setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Activity1.super.onBackPressed();
            }
        })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertedialog=bulider.create();
        alertedialog.show();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.image1:
                Intent i2=new Intent(getApplicationContext(), CuisineActivity.class);
                i2.putExtra("user1",user.getText().toString());
                i2.putExtra("champ2",champ1);
                startActivity(i2);
                break;

            case R.id.image2:
                Intent i3=new Intent(getApplicationContext(),  ChambreActivity.class);
                i3.putExtra("user2",user.getText().toString());
                i3.putExtra("champ3",champ1);
                startActivity(i3);
                break;
            case R.id.image3:
                Intent i4=new Intent(getApplicationContext(),   SalonActivity.class);
                i4.putExtra("user3",user.getText().toString());
                i4.putExtra("champ4",champ1);
                startActivity(i4);
                break;

            case R.id.image4:
                Intent i5=new Intent(getApplicationContext(),  SalledebainActivity.class);
                i5.putExtra("user4",user.getText().toString());
                i5.putExtra("champ5",champ1);
                startActivity(i5);
                break;
            case R.id.image5:
                Intent i6=new Intent(getApplicationContext(),  SallesejourActivity.class);
                i6.putExtra("user5",user.getText().toString());
                i6.putExtra("champ6",champ1);
                startActivity(i6);
                break;

            case R.id.image6:
                Intent i7=new Intent(getApplicationContext(),  JardinActivity.class);
                i7.putExtra("user6",user.getText().toString());
                i7.putExtra("champ7",champ1);
                startActivity(i7);
                break;

        }
    }
    private void gaz() {
        AlertDialog.Builder bulider= new AlertDialog.Builder(this);
        bulider.setMessage("Attention fuite de gaz");
        AlertDialog alertedialog=bulider.create();
        alertedialog.show();
    }

    public void onLoginClickedout(View view) {
        logout();
    }
}
