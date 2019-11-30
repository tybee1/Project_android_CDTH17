package com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_traloicauhoi.Object_Model.Credit;
import com.example.da_traloicauhoi.R;

import java.util.ArrayList;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolder> {

    private Context context;

    public CreditAdapter(Context context, ArrayList<Credit> listCredit) {
        this.context = context;
        this.listCredit = listCredit;
    }

    private ArrayList<Credit> listCredit;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_credit,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mToTalCredit.setText(listCredit.get(position).getCredit() + "");
        holder.mTotalUSD.setText(listCredit.get(position).getSo_tien() +"");
    }

    @Override
    public int getItemCount() {
        return listCredit.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private LinearLayout lnCredit;
        private TextView mToTalCredit, mTotalUSD;
        public ViewHolder(@NonNull View itemView)   {
            super(itemView);

            lnCredit = itemView.findViewById(R.id.lnCredit_item);
            mToTalCredit = itemView.findViewById(R.id.txtTotalCredit);
            mTotalUSD = itemView.findViewWithTag(R.id.txtTotalUSD);

            lnCredit.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

        }
    }


}
