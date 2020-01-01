package com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_traloicauhoi.Object_Model.NguoiChoi;
import com.example.da_traloicauhoi.R;

import java.util.ArrayList;

public class BXHAdapter extends RecyclerView.Adapter<BXHAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NguoiChoi> mListNguoiChoi;

    public BXHAdapter(Context context, ArrayList<NguoiChoi> mListNguoiChoi) {
        this.context = context;
        this.mListNguoiChoi = mListNguoiChoi;
    }

    @NonNull
    @Override
    public BXHAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.item_bang_xep_hang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BXHAdapter.ViewHolder holder, int position) {
        holder.txtDiemCaoNhat.setText(mListNguoiChoi.get(position).getDiem_cao_nhat() + "");
        holder.txtSTT.setText(position + 1 + "");
        holder.txtTen.setText(mListNguoiChoi.get(position).getTen_dang_nhap());
    }

    @Override
    public int getItemCount() {
        return mListNguoiChoi.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgNguoiChoi;
        private TextView txtSTT, txtTen, txtDiemCaoNhat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNguoiChoi = itemView.findViewById(R.id.imgUser_BXH);
            txtSTT = itemView.findViewById(R.id.txtSTT_BXH);
            txtTen = itemView.findViewById(R.id.txtTen_BXH);
            txtDiemCaoNhat = itemView.findViewById(R.id.txtDiemCaoNhat_BXH);

        }
    }
}
