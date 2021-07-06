package com.quintus.labs.smarthome.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.application.isradeleon.notify.Notify;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quintus.labs.smarthome.R;

import java.util.Objects;

public class CuisineActivity extends AppCompatActivity {

    Switch butonswitsh1;
    ImageButton image1,btn1;
    TextView user3;
    DatabaseReference reff1;
    String patron,champ22;
    TextView tem,gaz2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);
        user3 = findViewById(R.id.user3);
        Bundle extras=getIntent().getExtras();
        if (extras!=null) {
            champ22=(extras.getString("champ2"));
            patron=(extras.getString("user1"));
            user3.append(patron);
        }
        image1 = (ImageButton) findViewById(R.id.home_rl2);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),  Activity1.class);
                i2.putExtra("name",user3.getText().toString());
                i2.putExtra("champ",champ22);
                startActivity(i2);


            }
        });
        butonswitsh1 = (Switch) findViewById(R.id.switch1);
        tem = (TextView) findViewById(R.id.texttem);
        gaz2= (TextView) findViewById(R.id.textgaz);

        btn1 = (ImageButton) findViewById(R.id.btn1);
        reff1= FirebaseDatabase.getInstance().getReference().child("donnes");
        reff1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String etat= Objects.requireNonNull(dataSnapshot.child(champ22).child("cuisine").child("température").getValue()).toString();
                String gaz= Objects.requireNonNull(dataSnapshot.child(champ22).child("cuisine").child("gaz").getValue()).toString();

                if (true) {
                    tem.setText(etat);
                }

                if (gaz.equals("1")) {
                    gaz();
                    gaz2.setText("!!");
                }
                if (gaz.equals("0")) {
                    gaz2.setText("Ok");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }
    private void gaz() {
        AlertDialog.Builder bulider= new AlertDialog.Builder(this);
        bulider.setMessage("Attention fuite de gaz").setCancelable(false).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CuisineActivity.super.onBackPressed();
            }
        });
        AlertDialog alertedialog=bulider.create();
        alertedialog.show();
    }
}
