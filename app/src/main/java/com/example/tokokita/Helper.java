package com.example.tokokita;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import java.util.ArrayList;
import java.util.jar.Attributes;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class Helper {

    private Context context;
    private Realm realm;
    private RealmResults<DataModel> realmResults;

    //logt
    private static final String TAG = "RealmHelper";

    public Helper(Context context) {
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void inputDataAwal() {
        DataModel data = new DataModel();
        data.setId(1);
        data.setName("Melvin");
        data.setPrice(10000);
        data.setDescription("Sebuah produk yang sangat berguna bagi kalangan masyarakat");
        data.

        realm.beginTransaction();
        realm.copyToRealm(data);
        realm.commitTransaction();

    }

    public ArrayList<DataModel> tampilDataProduk() {
        ArrayList<DataModel> datar = new ArrayList<>();
        realmResults = realm.where(DataModel.class).findAll();
        realmResults.sort("id", Sort.ASCENDING);

        if (realmResults.size() > 0) {

            for (int i = 0; i < realmResults.size(); i++) {
                DataModel data = new DataModel();
                data.setId(realmResults.get(i).getId());
                data.setName(realmResults.get(i).getName());
                data.setPrice(realmResults.get(i).getPrice());
                data.setDescription(realmResults.get(i).getDescription());
                datar.add(data);
            }
        }
        return datar;
    }

    public void tambahProduk(String nama, String deskripsi, int Harga) {
        DataModel dataproduk = new DataModel();
        dataproduk.setId((int) (System.currentTimeMillis() / 1000));
        dataproduk.setName(nama);
        dataproduk.setPrice(Harga);
        dataproduk.setDescription(deskripsi);

        realm.beginTransaction();
        realm.copyToRealm(dataproduk);
        realm.commitTransaction();

        Toast.makeText(context, "Produk telah berhasil ditambahkan", Toast.LENGTH_SHORT).show();
    }

    public void updateProduk(int id, String nama, String deskripsi, int Harga) {
        realm.beginTransaction();
        DataModel dataProduk = realm.where(DataModel.class).equalTo("id", id).findFirst();
        dataProduk.setName(nama);
        dataProduk.setDescription(deskripsi);
        dataProduk.setPrice(Harga);
        realm.copyToRealm(dataProduk);
        realm.commitTransaction();

    }

    public void deleteProduk(int id) {
        realm.beginTransaction();
        RealmResults<DataModel> siswa = realm.where(DataModel.class).equalTo("id", id).findAll();
        siswa.deleteAllFromRealm();
        realm.commitTransaction();

        openDialog("Product Deleted", "Produk telah berhasil di hapus");
    }


    public void openDialog(String title, String description) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(description);
        //set negative button akan selalu mengclose dialog
        dialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //clear all tb
            }
        });
        dialogBuilder.show();
    }
}
