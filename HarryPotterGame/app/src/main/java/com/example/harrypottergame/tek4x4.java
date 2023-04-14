package com.example.harrypottergame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

public class tek4x4 extends AppCompatActivity {

    TextView textView_o1, timer;
    ImageView iv_11,iv_12,iv_13,iv_14,iv_21,iv_22,iv_23,iv_24,iv_31,iv_32,iv_33,iv_34,iv_41,iv_42,iv_43,iv_44;
    Integer[] kartDizisi = {101,102,103,104,105,106,107,108,201,202,203,204,205,206,207,208};

    FirebaseDatabase firebaseDatabase2;
    DatabaseReference databaseReference2;

    int ilkKart, ikinciKart;
    int ilkTiklanan, ikinciTiklanan;
    int kartSayisi = 1;
    int sira = 1;
    int oyuncu1 =0, oyuncu2 =0;
    String link101,link102,link103,link104,link105,link106,link107,link108,link201,link202,link203,link204,link205,link206,link207,link208;
    String path101,path102,path103,path104,path105,path106,path107,path108,path201,path202,path203,path204,path205,path206,path207,path208;
    public static List<Integer> loop = new ArrayList<>();
    public static List<Object> loop2 = new ArrayList<>();
    public static List<Object> loop3 = new ArrayList<>();
    int value,k, katsayi, sure,bitti=0,ak=0,ak2=0;
    int kart1,kart2,kart3,kart4,kart5,kart6,kart7,kart8,kartt;
    ImageView acKapa;
    MediaPlayer music,music2,music3,music4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tek4x4);

        acKapa = (ImageView) findViewById(R.id.acKapa);
        acKapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ak==0){music.setVolume(0,0);;ak=1;acKapa.setImageResource(R.drawable.mute);}
                else if(ak==1){ak=0;music.setVolume(1,1);acKapa.setImageResource(R.drawable.volume);}
            }
        });

        if(ak2==0){music = MediaPlayer.create(tek4x4.this, R.raw.prologue);music.start();ak2=1;}

        timer = (TextView) findViewById(R.id.timer);
        new CountDownTimer(45000, 1000) {

            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timer.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                sure = Integer.parseInt(f.format(sec));
                if(bitti==1)cancel();
            }
            public void onFinish() {
                timer.setText("00:00:00");
                if(ak!=1){music3 = MediaPlayer.create(tek4x4.this, R.raw.shocked);music3.start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            music3.stop();
                        }
                    }, 2000);}//millisec.
                loop3.removeAll(loop3);
                loop2.removeAll(loop2);
                loop.removeAll(loop);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(tek4x4.this);
                alertDialogBuilder
                        .setMessage("Bitti\n")
                        .setCancelable(false)
                        .setPositiveButton("new", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(),tek4x4.class);
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

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);
        iv_14 = (ImageView) findViewById(R.id.iv_14);
        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);
        iv_24 = (ImageView) findViewById(R.id.iv_24);
        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);
        iv_34 = (ImageView) findViewById(R.id.iv_34);
        iv_41 = (ImageView) findViewById(R.id.iv_41);
        iv_42 = (ImageView) findViewById(R.id.iv_42);
        iv_43 = (ImageView) findViewById(R.id.iv_43);
        iv_44 = (ImageView) findViewById(R.id.iv_44);

        iv_11.setTag("0");iv_12.setTag("1");iv_13.setTag("2");iv_14.setTag("3");
        iv_21.setTag("4");iv_22.setTag("5");iv_23.setTag("6");iv_24.setTag("7");
        iv_31.setTag("8");iv_32.setTag("9");iv_33.setTag("10");iv_34.setTag("11");
        iv_41.setTag("12");iv_42.setTag("13");iv_43.setTag("14");iv_44.setTag("15");
        loop.add(0);loop.add(1);loop.add(2);loop.add(3);

        loop2.add(link101);loop2.add(link102);loop2.add(link103);loop2.add(link104);loop2.add(link105);loop2.add(link106);loop2.add(link107);loop2.add(link108);
        loop2.add(link201);loop2.add(link202);loop2.add(link203);loop2.add(link204);loop2.add(link205);loop2.add(link206);loop2.add(link207);loop2.add(link208);

        loop3.add(path101);loop3.add(path102);loop3.add(path103);loop3.add(path104);loop3.add(path105);loop3.add(path106);loop3.add(path107);loop3.add(path108);

        firebaseResimAl(0);
        firebaseResimAl(1);
        firebaseResimAl(2);
        firebaseResimAl(3);
        firebaseResimAl(4);
        firebaseResimAl(5);
        firebaseResimAl(6);
        firebaseResimAl(7);

        Collections.shuffle(Arrays.asList(kartDizisi));


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
        else if(kartDizisi[kart] == 201){ Picasso.get().load(link201).into(IV);}
        else if(kartDizisi[kart] == 202){ Picasso.get().load(link202).into(IV);}
        else if(kartDizisi[kart] == 203){ Picasso.get().load(link203).into(IV);}
        else if(kartDizisi[kart] == 204){ Picasso.get().load(link204).into(IV);}
        else if(kartDizisi[kart] == 205){ Picasso.get().load(link205).into(IV);}
        else if(kartDizisi[kart] == 206){ Picasso.get().load(link206).into(IV);}
        else if(kartDizisi[kart] == 207){ Picasso.get().load(link207).into(IV);}
        else if(kartDizisi[kart] == 208){ Picasso.get().load(link208).into(IV);}

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

            iv_11.setEnabled(false);iv_12.setEnabled(false);iv_13.setEnabled(false);iv_14.setEnabled(false);
            iv_21.setEnabled(false);iv_22.setEnabled(false);iv_23.setEnabled(false);iv_24.setEnabled(false);
            iv_31.setEnabled(false);iv_32.setEnabled(false);iv_33.setEnabled(false);iv_34.setEnabled(false);
            iv_41.setEnabled(false);iv_42.setEnabled(false);iv_43.setEnabled(false);iv_44.setEnabled(false);

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
            else if(ilkTiklanan == 4){iv_21.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 5){iv_22.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 6){iv_23.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 7){iv_24.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 8){iv_31.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 9){iv_32.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 10){iv_33.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 11){iv_34.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 12){iv_41.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 13){iv_42.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 14){iv_43.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 15){iv_44.setVisibility(View.INVISIBLE);}

            if(ikinciTiklanan == 0){iv_11.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 1){iv_12.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 2){iv_13.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 3){iv_14.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 4){iv_21.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 5){iv_22.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 6){iv_23.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 7){iv_24.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 8){iv_31.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 9){iv_32.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 10){iv_33.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 11){iv_34.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 12){iv_41.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 13){iv_42.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 14){iv_43.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 15){iv_44.setVisibility(View.INVISIBLE);}

            //if(sira == 1){
            if(ak!=1){
                music2 = MediaPlayer.create(tek4x4.this, R.raw.victorytheme);
                music2.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        music2.stop();
                    }
                }, 1000);
            }//millisec.
            getdata(ilkKart);

        } else {
            iv_11.setImageResource(R.drawable.back);iv_12.setImageResource(R.drawable.back);iv_13.setImageResource(R.drawable.back);iv_14.setImageResource(R.drawable.back);
            iv_21.setImageResource(R.drawable.back);iv_22.setImageResource(R.drawable.back);iv_23.setImageResource(R.drawable.back);iv_24.setImageResource(R.drawable.back);
            iv_31.setImageResource(R.drawable.back);iv_32.setImageResource(R.drawable.back);iv_33.setImageResource(R.drawable.back);iv_34.setImageResource(R.drawable.back);
            iv_41.setImageResource(R.drawable.back);iv_42.setImageResource(R.drawable.back);iv_43.setImageResource(R.drawable.back);iv_44.setImageResource(R.drawable.back);

            //if(sira ==1){
                int result1,result2,k1,k2;
                result1=getdata2(ilkKart);
                k1= katsayi;
                result2=getdata2(ikinciKart);
                k2= katsayi;
                if(k1==k2){
                    double payda=((45-sure)/10);
                    double hesap = ( ((result1+result2)/k1)*payda);
                    oyuncu1 =( oyuncu1 - (int)hesap );
                    textView_o1.setText("oyuncu: "+ oyuncu1);}
                else {
                    double payda=((45-sure)/10);
                    double hesap = ( (((result1+result2)/2)*k1*k2)*payda);
                    oyuncu1 = (oyuncu1 - (int)hesap );
                    textView_o1.setText("oyuncu: "+ oyuncu1);}
        }

        iv_11.setEnabled(true);iv_12.setEnabled(true);iv_13.setEnabled(true);iv_14.setEnabled(true);
        iv_21.setEnabled(true);iv_22.setEnabled(true);iv_23.setEnabled(true);iv_24.setEnabled(true);
        iv_31.setEnabled(true);iv_32.setEnabled(true);iv_33.setEnabled(true);iv_34.setEnabled(true);
        iv_41.setEnabled(true);iv_42.setEnabled(true);iv_43.setEnabled(true);iv_44.setEnabled(true);

        oyunSonuKontrolu();
    }

    //OYUN SONU KONTROL EDER
    private void oyunSonuKontrolu(){
        if(iv_11.getVisibility()==View.INVISIBLE && iv_12.getVisibility()==View.INVISIBLE && iv_13.getVisibility()==View.INVISIBLE && iv_14.getVisibility()==View.INVISIBLE &&
                iv_21.getVisibility()==View.INVISIBLE && iv_22.getVisibility()==View.INVISIBLE && iv_23.getVisibility()==View.INVISIBLE && iv_24.getVisibility()==View.INVISIBLE &&
                iv_31.getVisibility()==View.INVISIBLE && iv_32.getVisibility()==View.INVISIBLE && iv_33.getVisibility()==View.INVISIBLE && iv_34.getVisibility()==View.INVISIBLE &&
                iv_41.getVisibility()==View.INVISIBLE && iv_42.getVisibility()==View.INVISIBLE && iv_43.getVisibility()==View.INVISIBLE && iv_44.getVisibility()==View.INVISIBLE){
            bitti=1;
            if(ak!=1){music4 = MediaPlayer.create(tek4x4.this, R.raw.congratulations);music4.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        music4.stop();
                    }
                }, 6000);}//millisec.
            loop3.removeAll(loop3);
            loop2.removeAll(loop2);
            loop.removeAll(loop);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(tek4x4.this);
            alertDialogBuilder
                    .setMessage("Oyun Sona Erdi\n")
                    .setCancelable(false)
                    .setPositiveButton("new", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(),tek4x4.class);
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

        if(k==0 || k==1 )houseNumber=0;
        else if(k==2 || k==3 )houseNumber=1;
        else if(k==4 || k==5 )houseNumber=2;
        else if(k==6 || k==7 )houseNumber=3;

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
                else if(k == 2){
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

            }
            @Override
            public void onCancelled( @NonNull DatabaseError databaseError) {
                Toast.makeText(tek4x4.this, "Resim Yüklerken Sorun Oluştu", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(tek4x4.this,""+value,Toast.LENGTH_SHORT).show();

                //if(sira ==1){
                oyuncu1 = ( oyuncu1 + ((2*value* katsayi)*(sure/10)) );
                textView_o1.setText("oyuncu: "+ oyuncu1);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tek4x4.this, "Kart bilgisi alınamadı", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(tek4x4.this,""+value,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tek4x4.this, "Kart bilgisi alınamadı", Toast.LENGTH_SHORT).show();
                value = -1;
            }

        });
        return value;
    }

}