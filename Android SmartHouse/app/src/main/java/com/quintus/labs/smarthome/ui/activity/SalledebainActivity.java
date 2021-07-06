package com.quintus.labs.smarthome.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quintus.labs.smarthome.R;

import java.util.Objects;

public class SalledebainActivity extends AppCompatActivity {
    ImageView ledall1,ledett1;
    Switch butonswitsh1;
    ImageButton image1,btn1;
    TextView user2;
    DatabaseReference reff1;
    String patron,champ22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salledebain);
        user2 = findViewById(R.id.user2);
        Bundle extras=getIntent().getExtras();
        if (extras!=null) {
            champ22=(extras.getString("champ5"));
            patron=(extras.getString("user4"));
            user2.append(patron);
        }
        image1 = (ImageButton) findViewById(R.id.home_rl2);
        ledall1 = (ImageView) findViewById(R.id.imageall1);
        ledett1 = (ImageView) findViewById(R.id.imageett1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),  Activity1.class);
                i2.putExtra("name",user2.getText().toString());
                i2.putExtra("champ",champ22);
                startActivity(i2);


            }
        });
        butonswitsh1 = (Switch) findViewById(R.id.switch1);

        btn1 = (ImageButton) findViewById(R.id.btn1);
        reff1= FirebaseDatabase.getInstance().getReference().child("donnes");
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String etat= Objects.requireNonNull(dataSnapshot.child(champ22).child("salle de bain").child("lampe").getValue()).toString();
                String gaz= Objects.requireNonNull(dataSnapshot.child(champ22).child("cuisine").child("gaz").getValue()).toString();

                if (etat.equals("1")) {
                    butonswitsh1.setChecked(true);
                }
                if (etat.equals("0")) {
                    butonswitsh1.setChecked(false);

                }
                if (gaz.equals("1")) {
                    gaz();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        butonswitsh1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    ledall1.setImageResource(R.drawable.ledallume);
                    reff1.child(champ22).child("salle de bain").child("lampe").setValue((1));
                }
                else{
                    ledall1.setImageResource(R.drawable.ledeteinte);
                    reff1.child(champ22).child("salle de bain").child("lampe").setValue((0));
                }
            }
        });




    }
    private void gaz() {
        AlertDialog.Builder bulider= new AlertDialog.Builder(this);
        bulider.setMessage("Attention fuite de gaz");
        AlertDialog alertedialog=bulider.create();
        alertedialog.show();
    }




}