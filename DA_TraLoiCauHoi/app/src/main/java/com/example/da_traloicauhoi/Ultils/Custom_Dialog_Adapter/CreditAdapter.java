package com.example.da_traloicauhoi.Ultils.Custom_Dialog_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_traloicauhoi.Object_Model.Credit;
import com.example.da_traloicauhoi.R;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.API_AsyncTask;
import com.example.da_traloicauhoi.Ultils.API_Asyntask.NetworkUtils;
import com.example.da_traloicauhoi.Ultils.Algorithrm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Credit> listCredit;
    private int id_nguoi_choi;
    public CreditAdapter(Context context, ArrayList<Credit> listCredit, int id_nguoi_choi) {
        this.context = context;
        this.listCredit = listCredit;
        this.id_nguoi_choi = id_nguoi_choi;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_credit,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mToTalCredit.setText(Algorithrm.ConvertToFormatMoney(listCredit.get(position).getCredit()+""));
       holder.mTotalUSD.setText(Algorithrm.ConvertToFormatMoney(listCredit.get(position).getSo_tien() +""));
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
            mTotalUSD = itemView.findViewById(R.id.txtTotalUSD);

            lnCredit.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            Credit objCredit = listCredit.get(getAdapterPosition());
            Map<String, String> paramet = new HashMap<>();
            paramet.put("id_nguoi_choi",id_nguoi_choi+"");
            paramet.put("credit_id",objCredit.getId()+"");
            paramet.put("credit",objCredit.getCredit()+"");
            paramet.put("so_tien",objCredit.getSo_tien()+"");

            new API_AsyncTask(context, NetworkUtils.POST,paramet,"Mua credit","Wating..."){
                @Override
                public void XuLy(JSONObject jsonObject, Context context) throws JSONException {
                    if (jsonObject.getBoolean("success") == true) {
                        new CustomDialog(context, "Thông báo", "Đã mua.", "Ok", CustomDialog.SIZE_M).show();

                    } else {
                        new CustomDialog(context, "Thông báo", "Mua thất bại", "Ok", CustomDialog.SIZE_M).show();
                    }
                }

            }.execute("credit/update-lich-su-mua-credit");
        }
    }


}
