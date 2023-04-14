package com.example.harrypottergame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TO6x6 extends AppCompatActivity {

    TextView textView_o1, textView_o2,timer;
    ImageView iv_11,iv_12,iv_13,iv_14,iv_15,iv_16,iv_21,iv_22,iv_23,iv_24,iv_25,iv_26,iv_31,iv_32,iv_33,iv_34,iv_35,iv_36,iv_41,iv_42,iv_43,iv_44,iv_45,iv_46,iv_51,iv_52,iv_53,iv_54,iv_55,iv_56,iv_61,iv_62,iv_63,iv_64,iv_65,iv_66;
    Integer[] kartDizisi = {101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218};

    FirebaseDatabase firebaseDatabase2;
    DatabaseReference databaseReference2;

    int ilkKart, ikinciKart;
    int ilkTiklanan, ikinciTiklanan;
    int kartSayisi = 1;
    int sira = 1;
    int oyuncu1 =0, oyuncu2 =0;
    String link101,link102,link103,link104,link105,link106,link107,link108,link109,link110,link111,link112,link113,link114,link115,link116,link117,link118,link201,link202,link203,link204,link205,link206,link207,link208,link209,link210,link211,link212,link213,link214,link215,link216,link217,link218;
    String path101,path102,path103,path104,path105,path106,path107,path108,path109,path110,path111,path112,path113,path114,path115,path116,path117,path118,path201,path202,path203,path204,path205,path206,path207,path208,path209,path210,path211,path212,path213,path214,path215,path216,path217,path218;

    public static List<Integer> loop = new ArrayList<>();
    public static List<Object> loop2 = new ArrayList<>();
    public static List<Object> loop3 = new ArrayList<>();
    int value,k, katsayi,bitti=0,ak=0,ak2=0;
    int kart1,kart2,kart3,kart4,kart5,kart6,kart7,kart8,kart9,kart10,kart11,kart12,kart13,kart14,kart15,kart16,kart17,kart18,kartt;
    ImageView acKapa;
    MediaPlayer music,music2,music3,music4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to6x6);

        acKapa = (ImageView) findViewById(R.id.acKapa);
        acKapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ak==0){music.setVolume(0,0);;ak=1;acKapa.setImageResource(R.drawable.mute);}
                else if(ak==1){ak=0;music.setVolume(1,1);acKapa.setImageResource(R.drawable.volume);}
            }
        });

        if(ak2==0){music = MediaPlayer.create(TO6x6.this, R.raw.prologue);music.start();ak2=1;}

        timer = (TextView) findViewById(R.id.timer);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                if(bitti==1)cancel();
            }
            public void onFinish() {
                timer.setText("00:00:00");
                if(ak!=1){music3 = MediaPlayer.create(TO6x6.this, R.raw.shocked);music3.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            music3.stop();
                        }
                    }, 2000);}//millisec.
                loop3.removeAll(loop3);
                loop2.removeAll(loop2);
                loop.removeAll(loop);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TO6x6.this);
                alertDialogBuilder
                        .setMessage("Bitti\n")
                        .setCancelable(false)
                        .setPositiveButton("new", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(),TO6x6.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("çıkış", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }.start();

        textView_o1 = (TextView) findViewById(R.id.tv_p1);
        textView_o2 = (TextView) findViewById(R.id.tv_p2);


        iv_11 =(ImageView) findViewById(R.id.iv_11);
        iv_12 =(ImageView) findViewById(R.id.iv_12);
        iv_13 =(ImageView) findViewById(R.id.iv_13);
        iv_14 =(ImageView) findViewById(R.id.iv_14);
        iv_15 =(ImageView) findViewById(R.id.iv_15);
        iv_16 =(ImageView) findViewById(R.id.iv_16);
        iv_21 =(ImageView) findViewById(R.id.iv_21);
        iv_22 =(ImageView) findViewById(R.id.iv_22);
        iv_23 =(ImageView) findViewById(R.id.iv_23);
        iv_24 =(ImageView) findViewById(R.id.iv_24);
        iv_25 =(ImageView) findViewById(R.id.iv_25);
        iv_26 =(ImageView) findViewById(R.id.iv_26);
        iv_31 =(ImageView) findViewById(R.id.iv_31);
        iv_32 =(ImageView) findViewById(R.id.iv_32);
        iv_33 =(ImageView) findViewById(R.id.iv_33);
        iv_34 =(ImageView) findViewById(R.id.iv_34);
        iv_35 =(ImageView) findViewById(R.id.iv_35);
        iv_36 =(ImageView) findViewById(R.id.iv_36);
        iv_41 =(ImageView) findViewById(R.id.iv_41);
        iv_42 =(ImageView) findViewById(R.id.iv_42);
        iv_43 =(ImageView) findViewById(R.id.iv_43);
        iv_44 =(ImageView) findViewById(R.id.iv_44);
        iv_45 =(ImageView) findViewById(R.id.iv_45);
        iv_46 =(ImageView) findViewById(R.id.iv_46);
        iv_51 =(ImageView) findViewById(R.id.iv_51);
        iv_52 =(ImageView) findViewById(R.id.iv_52);
        iv_53 =(ImageView) findViewById(R.id.iv_53);
        iv_54 =(ImageView) findViewById(R.id.iv_54);
        iv_55 =(ImageView) findViewById(R.id.iv_55);
        iv_56 =(ImageView) findViewById(R.id.iv_56);
        iv_61 =(ImageView) findViewById(R.id.iv_61);
        iv_62 =(ImageView) findViewById(R.id.iv_62);
        iv_63 =(ImageView) findViewById(R.id.iv_63);
        iv_64 =(ImageView) findViewById(R.id.iv_64);
        iv_65 =(ImageView) findViewById(R.id.iv_65);
        iv_66 =(ImageView) findViewById(R.id.iv_66);


        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_15.setTag("4");
        iv_16.setTag("5");
        iv_21.setTag("6");
        iv_22.setTag("7");
        iv_23.setTag("8");
        iv_24.setTag("9");
        iv_25.setTag("10");
        iv_26.setTag("11");
        iv_31.setTag("12");
        iv_32.setTag("13");
        iv_33.setTag("14");
        iv_34.setTag("15");
        iv_35.setTag("16");
        iv_36.setTag("17");
        iv_41.setTag("18");
        iv_42.setTag("19");
        iv_43.setTag("20");
        iv_44.setTag("21");
        iv_45.setTag("22");
        iv_46.setTag("23");
        iv_51.setTag("24");
        iv_52.setTag("25");
        iv_53.setTag("26");
        iv_54.setTag("27");
        iv_55.setTag("28");
        iv_56.setTag("29");
        iv_61.setTag("30");
        iv_62.setTag("31");
        iv_63.setTag("32");
        iv_64.setTag("33");
        iv_65.setTag("34");
        iv_66.setTag("35");
        loop.add(0);loop.add(1);loop.add(2);loop.add(3);

        loop2.add(link101);loop2.add(link102);loop2.add(link103);loop2.add(link104);loop2.add(link105);loop2.add(link106);loop2.add(link107);loop2.add(link108);loop2.add(link109);loop2.add(link110);loop2.add(link111);loop2.add(link112);loop2.add(link113);loop2.add(link114);loop2.add(link115);loop2.add(link116);loop2.add(link117);loop2.add(link118);
        loop2.add(link201);loop2.add(link202);loop2.add(link203);loop2.add(link204);loop2.add(link205);loop2.add(link206);loop2.add(link207);loop2.add(link208);loop2.add(link209);loop2.add(link210);loop2.add(link211);loop2.add(link212);loop2.add(link213);loop2.add(link214);loop2.add(link215);loop2.add(link216);loop2.add(link217);loop2.add(link218);

        loop3.add(path101);loop3.add(path102);loop3.add(path103);loop3.add(path104);loop3.add(path105);loop3.add(path106);loop3.add(path107);loop3.add(path108);loop3.add(path109);loop3.add(path110);loop3.add(path111);loop3.add(path112);loop3.add(path113);loop3.add(path114);loop3.add(path115);loop3.add(path116);loop3.add(path117);loop3.add(path118);
        loop3.add(path201);loop3.add(path202);loop3.add(path203);loop3.add(path204);loop3.add(path205);loop3.add(path206);loop3.add(path207);loop3.add(path208);loop3.add(path209);loop3.add(path210);loop3.add(path211);loop3.add(path212);loop3.add(path213);loop3.add(path214);loop3.add(path215);loop3.add(path216);loop3.add(path217);loop3.add(path218);

        firebaseResimAl(0);
        firebaseResimAl(1);
        firebaseResimAl(2);
        firebaseResimAl(3);
        firebaseResimAl(4);
        firebaseResimAl(5);
        firebaseResimAl(6);
        firebaseResimAl(7);
        firebaseResimAl(8);
        firebaseResimAl(9);
        firebaseResimAl(10);
        firebaseResimAl(11);
        firebaseResimAl(12);
        firebaseResimAl(13);
        firebaseResimAl(14);
        firebaseResimAl(15);
        firebaseResimAl(16);
        firebaseResimAl(17);

        Collections.shuffle(Arrays.asList(kartDizisi));

        textView_o2.setTextColor(Color.GRAY);

        iv_11.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_11,kart);}});
        iv_12.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_12,kart);}});
        iv_13.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_13,kart);}});
        iv_14.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_14,kart);}});
        iv_15.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_15,kart);}});
        iv_16.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_16,kart);}});
        iv_21.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_21,kart);}});
        iv_22.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_22,kart);}});
        iv_23.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_23,kart);}});
        iv_24.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_24,kart);}});
        iv_25.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_25,kart);}});
        iv_26.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_26,kart);}});
        iv_31.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_31,kart);}});
        iv_32.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_32,kart);}});
        iv_33.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_33,kart);}});
        iv_34.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_34,kart);}});
        iv_35.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_35,kart);}});
        iv_36.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_36,kart);}});
        iv_41.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_41,kart);}});
        iv_42.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_42,kart);}});
        iv_43.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_43,kart);}});
        iv_44.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_44,kart);}});
        iv_45.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_45,kart);}});
        iv_46.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_46,kart);}});
        iv_51.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_51,kart);}});
        iv_52.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_52,kart);}});
        iv_53.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_53,kart);}});
        iv_54.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_54,kart);}});
        iv_55.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_55,kart);}});
        iv_56.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_56,kart);}});
        iv_61.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_61,kart);}});
        iv_62.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_62,kart);}});
        iv_63.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_63,kart);}});
        iv_64.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_64,kart);}});
        iv_65.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_65,kart);}});
        iv_66.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {int kart = Integer.parseInt((String)view.getTag());
            incele(iv_66,kart);}});

    }

    //HESAPLAMADAN ONCE SECİLMİŞ 2 KARTIN BİLGİLERİ ilkKart VE ikinciKart a ATILIR
    private void incele(ImageView IV, int kart){
        if(kartDizisi[kart] == 101){ Picasso.get().load(link101).into(IV);}
        else if(kartDizisi[kart] == 102){ Picasso.get().load(link102).into(IV);}
        else if(kartDizisi[kart] == 103){ Picasso.get().load(link103).into(IV);}
        else if(kartDizisi[kart] == 104){ Picasso.get().load(link104).into(IV);}
        else if(kartDizisi[kart] == 105){ Picasso.get().load(link105).into(IV);}
        else if(kartDizisi[kart] == 106){ Picasso.get().load(link106).into(IV);}
        else if(kartDizisi[kart] == 107){ Picasso.get().load(link107).into(IV);}
        else if(kartDizisi[kart] == 108){ Picasso.get().load(link108).into(IV);}
        else if(kartDizisi[kart] == 109){ Picasso.get().load(link109).into(IV);}
        else if(kartDizisi[kart] == 110){ Picasso.get().load(link110).into(IV);}
        else if(kartDizisi[kart] == 111){ Picasso.get().load(link111).into(IV);}
        else if(kartDizisi[kart] == 112){ Picasso.get().load(link112).into(IV);}
        else if(kartDizisi[kart] == 113){ Picasso.get().load(link113).into(IV);}
        else if(kartDizisi[kart] == 114){ Picasso.get().load(link114).into(IV);}
        else if(kartDizisi[kart] == 115){ Picasso.get().load(link115).into(IV);}
        else if(kartDizisi[kart] == 116){ Picasso.get().load(link116).into(IV);}
        else if(kartDizisi[kart] == 117){ Picasso.get().load(link116).into(IV);}
        else if(kartDizisi[kart] == 118){ Picasso.get().load(link116).into(IV);}
        else if(kartDizisi[kart] == 201){ Picasso.get().load(link201).into(IV);}
        else if(kartDizisi[kart] == 202){ Picasso.get().load(link202).into(IV);}
        else if(kartDizisi[kart] == 203){ Picasso.get().load(link203).into(IV);}
        else if(kartDizisi[kart] == 204){ Picasso.get().load(link204).into(IV);}
        else if(kartDizisi[kart] == 205){ Picasso.get().load(link205).into(IV);}
        else if(kartDizisi[kart] == 206){ Picasso.get().load(link206).into(IV);}
        else if(kartDizisi[kart] == 207){ Picasso.get().load(link207).into(IV);}
        else if(kartDizisi[kart] == 208){ Picasso.get().load(link208).into(IV);}
        else if(kartDizisi[kart] == 209){ Picasso.get().load(link209).into(IV);}
        else if(kartDizisi[kart] == 210){ Picasso.get().load(link210).into(IV);}
        else if(kartDizisi[kart] == 211){ Picasso.get().load(link211).into(IV);}
        else if(kartDizisi[kart] == 212){ Picasso.get().load(link212).into(IV);}
        else if(kartDizisi[kart] == 213){ Picasso.get().load(link213).into(IV);}
        else if(kartDizisi[kart] == 214){ Picasso.get().load(link214).into(IV);}
        else if(kartDizisi[kart] == 215){ Picasso.get().load(link215).into(IV);}
        else if(kartDizisi[kart] == 216){ Picasso.get().load(link216).into(IV);}
        else if(kartDizisi[kart] == 217){ Picasso.get().load(link216).into(IV);}
        else if(kartDizisi[kart] == 218){ Picasso.get().load(link216).into(IV);}

        if(kartSayisi == 1){
            ilkKart = kartDizisi[kart];
            if(ilkKart > 200){
                ilkKart = ilkKart - 100;
            }
            kartSayisi = 2;
            ilkTiklanan = kart;

            IV.setEnabled(false);
        } else if (kartSayisi == 2) {
            ikinciKart = kartDizisi[kart];
            if(ikinciKart > 200){
                ikinciKart = ikinciKart - 100;
            }
            kartSayisi = 1;
            ikinciTiklanan = kart;

            iv_11.setEnabled(false);iv_12.setEnabled(false);iv_13.setEnabled(false);iv_14.setEnabled(false);iv_15.setEnabled(false);;iv_16.setEnabled(false);
            iv_21.setEnabled(false);iv_22.setEnabled(false);iv_23.setEnabled(false);iv_24.setEnabled(false);iv_25.setEnabled(false);iv_26.setEnabled(false);
            iv_31.setEnabled(false);iv_32.setEnabled(false);iv_33.setEnabled(false);iv_34.setEnabled(false);iv_35.setEnabled(false);iv_36.setEnabled(false);
            iv_41.setEnabled(false);iv_42.setEnabled(false);iv_43.setEnabled(false);iv_44.setEnabled(false);iv_45.setEnabled(false);iv_46.setEnabled(false);
            iv_51.setEnabled(false);iv_52.setEnabled(false);iv_53.setEnabled(false);iv_54.setEnabled(false);iv_55.setEnabled(false);iv_56.setEnabled(false);
            iv_61.setEnabled(false);iv_62.setEnabled(false);iv_63.setEnabled(false);iv_64.setEnabled(false);iv_65.setEnabled(false);iv_66.setEnabled(false);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    hesaplama();
                }
            },1500);
        }
    }

    private void hesaplama(){
        if(ilkKart == ikinciKart){
            if(ilkTiklanan == 0){iv_11.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 1){iv_12.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 2){iv_13.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 3){iv_14.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 4){iv_15.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 5){iv_16.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 6){iv_21.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 7){iv_22.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 8){iv_23.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 9){iv_24.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 10){iv_25.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 11){iv_26.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 12){iv_31.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 13){iv_32.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 14){iv_33.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 15){iv_34.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 16){iv_35.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 17){iv_36.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 18){iv_41.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 19){iv_42.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 20){iv_43.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 21){iv_44.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 22){iv_45.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 23){iv_46.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 24){iv_51.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 25){iv_52.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 26){iv_53.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 27){iv_54.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 28){iv_55.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 29){iv_56.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 30){iv_61.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 31){iv_62.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 32){iv_63.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 33){iv_64.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 34){iv_65.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 35){iv_66.setVisibility(View.INVISIBLE);}

            if(ikinciTiklanan == 0){iv_11.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 1){iv_12.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 2){iv_13.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 3){iv_14.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 4){iv_15.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 5){iv_16.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 6){iv_21.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 7){iv_22.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 8){iv_23.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 9){iv_24.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 10){iv_25.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 11){iv_26.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 12){iv_31.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 13){iv_32.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 14){iv_33.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 15){iv_34.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 16){iv_35.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 17){iv_36.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 18){iv_41.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 19){iv_42.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 20){iv_43.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 21){iv_44.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 22){iv_45.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 23){iv_46.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 24){iv_51.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 25){iv_52.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 26){iv_53.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 27){iv_54.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 28){iv_55.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 29){iv_56.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 30){iv_61.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 31){iv_62.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 32){iv_63.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 33){iv_64.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 34){iv_65.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 35){iv_66.setVisibility(View.INVISIBLE);}

            if(sira == 1){
                if(ak!=1){music2 = MediaPlayer.create(TO6x6.this, R.raw.victorytheme);music2.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            music2.stop();
                        }
                    }, 1000);}//millisec.
                getdata(ilkKart);
            } else if(sira == 2){
                if(ak!=1){music2 = MediaPlayer.create(TO6x6.this, R.raw.victorytheme);music2.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            music2.stop();
                        }
                    }, 1000);}//millisec.
                getdata(ilkKart);
            }
        } else {
            iv_11.setImageResource(R.drawable.back);iv_12.setImageResource(R.drawable.back);iv_13.setImageResource(R.drawable.back);iv_14.setImageResource(R.drawable.back);iv_15.setImageResource(R.drawable.back);;iv_16.setImageResource(R.drawable.back);
            iv_21.setImageResource(R.drawable.back);iv_22.setImageResource(R.drawable.back);iv_23.setImageResource(R.drawable.back);iv_24.setImageResource(R.drawable.back);iv_25.setImageResource(R.drawable.back);iv_26.setImageResource(R.drawable.back);
            iv_31.setImageResource(R.drawable.back);iv_32.setImageResource(R.drawable.back);iv_33.setImageResource(R.drawable.back);iv_34.setImageResource(R.drawable.back);iv_35.setImageResource(R.drawable.back);iv_36.setImageResource(R.drawable.back);
            iv_41.setImageResource(R.drawable.back);iv_42.setImageResource(R.drawable.back);iv_43.setImageResource(R.drawable.back);iv_44.setImageResource(R.drawable.back);iv_45.setImageResource(R.drawable.back);iv_46.setImageResource(R.drawable.back);
            iv_51.setImageResource(R.drawable.back);iv_52.setImageResource(R.drawable.back);iv_53.setImageResource(R.drawable.back);iv_54.setImageResource(R.drawable.back);iv_55.setImageResource(R.drawable.back);iv_56.setImageResource(R.drawable.back);
            iv_61.setImageResource(R.drawable.back);iv_62.setImageResource(R.drawable.back);iv_63.setImageResource(R.drawable.back);iv_64.setImageResource(R.drawable.back);iv_65.setImageResource(R.drawable.back);iv_66.setImageResource(R.drawable.back);
            if(sira ==1){
                int result1,result2,k1,k2;
                result1=getdata2(ilkKart);
                k1= katsayi;
                result2=getdata2(ikinciKart);
                k2= katsayi;
                if(k1==k2){
                    oyuncu1 =( oyuncu1 - ( (result1+result2)/k1 ) );
                    textView_o1.setText("o1: "+ oyuncu1);}
                else {
                    oyuncu1 = (oyuncu1 - (((result1+result2)/2)*k1*k2) );
                    textView_o1.setText("o1: "+ oyuncu1);}
                sira =2;
                textView_o1.setTextColor(Color.GRAY);
                textView_o2.setTextColor(Color.CYAN);
            } else if(sira ==2){
                int result1,result2,k1,k2;
                result1=getdata2(ilkKart);
                k1= katsayi;
                result2=getdata2(ikinciKart);
                k2= katsayi;
                if(k1==k2){
                    oyuncu2 =( oyuncu2 - ( (result1+result2)/k1 ) );
                    textView_o2.setText("o2: "+ oyuncu2);}
                else {
                    oyuncu2 = (oyuncu2 - (((result1+result2)/2)*k1*k2) );
                    textView_o2.setText("o2: "+ oyuncu2);}
                sira =1;
                textView_o1.setTextColor(Color.CYAN);
                textView_o2.setTextColor(Color.GRAY);
            }
        }

        iv_11.setEnabled(true);iv_12.setEnabled(true);iv_13.setEnabled(true);iv_14.setEnabled(true);iv_15.setEnabled(true);;iv_16.setEnabled(true);
        iv_21.setEnabled(true);iv_22.setEnabled(true);iv_23.setEnabled(true);iv_24.setEnabled(true);iv_25.setEnabled(true);iv_26.setEnabled(true);
        iv_31.setEnabled(true);iv_32.setEnabled(true);iv_33.setEnabled(true);iv_34.setEnabled(true);iv_35.setEnabled(true);iv_36.setEnabled(true);
        iv_41.setEnabled(true);iv_42.setEnabled(true);iv_43.setEnabled(true);iv_44.setEnabled(true);iv_45.setEnabled(true);iv_46.setEnabled(true);
        iv_51.setEnabled(true);iv_52.setEnabled(true);iv_53.setEnabled(true);iv_54.setEnabled(true);iv_55.setEnabled(true);iv_56.setEnabled(true);
        iv_61.setEnabled(true);iv_62.setEnabled(true);iv_63.setEnabled(true);iv_64.setEnabled(true);iv_65.setEnabled(true);iv_66.setEnabled(true);

        oyunSonuKontrolu();
    }

    //OYUN SONU KONTROL EDER
    private void oyunSonuKontrolu(){
        if(iv_11.getVisibility()==View.INVISIBLE && iv_12.getVisibility()==View.INVISIBLE && iv_13.getVisibility()==View.INVISIBLE && iv_14.getVisibility()==View.INVISIBLE && iv_15.getVisibility()==View.INVISIBLE && iv_16.getVisibility()==View.INVISIBLE &&
                iv_21.getVisibility()==View.INVISIBLE && iv_22.getVisibility()==View.INVISIBLE && iv_23.getVisibility()==View.INVISIBLE && iv_24.getVisibility()==View.INVISIBLE && iv_25.getVisibility()==View.INVISIBLE&& iv_26.getVisibility()==View.INVISIBLE &&
                iv_31.getVisibility()==View.INVISIBLE && iv_32.getVisibility()==View.INVISIBLE && iv_33.getVisibility()==View.INVISIBLE && iv_34.getVisibility()==View.INVISIBLE && iv_35.getVisibility()==View.INVISIBLE &&iv_36.getVisibility()==View.INVISIBLE &&
                iv_41.getVisibility()==View.INVISIBLE && iv_42.getVisibility()==View.INVISIBLE && iv_43.getVisibility()==View.INVISIBLE && iv_44.getVisibility()==View.INVISIBLE && iv_45.getVisibility()==View.INVISIBLE &&iv_46.getVisibility()==View.INVISIBLE && iv_51.getVisibility()==View.INVISIBLE && iv_52.getVisibility()==View.INVISIBLE && iv_53.getVisibility()==View.INVISIBLE && iv_54.getVisibility()==View.INVISIBLE && iv_55.getVisibility()==View.INVISIBLE && iv_56.getVisibility()==View.INVISIBLE &&
                iv_61.getVisibility()==View.INVISIBLE && iv_62.getVisibility()==View.INVISIBLE && iv_63.getVisibility()==View.INVISIBLE && iv_64.getVisibility()==View.INVISIBLE && iv_65.getVisibility()==View.INVISIBLE && iv_66.getVisibility()==View.INVISIBLE){
            bitti=1;
            if(ak!=1){music4 = MediaPlayer.create(TO6x6.this, R.raw.congratulations);music4.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        music4.stop();
                    }
                }, 6000);}//millisec.
            loop3.removeAll(loop3);
            loop2.removeAll(loop2);
            loop.removeAll(loop);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TO6x6.this);
            alertDialogBuilder
                    .setMessage("Oyun Sona Erdi\n")
                    .setCancelable(false)
                    .setPositiveButton("new", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(),TO6x6.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("çık", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            music.stop();
            AlertDialog alertDialog1 = alertDialogBuilder.create();
            alertDialog1.show();
        }
    }

    //EVLERDEN KARTARI CEKİP DAHA SONRA KULLANILACAK BİLGİLERİNİ DEGİSKENLERE ATAR
    private void firebaseResimAl(int k){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        Random rand = new Random();
        int kartId = rand.nextInt(11) + 101;
        int houseNumber=0;

        if(k==0 || k==1  || k==2 || k==3 || k==4 || k==5 || k==6 || k==7 )houseNumber=0;
        else if(k==8 || k==9  || k==10 || k==11 || k==12 || k==13 || k==14 || k==15 )houseNumber=1;
        else if(k==16 || k==17  || k==18 || k==19 || k==20 || k==21 || k==22 || k==23 || k==24 || k==25)houseNumber=2;
        else if(k==26 || k==27  || k==28 || k==29 || k==30 || k==31 || k==32 || k==33 || k==34 || k==35)houseNumber=3;

        if     (houseNumber == 0 ) loop3.set(k,"gryffindor");
        else if(houseNumber == 1 ) loop3.set(k,"slytherin");
        else if(houseNumber == 2 ) loop3.set(k,"ravenclaw");
        else if(houseNumber == 3 ) loop3.set(k,"hufflepuff");

        DatabaseReference getImage = databaseReference.child(loop3.get(k).toString()+"Images").child(""+kartId);

        getImage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot) {

                if(k == 0){
                    link101 = dataSnapshot.getValue(String.class);link201 = dataSnapshot.getValue(String.class);kart1=kartId;}
                else if(k == 1){
                    link102 = dataSnapshot.getValue(String.class);link202 = dataSnapshot.getValue(String.class);kart2=kartId;}
                if(k == 2){
                    link103 = dataSnapshot.getValue(String.class);link203 = dataSnapshot.getValue(String.class);kart3=kartId;}
                else if(k == 3){
                    link104 = dataSnapshot.getValue(String.class);link204 = dataSnapshot.getValue(String.class);kart4=kartId;}
                else if(k == 4){
                    link105 = dataSnapshot.getValue(String.class);link205 = dataSnapshot.getValue(String.class);kart5=kartId;}
                else if(k == 5){
                    link106 = dataSnapshot.getValue(String.class);link206 = dataSnapshot.getValue(String.class);kart6=kartId;}
                else if(k == 6){
                    link107 = dataSnapshot.getValue(String.class);link207 = dataSnapshot.getValue(String.class);kart7=kartId;}
                else if(k == 7){
                    link108 = dataSnapshot.getValue(String.class);link208 = dataSnapshot.getValue(String.class);kart8=kartId;}
                else if(k == 8){
                    link109 = dataSnapshot.getValue(String.class);link209 = dataSnapshot.getValue(String.class);kart9=kartId;}
                else if(k == 9){
                    link110 = dataSnapshot.getValue(String.class);link210 = dataSnapshot.getValue(String.class);kart10=kartId;}
                else if(k == 10){
                    link111 = dataSnapshot.getValue(String.class);link211 = dataSnapshot.getValue(String.class);kart11=kartId;}
                else if(k == 11){
                    link112 = dataSnapshot.getValue(String.class);link212 = dataSnapshot.getValue(String.class);kart12=kartId;}
                else if(k == 12){
                    link113 = dataSnapshot.getValue(String.class);link213 = dataSnapshot.getValue(String.class);kart13=kartId;}
                else if(k == 13){
                    link114 = dataSnapshot.getValue(String.class);link214 = dataSnapshot.getValue(String.class);kart14=kartId;}
                else if(k == 14){
                    link115 = dataSnapshot.getValue(String.class);link215 = dataSnapshot.getValue(String.class);kart15=kartId;}
                else if(k == 15){
                    link116 = dataSnapshot.getValue(String.class);link216 = dataSnapshot.getValue(String.class);kart16=kartId;}
                else if(k == 16){
                    link117 = dataSnapshot.getValue(String.class);link217 = dataSnapshot.getValue(String.class);kart17=kartId;}
                else if(k == 17){
                    link118 = dataSnapshot.getValue(String.class);link218 = dataSnapshot.getValue(String.class);kart18=kartId;}

            }
            @Override
            public void onCancelled( @NonNull DatabaseError databaseError) {
                Toast.makeText(TO6x6.this, "Resim Yüklerken Sorun Oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //DOGRU KART ESLESMESİ DURUMUNDA CAGRILIP FİREBASEDEKİ KART PUANLARINI ALARAK HESAP YAPAR, TEXTLERİ GUNCELLER
    private void getdata(int kart) {
        if     (kart==101 || kart ==201){k=0;kartt=kart1;}
        else if(kart==102 || kart ==202){k=1;kartt=kart2;}
        else if(kart==103 || kart ==203){k=2;kartt=kart3;}
        else if(kart==104 || kart ==204){k=3;kartt=kart4;}
        else if(kart==105 || kart ==205){k=4;kartt=kart5;}
        else if(kart==106 || kart ==206){k=5;kartt=kart6;}
        else if(kart==107 || kart ==207){k=6;kartt=kart7;}
        else if(kart==108 || kart ==208){k=7;kartt=kart8;}
        else if(kart==109 || kart ==209){k=8;kartt=kart9;}
        else if(kart==110 || kart ==210){k=9;kartt=kart10;}
        else if(kart==111 || kart ==211){k=10;kartt=kart11;}
        else if(kart==112 || kart ==212){k=11;kartt=kart12;}
        else if(kart==113 || kart ==213){k=12;kartt=kart13;}
        else if(kart==114 || kart ==214){k=13;kartt=kart14;}
        else if(kart==115 || kart ==215){k=14;kartt=kart15;}
        else if(kart==116 || kart ==216){k=15;kartt=kart16;}
        else if(kart==117 || kart ==217){k=16;kartt=kart17;}
        else if(kart==118 || kart ==218){k=17;kartt=kart18;}

        firebaseDatabase2 = FirebaseDatabase.getInstance();
        databaseReference2 = firebaseDatabase2.getReference(loop3.get(k).toString()).child(""+kartt);

        if     (loop3.get(k).equals("grryffindor")) katsayi =2;
        else if(loop3.get(k).equals("slytherin")) katsayi =2;
        else if(loop3.get(k).equals("ravenclaw")) katsayi =1;
        else if(loop3.get(k).equals("hufflepuff")) katsayi =1;
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value = snapshot.getValue(Integer.class);
                Log.i("kartpuanları",""+value);
                Toast.makeText(TO6x6.this,""+value,Toast.LENGTH_SHORT).show();

                if(sira ==1){
                    oyuncu1 = (oyuncu1 + (2*value* katsayi));
                    textView_o1.setText("o1: "+ oyuncu1);}
                else if(sira ==2){
                    oyuncu2 = (oyuncu2 + (2*value* katsayi));
                    textView_o2.setText("o2: "+ oyuncu2);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TO6x6.this, "Kart bilgisi alınamadı", Toast.LENGTH_SHORT).show();
                value = -1;
            }

        });
    }

    //YANLIS KART ESLESMESİ DURUMUNDA YAPILACAK HESAP İÇİN CAGRILIP FİREBASEDEN PUAN CEKER
    private int getdata2(int kart) {
        if     (kart==101 || kart ==201){k=0;kartt=kart1;}
        else if(kart==102 || kart ==202){k=1;kartt=kart2;}
        else if(kart==103 || kart ==203){k=2;kartt=kart3;}
        else if(kart==104 || kart ==204){k=3;kartt=kart4;}
        else if(kart==105 || kart ==205){k=4;kartt=kart5;}
        else if(kart==106 || kart ==206){k=5;kartt=kart6;}
        else if(kart==107 || kart ==207){k=6;kartt=kart7;}
        else if(kart==108 || kart ==208){k=7;kartt=kart8;}
        else if(kart==109 || kart ==209){k=8;kartt=kart9;}
        else if(kart==110 || kart ==210){k=9;kartt=kart10;}
        else if(kart==111 || kart ==211){k=10;kartt=kart11;}
        else if(kart==112 || kart ==212){k=11;kartt=kart12;}
        else if(kart==113 || kart ==213){k=12;kartt=kart13;}
        else if(kart==114 || kart ==214){k=13;kartt=kart14;}
        else if(kart==115 || kart ==215){k=14;kartt=kart15;}
        else if(kart==116 || kart ==216){k=15;kartt=kart16;}
        else if(kart==117 || kart ==217){k=16;kartt=kart17;}
        else if(kart==118 || kart ==218){k=17;kartt=kart18;}

        firebaseDatabase2 = FirebaseDatabase.getInstance();
        databaseReference2 = firebaseDatabase2.getReference(loop3.get(k).toString()).child(""+kartt);

        if     (loop3.get(k).equals("grryffindor")) katsayi =2;
        else if(loop3.get(k).equals("slytherin")) katsayi =2;
        else if(loop3.get(k).equals("ravenclaw")) katsayi =1;
        else if(loop3.get(k).equals("hufflepuff")) katsayi =1;
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value = snapshot.getValue(Integer.class);
                Toast.makeText(TO6x6.this,""+value,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TO6x6.this, "Kart bilgisi alınamadı", Toast.LENGTH_SHORT).show();
                value = -1;
            }

        });
        return value;
    }

}