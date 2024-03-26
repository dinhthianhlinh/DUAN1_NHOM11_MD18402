package com.example.nhom11_duan1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom11_duan1.Adapter.SanPhamAdapter;
import com.example.nhom11_duan1.AddSanPhamActivity;
import com.example.nhom11_duan1.R;
import com.example.nhom11_duan1.DTO.SanPham;
import com.example.nhom11_duan1.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class fragment_QuanLySanPham extends Fragment {
    FloatingActionButton btnAdd;
    RecyclerView recyclerView;
    SanPhamAdapter sanPhamAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quanlysanpham,container,false);
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddSanPhamActivity.class);
                getActivity().startActivity(intent);
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddSanPhamActivity.class);
                getActivity().startActivity(intent);
            }
        });
        Query query = Utility.getCollectionReference().orderBy("timestamp",Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<SanPham> options = new FirestoreRecyclerOptions.Builder<SanPham>()
                .setQuery(query,SanPham.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sanPhamAdapter = new SanPhamAdapter(options,getContext());
        recyclerView.setAdapter(sanPhamAdapter);

        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        sanPhamAdapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        sanPhamAdapter.stopListening();
    }
    @Override
    public void onResume(){
        super.onResume();
        sanPhamAdapter.notifyDataSetChanged();
    }
}
//123
