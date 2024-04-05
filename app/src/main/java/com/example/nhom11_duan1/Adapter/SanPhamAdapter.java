package com.example.nhom11_duan1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom11_duan1.AddSanPhamActivity;
import com.example.nhom11_duan1.R;
import com.example.nhom11_duan1.DTO.SanPham;
import com.example.nhom11_duan1.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SanPhamAdapter extends FirestoreRecyclerAdapter<SanPham, SanPhamAdapter.SanPhamHolder> {
    Context context;

    public SanPhamAdapter(@NonNull FirestoreRecyclerOptions<SanPham> options,Context context) {
        super(options);
        this.context = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull SanPhamHolder holder, int position, @NonNull SanPham sanPham) {

        holder.tvTenSP.setText(sanPham.tenSP);
        holder.tvGiaSP.setText(Integer.toString(sanPham.giaSP));
        holder.tvMoTaSP.setText(sanPham.moTaSP);
        holder.tvHangSP.setText(sanPham.hangSP);
        holder.tvTimeStamp.setText(Utility.timestampToString(sanPham.timestamp));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddSanPhamActivity.class);
                intent.putExtra("TenSP",sanPham.tenSP);
                intent.putExtra("giaSP",sanPham.giaSP);
                intent.putExtra("moTaSP",sanPham.moTaSP);
                intent.putExtra("hangSP",sanPham.hangSP);
                String docID = getSnapshots().getSnapshot(position).getId();
                intent.putExtra("docID",docID);
                context.startActivity(intent);
            }
        });
        Utility.layAnh(getSnapshots().getSnapshot(position).getId()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
                    Uri uri = task.getResult();
                    Utility.setAnh(context,uri,holder.imgSP);
                }
            }
        });
    }

    @NonNull
    @Override
    public SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new SanPhamHolder(view);
    }

    public class SanPhamHolder extends RecyclerView.ViewHolder{
        TextView tvTenSP,tvGiaSP,tvMoTaSP,tvHangSP,tvTimeStamp;
        ImageView imgSP;
        public SanPhamHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSP = itemView.findViewById(R.id.tvTenSP);
            tvGiaSP = itemView.findViewById(R.id.tvGiaSP);
            tvMoTaSP = itemView.findViewById(R.id.tvMoTaSP);
            tvHangSP = itemView.findViewById(R.id.tvHangSP);
            tvTimeStamp = itemView.findViewById(R.id.tvTimestamp);

        }
    }

}
//123