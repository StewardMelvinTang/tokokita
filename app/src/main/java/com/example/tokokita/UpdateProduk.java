package com.example.tokokita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProduk extends AppCompatActivity {

    private EditText tvName;
    private EditText tvHarga;
    private EditText tvdeskripsi;
    private Button btnSimpan, btnDelete;
    private Helper helper;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_produk);

        Toolbar tbMW = findViewById(R.id.tbUpdate);
        setSupportActionBar(tbMW);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Produk");

        int id = getIntent().getIntExtra("DATA_ID", 0);
        int price = getIntent().getIntExtra("DATA_HARGA", 0);
        String nama = getIntent().getStringExtra("DATA_NAMA");
        String deskripsi = getIntent().getStringExtra("DATA_DESC");

        tvName = findViewById(R.id.tv_nama);
        tvHarga = findViewById(R.id.tv_harga);
        tvdeskripsi = findViewById(R.id.tv_deskripsi);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnDelete = findViewById(R.id.btn_delete);

        tvName.setText(nama);
        tvdeskripsi.setText(deskripsi);
        tvHarga.setText(String.valueOf(price));


        helper = new Helper(this);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogDelete(id,"Delete Product?", "Anda yakin ingin menghapus produk " + nama + "? Tindakan anda tidak dapat di undo.");

            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = tvName.getText().toString();
                String deskripsi = tvdeskripsi.getText().toString();
                int harga = Integer.parseInt(tvHarga.getText().toString());
                helper.updateProduk(id, nama, deskripsi, harga);
                Toast.makeText(UpdateProduk.this, "Produk telah berhasil diupdate", Toast.LENGTH_SHORT).show();
                MainActivity.getInstance().setData();
                finish();
            }
        });
    }

    public void openDialogDelete(int id, String title, String deskripsi) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UpdateProduk.this);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(deskripsi);
        //set negative button akan selalu mengclose dialog

        dialogBuilder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //close dialog
            }
        });

        dialogBuilder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                helper.deleteProduk(id);
                finish();
            }
        });
        dialogBuilder.show();
    }
    public void openDialogTutup(String title, String deskripsi) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UpdateProduk.this);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(deskripsi);
        //set negative button akan selalu mengclose dialog

        dialogBuilder.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //close dialog
            }
        });

        dialogBuilder.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialogBuilder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            openDialogTutup("Are you sure?", "Apapun yang diubah pada produk ini tidak akan tersimpan");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

        //End of method
}