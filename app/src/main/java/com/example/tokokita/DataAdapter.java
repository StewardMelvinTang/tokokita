package com.example.tokokita;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<DataModel> dataProduk;




    public DataAdapter(Context context, ArrayList<DataModel> dataProduk){
        this.context = context;
        this.dataProduk = dataProduk;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView tvName, tvHarga;
        public MyViewHolder(View itemView) {
            super (itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_list_namaproduk);
            tvHarga = (TextView) itemView.findViewById(R.id.tv_list_harga);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recycler_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //set data
        int price = dataProduk.get(position).getPrice();
        holder.tvName.setText(dataProduk.get(position).getName());
        holder.tvHarga.setText("Rp." + String.valueOf(price));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context, UpdateProduk.class);
                pindah.putExtra("DATA_ID", dataProduk.get(position).getId());
                pindah.putExtra("DATA_NAMA", dataProduk.get(position).getName());
                pindah.putExtra("DATA_HARGA", dataProduk.get(position).getPrice());
                pindah.putExtra("DATA_DESC", dataProduk.get(position).getDescription());
                context.startActivity(pindah);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataProduk.size();
    }



}
