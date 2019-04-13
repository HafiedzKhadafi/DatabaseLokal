package com.example.dblokal.ui.main.holder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dblokal.R;
import com.example.dblokal.entity.DataSekolah;
import com.example.dblokal.ui.main.EditData;
import com.example.dblokal.ui.main.MainContract;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewHolder> {
    Context context;
    List<DataSekolah> list;
    MainContract.hapus hapus;

    public MainAdapter(Context context, List<DataSekolah> list, MainContract.hapus hapus) {
        this.context = context;
        this.list = list;
        this.hapus = hapus;
    }

    @NonNull
    @Override
    public MainAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final DataSekolah item = list.get(position);
        holder.tvNamaSekolah.setText(item.getNamasekolah());
        holder.tvAlamatSekolah.setText(item.getAlamatsekolah());
        holder.tvJumlahGuru.setText(item.getJumlahguru());
        holder.tvJumlahSiswa.setText(item.getJumlahsiswa());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditData.class);
                intent.putExtra("namasekolah", item.getNamasekolah());
                intent.putExtra("alamatsekolah", item.getAlamatsekolah());
                intent.putExtra("jumlahguru", item.getJumlahguru());
                intent.putExtra("jumlahsiswa", item.getJumlahsiswa());
                intent.putExtra("id", item.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                hapus.deleteData(item);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaSekolah, tvAlamatSekolah, tvJumlahGuru, tvJumlahSiswa;
        CardView cardView;

        public viewHolder(View itemView){
            super(itemView);
            tvNamaSekolah = itemView.findViewById(R.id.tv_item_namasekolah);
            tvAlamatSekolah = itemView.findViewById(R.id.tv_item_alamatsekolah);
            tvJumlahGuru = itemView.findViewById(R.id.tv_item_jumlahguru);
            tvJumlahSiswa = itemView.findViewById(R.id.tv_item_jumlahsiswa);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
