package com.example.tokokita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class TambahProduk extends AppCompatActivity {

    private EditText tvNama, tvHarga, tvDeskripsi;
    private Button btnTambah;
    private Helper helper;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);
        initView();

        Toolbar tbMW = findViewById(R.id.tbAdd);
        setSupportActionBar(tbMW);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Produk");

        helper = new Helper(this);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvNama.getText().toString().isEmpty() || tvHarga.getText().toString().isEmpty() || tvDeskripsi.getText().toString().isEmpty())
                {
                    openDialog("Can't add product", "Mohon lengkapi nama, harga dan deskripsi");
                } else {
                    String nama = tvNama.getText().toString();
                    int harga = Integer.parseInt(tvHarga.getText().toString());
                    String deskripsi = tvDeskripsi.getText().toString();
                    helper.tambahProduk(nama,deskripsi,harga);
                    MainActivity.getInstance().setData();
                    finish();

                }
            }
        });


    }

    public void openDialog(String title, String description) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(TambahProduk.this);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(description);
        //set negative button akan selalu mengclose dialog
        dialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //when clicked negative button

            }
        });
        dialogBuilder.show();
    }

    private void initView() {
        tvNama = findViewById(R.id.tv_nama);
        tvHarga = findViewById(R.id.tv_harga);
        btnTambah = findViewById(R.id.btn_tambah);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
    }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == android.R.id.home) {
                finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }