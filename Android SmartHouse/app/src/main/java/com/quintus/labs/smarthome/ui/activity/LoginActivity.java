package com.quintus.labs.smarthome.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quintus.labs.smarthome.R;

import java.util.ArrayList;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

        EditText nom,passe ;
        DatabaseReference reff;
        Member membre;
        ArrayList<String> list;
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            nom= findViewById(R.id.name);
            passe= findViewById(R.id.passe);

            membre=new Member();
            list = new ArrayList<>();
            reff= FirebaseDatabase.getInstance().getReference().child("Member");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    for (DataSnapshot datasnapshot: snapshot.getChildren()) {
                        list.add(datasnapshot.getValue().toString());
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });}

        public void onLoginClicked(View view) {

            membre.setName(nom.getText().toString());
            membre.setCode(passe.getText().toString());
            if (!(list.contains("{code="+passe.getText().toString()+", name="+nom.getText().toString()+"}"))){
                nom.setError("Compte Invalide");
                return;
            }
            Intent i1=new Intent(getApplicationContext(), Activity1.class);
            i1.putExtra("name",nom.getText().toString());
            i1.putExtra("champ","{code="+passe.getText().toString()+", name="+nom.getText().toString()+"}");
            startActivity(i1);
            finish();
        }

        public void onLoginClicked3(View view) {
            Intent i1=new Intent(getApplicationContext(), compte.class);
            i1.putExtra("name",nom.getText().toString());
            startActivity(i1);
            finish(); }
    }




      /*
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists())
                        max=snapshot.getChildrenCount();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



        String case1=nom.getText().toString().trim();
        String case2=passe.getText().toString().trim();
        membre.setName(case1);
        membre.setCode(case2);
        if (TextUtils.isEmpty(case1)){
            nom.setError("Please entrer votre nom");
            return;
        }
        if (TextUtils.isEmpty(case2)){
            passe.setError("Please entrer votre mot de passe");
            return;
        }
        prog.setMessage("Attendez s'il vous plaît");
        prog.show();
        prog.setCanceledOnTouchOutside(false);reff= FirebaseDatabase.getInstance().getReference().child("Membre");


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String etat= Objects.requireNonNull(dataSnapshot.child("lampe").getValue()).toString();
                if (etat.equals("1")) {

                }
                if (etat.equals("0")) {


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        reff.child(String.valueOf(max+1)).setValue((membre));
        Intent i1=new Intent(getApplicationContext(), Activity1.class);
        i1.putExtra("name",nom.getText().toString());
        startActivity(i1);
        finish();
    }

    public void onLoginClicked3(View view) {
        Intent i=new Intent(getApplicationContext(), compte.class);
        startActivity(i);
    }
}*/
