package com.example.nhom11_duan1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom11_duan1.Adapter.HoaDonAdapter;
import com.example.nhom11_duan1.Adapter.SanPhamAdapter;
import com.example.nhom11_duan1.DTO.HoaDon;
import com.example.nhom11_duan1.DTO.HoaDonChiTiet;
import com.example.nhom11_duan1.DTO.SanPham;
import com.example.nhom11_duan1.R;
import com.example.nhom11_duan1.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

public class fragment_QuanLyDonHang extends Fragment {
    RecyclerView recyclerView;
    HoaDonAdapter adapter;
    TextView tvChoXacNhan,tvDangGiao,tvDaGiao,tvDaHuy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__quan_ly_don_hang,container,false);
        recyclerView = view.findViewById(R.id.rcyView);

        tvChoXacNhan = view.findViewById(R.id.tvChoXacNhan);
        tvDangGiao = view.findViewById(R.id.tvDangGiao);
        tvDaGiao = view.findViewById(R.id.tvDaGiao);
        tvDaHuy = view.findViewById(R.id.tvDaHuy);

        tvChoXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lấy dữ liệu từ tài liệu người dùng// Thay "fieldName" bằng tên trường cần lấy dữ liệu
                // Hiển thị dữ liệu trong TextView
                Query query = Utility.HoaDonChiTiet1().whereEqualTo("trangThai", "Chờ Xác Nhận");
                FirestoreRecyclerOptions<HoaDonChiTiet> options = new FirestoreRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(query, HoaDonChiTiet.class).build();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new HoaDonAdapter(options, getContext());
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }

        });
        tvDangGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = Utility.HoaDonChiTiet1().whereEqualTo("trangThai", "Đang Giao");
                FirestoreRecyclerOptions<HoaDonChiTiet> options = new FirestoreRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(query, HoaDonChiTiet.class).build();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new HoaDonAdapter(options, getContext());
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }
        });
        tvDaGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = Utility.HoaDonChiTiet1().whereEqualTo("trangThai", "Đã Giao");
                FirestoreRecyclerOptions<HoaDonChiTiet> options = new FirestoreRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(query, HoaDonChiTiet.class).build();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new HoaDonAdapter(options, getContext());
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }
        });
        tvDaHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = Utility.HoaDonChiTiet1().whereEqualTo("trangThai", "Đã Hủy");
                FirestoreRecyclerOptions<HoaDonChiTiet> options = new FirestoreRecyclerOptions.Builder<HoaDonChiTiet>()
                        .setQuery(query, HoaDonChiTiet.class).build();
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new HoaDonAdapter(options, getContext());
                recyclerView.setAdapter(adapter);
                adapter.startListening();
            }
        });

        return view;
    }

}