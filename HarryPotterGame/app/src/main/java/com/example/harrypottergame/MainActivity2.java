package com.example.harrypottergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner2;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter2;
    private String[] zorluk = {"2*2","4*4","6*6"};
    private String[] tekCok = {"Çok Oyunculu","Tek Oyunculu"};
    MediaPlayer musicc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        musicc = MediaPlayer.create(MainActivity2.this, R.raw.prologue);
        musicc.start();

        spinner = findViewById(R.id.spinner4);
        spinner2 = findViewById(R.id.spinner6);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, zorluk);
        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tekCok);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter2);

        Button başla = (Button) findViewById(R.id.button3);

        başla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinner.getSelectedItem().toString().equals("2*2") && spinner2.getSelectedItem().toString().equals("Çok Oyunculu")){
                    //correct
                    //Toast.makeText(MainActivity2.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    openTO2x2();musicc.stop();
                }else if(spinner.getSelectedItem().toString().equals("4*4") && spinner2.getSelectedItem().toString().equals("Çok Oyunculu")){
                    //correct
                    //Toast.makeText(MainActivity2.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    openTO4x4();musicc.stop();
                }else if(spinner.getSelectedItem().toString().equals("2*2") && spinner2.getSelectedItem().toString().equals("Tek Oyunculu")){
                    //correct
                    //Toast.makeText(MainActivity2.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    opentek2x2();musicc.stop();
                }else if(spinner.getSelectedItem().toString().equals("4*4") && spinner2.getSelectedItem().toString().equals("Tek Oyunculu")){
                    //correct
                    //Toast.makeText(MainActivity2.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    opentek4x4();musicc.stop();
                }else if(spinner.getSelectedItem().toString().equals("6*6") && spinner2.getSelectedItem().toString().equals("Çok Oyunculu")){
                    //correct
                    //Toast.makeText(MainActivity2.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    openTO6x6();musicc.stop();
                }else if(spinner.getSelectedItem().toString().equals("6*6") && spinner2.getSelectedItem().toString().equals("Tek Oyunculu")){
                    //correct
                    //Toast.makeText(MainActivity2.this,"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    opentek6x6();musicc.stop();
                }// else
                    //incorrect
                    //Toast.makeText(MainActivity2.this,"Başarısız",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openTO2x2() {
        Intent intent = new Intent(this, TO2x2.class);
        startActivity(intent);
    }
    public void openTO4x4() {
        Intent intent = new Intent(this, TO4x4.class);
        startActivity(intent);
    }
    public void openTO6x6() {
        Intent intent = new Intent(this, TO6x6.class);
        startActivity(intent);
    }
    public void opentek2x2() {
        Intent intent = new Intent(this, tek2x2.class);
        startActivity(intent);
    }
    public void opentek4x4() {
        Intent intent = new Intent(this, tek4x4.class);
        startActivity(intent);
    }
    public void opentek6x6() {
        Intent intent = new Intent(this, tek6x6.class);
        startActivity(intent);
    }

}