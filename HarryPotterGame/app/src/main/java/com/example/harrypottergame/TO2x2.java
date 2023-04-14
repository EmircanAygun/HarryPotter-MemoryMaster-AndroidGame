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

public class TO2x2 extends AppCompatActivity {

    TextView textView_o1, textView_o2,timer;
    ImageView iv_11,iv_12,iv_21,iv_22;
    Integer[] kartDizisi = {101,102,201,202};

    FirebaseDatabase firebaseDatabase2;
    DatabaseReference databaseReference2;

    int ilkKart, ikinciKart;
    int ilkTiklanan, ikinciTiklanan;
    int kartSayisi = 1;
    int sira = 1;
    int oyuncu1 =0, oyuncu2 =0;
    String link101,link102,link201,link202;
    String path101,path102,path201,path202;
    public static List<Integer> loop = new ArrayList<>();
    public static List<Object> loop2 = new ArrayList<>();
    public static List<Object> loop3 = new ArrayList<>();
    int value,k, katsayi,bitti=0,ak=0,ak2=0;
    int kart1,kart2,kartt;
    ImageView acKapa;
    MediaPlayer music,music2,music3,music4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to2x2);

        acKapa = (ImageView) findViewById(R.id.acKapa);
        acKapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ak==0){music.setVolume(0,0);;ak=1;acKapa.setImageResource(R.drawable.mute);}
                else if(ak==1){ak=0;music.setVolume(1,1);acKapa.setImageResource(R.drawable.volume);}
            }
        });

        if(ak2==0){music = MediaPlayer.create(TO2x2.this, R.raw.prologue);music.start();ak2=1;}

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
                if(ak!=1){music3 = MediaPlayer.create(TO2x2.this, R.raw.shocked);music3.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        music3.stop();
                    }
                }, 2000);}//millisec.
                loop3.removeAll(loop3);
                loop2.removeAll(loop2);
                loop.removeAll(loop);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TO2x2.this);
                alertDialogBuilder
                        .setMessage("Bitti\n")
                        .setCancelable(false)
                        .setPositiveButton("new", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getApplicationContext(),TO2x2.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("çıkış", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                music.stop();
                                finish();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }.start();

        textView_o1 = (TextView) findViewById(R.id.tv_p1);
        textView_o2 = (TextView) findViewById(R.id.tv_p2);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_21.setTag("2");
        iv_22.setTag("3");
        loop.add(0);loop.add(1);loop.add(2);loop.add(3);
        loop2.add(link101);loop2.add(link102);loop2.add(link201);loop2.add(link202);
        loop3.add(path101);loop3.add(path102);

        firebaseResimAl(0);
        firebaseResimAl(1);

        Collections.shuffle(Arrays.asList(kartDizisi));

        textView_o2.setTextColor(Color.GRAY);

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kart = Integer.parseInt((String)view.getTag());
                incele(iv_11,kart);
            }
        });
        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kart = Integer.parseInt((String)view.getTag());
                incele(iv_12,kart);
            }
        });
        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kart = Integer.parseInt((String)view.getTag());
                incele(iv_21,kart);
            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int kart = Integer.parseInt((String)view.getTag());
                incele(iv_22,kart);
            }
        });
    }

    //HESAPLAMADAN ONCE SECİLMİŞ 2 KARTIN BİLGİLERİ ilkKart VE ikinciKart a ATILIR
    private void incele(ImageView IV, int kart){
        if(kartDizisi[kart] == 101){
            Picasso.get().load(link101).into(IV);
        } else if(kartDizisi[kart] == 102){
            Picasso.get().load(link102).into(IV);
        } else if(kartDizisi[kart] == 201){
            Picasso.get().load(link201).into(IV);
        } else if(kartDizisi[kart] == 202){
            Picasso.get().load(link202).into(IV);
        }

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

            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);

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
            else if(ilkTiklanan == 2){iv_21.setVisibility(View.INVISIBLE);}
            else if(ilkTiklanan == 3){iv_22.setVisibility(View.INVISIBLE);}

            if(ikinciTiklanan == 0){iv_11.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 1){iv_12.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 2){iv_21.setVisibility(View.INVISIBLE);}
            else if(ikinciTiklanan == 3){iv_22.setVisibility(View.INVISIBLE);}

            if(sira == 1){
                if(ak!=1){music2 = MediaPlayer.create(TO2x2.this, R.raw.victorytheme);music2.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        music2.stop();
                    }
                }, 1000);}//millisec.
                getdata(ilkKart);
            } else if(sira == 2){
                music2 = MediaPlayer.create(TO2x2.this, R.raw.victorytheme);
                if(ak!=1){music2 = MediaPlayer.create(TO2x2.this, R.raw.victorytheme);music2.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        music2.stop();
                    }
                }, 1000);}//millisec.
                getdata(ilkKart);
            }
        } else {
            iv_11.setImageResource(R.drawable.back);
            iv_12.setImageResource(R.drawable.back);
            iv_21.setImageResource(R.drawable.back);
            iv_22.setImageResource(R.drawable.back);

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

        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);

        oyunSonuKontrolu();
    }

    //OYUN SONU KONTROL EDER
    private void oyunSonuKontrolu(){
        if(iv_11.getVisibility()==View.INVISIBLE &&
                iv_12.getVisibility()==View.INVISIBLE &&
                iv_21.getVisibility()==View.INVISIBLE &&
                iv_22.getVisibility()==View.INVISIBLE){
            bitti=1;
            if(ak!=1){music4 = MediaPlayer.create(TO2x2.this, R.raw.congratulations);music4.start();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    music4.stop();
                }
            }, 6000);
            }//millisec.
            loop3.removeAll(loop3);
            loop2.removeAll(loop2);
            loop.removeAll(loop);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TO2x2.this);
            alertDialogBuilder
                    .setMessage("Oyun Sona Erdi\n")
                    .setCancelable(false)
                    .setPositiveButton("new", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(),TO2x2.class);
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
            if(ak!=1)music.stop();
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
        int rand_int2 = rand.nextInt(loop.size());
        int houseNumber = loop.get(rand_int2);
        loop.remove(rand_int2);

        if     (houseNumber == 0 ) loop3.set(k,"gryffindor");
        else if(houseNumber == 1 ) loop3.set(k,"slytherin");
        else if(houseNumber == 2 ) loop3.set(k,"ravenclaw");
        else if(houseNumber == 3 ) loop3.set(k,"hufflepuff");

        DatabaseReference getImage = databaseReference.child(loop3.get(k).toString()+"Images").child(""+kartId);

        getImage.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot) {

                if(k == 0){
                    link101 = dataSnapshot.getValue(String.class);
                    link201 = dataSnapshot.getValue(String.class);
                    kart1=kartId;
                }
                if(k == 1){
                    link102 = dataSnapshot.getValue(String.class);
                    link202 = dataSnapshot.getValue(String.class);
                    kart2=kartId;
                }
            }
            @Override
            public void onCancelled( @NonNull DatabaseError databaseError) {
                Toast.makeText(TO2x2.this, "Resim Yüklerken Sorun Oluştu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //DOGRU KART ESLESMESİ DURUMUNDA CAGRILIP FİREBASEDEKİ KART PUANLARINI ALARAK HESAP YAPAR, TEXTLERİ GUNCELLER
    private void getdata(int kart) {
        if     (kart==101 || kart ==201){k=0;kartt=kart1;}
        else if(kart==102 || kart ==202){k=1;kartt=kart2;}

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
                Toast.makeText(TO2x2.this,""+value,Toast.LENGTH_SHORT).show();

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
                Toast.makeText(TO2x2.this, "Kart bilgisi alınamadı", Toast.LENGTH_SHORT).show();
                value = -1;
            }

        });
    }

    //YANLIS KART ESLESMESİ DURUMUNDA YAPILACAK HESAP İÇİN CAGRILIP FİREBASEDEN PUAN CEKER
    private int getdata2(int kart) {
        if     (kart==101 || kart ==201){k=0;kartt=kart1;}
        else if(kart==102 || kart ==202){k=1;kartt=kart2;}

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
                Toast.makeText(TO2x2.this,""+value,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TO2x2.this, "Kart bilgisi alınamadı", Toast.LENGTH_SHORT).show();
                value = -1;
            }

        });
        return value;
    }
}