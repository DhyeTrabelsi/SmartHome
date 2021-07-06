package com.quintus.labs.smarthome.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quintus.labs.smarthome.R;

public class compte extends AppCompatActivity {
    EditText nom,passe,passe2;
    DatabaseReference reff1;
    ProgressDialog prog;
    Member membre;
    long max=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
        nom= findViewById(R.id.name);
        passe=findViewById(R.id.passe);
        passe2=findViewById(R.id.repasse);
        membre=new Member();
        prog= new ProgressDialog(this);
        reff1= FirebaseDatabase.getInstance().getReference().child("Member");
        reff1.addValueEventListener(new ValueEventListener() {
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
    public void onLoginClicked1(View view) {
        String nomi=nom.getText().toString();
        String passei=passe.getText().toString();
        String passe2i=passe2.getText().toString();
        if (TextUtils.isEmpty(nomi)){
            nom.setError("Entrer votre nom");
            return;
        }
        if (TextUtils.isEmpty(passei)){
            passe.setError("Saisie un mot de passe");
            return;
        }
        if (!(passei.equals(passe2i))){
            passe2.setError("mot de passe different");
            passe.setError("mot de passe different");
            return;
        }
        if (passei.length()<8){
            passe.setError("Longeur de mot de passe erroné");
            return;
        }
        membre.setName(nomi);
        membre.setCode(passei);
        prog.setMessage("Attendez s'il vous plaît");
        prog.show();
        prog.setCanceledOnTouchOutside(false);
        reff1.child(String.valueOf(max+1)).setValue((membre));
        Intent i1=new Intent(getApplicationContext(), Activity1.class);
        i1.putExtra("name",nom.getText().toString());
        startActivity(i1);
        finish();
    }

    public void onLoginClicked2(View view) {
        Intent i=new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}

