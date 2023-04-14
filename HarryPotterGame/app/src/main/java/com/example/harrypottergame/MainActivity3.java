package com.example.harrypottergame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TextView username;
    private TextView password;
    private TextView repassword;
    private MaterialButton regbtn;
    MediaPlayer musicc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        musicc = MediaPlayer.create(MainActivity3.this, R.raw.prologue);
        musicc.start();

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        regbtn = (MaterialButton) findViewById(R.id.signupbtn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString();
                int password1 = Integer.parseInt(password.getText().toString().trim());
                int repassword1 = Integer.parseInt(repassword.getText().toString().trim());

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("users").child(username1);

                addDatatoFirebase(username1,password1);

                Toast.makeText(MainActivity3.this,"Username is"+username1, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDatatoFirebase(String name, int password2) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.setValue(password2);

                //Toast.makeText(MainActivity3.this, "-", Toast.LENGTH_SHORT).show();
                musicc.stop();
                openMainActivity2();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity3.this, "Başarısız" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openMainActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}