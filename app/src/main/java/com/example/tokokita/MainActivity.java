package com.example.tokokita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_sharetoko, btn_refresh;
    FloatingActionButton btn_add;
    RecyclerView rv_list;
    ArrayList<DataModel> dataProduk = new ArrayList<>();
    private Helper helper;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        //get ref
        helper = new Helper(MainActivity.this);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        btn_add = (FloatingActionButton) findViewById(R.id.BTN_Add);
        btn_sharetoko = (ImageButton) findViewById(R.id.BTN_ShareToko);
        btn_refresh = (ImageButton) findViewById(R.id.BTN_Refresh);
        btn_add = (FloatingActionButton) findViewById(R.id.BTN_Add);
        rv_list.setAdapter(new DataAdapter(MainActivity.this, dataProduk));
        rv_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        FloatingActionButton fab = findViewById(R.id.BTN_Add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahProduk.class));

            }
        });

        setData();

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setData();
            }
        });

        btn_sharetoko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Kunjungi toko saya! di https://tokokami.com/namatokosaya");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });



    }
   public void setData() {
        dataProduk = helper.tampilDataProduk();
        if (dataProduk.size() == 0) {
            rv_list.setVisibility(View.GONE);
        } else {
            rv_list.setVisibility(View.VISIBLE);
            rv_list.setAdapter(new DataAdapter(MainActivity.this, dataProduk));
        }
    }
    public static MainActivity getInstance() {
        return instance;
    }


    //End of func
}