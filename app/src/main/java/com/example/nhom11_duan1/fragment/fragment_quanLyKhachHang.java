package com.example.nhom11_duan1.fragment;

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
import com.example.nhom11_duan1.Adapter.UserApdater;
import com.example.nhom11_duan1.DTO.SanPham;
import com.example.nhom11_duan1.DTO.User;
import com.example.nhom11_duan1.R;
import com.example.nhom11_duan1.Utility;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class fragment_quanLyKhachHang extends Fragment {
    RecyclerView recyclerViewUser;
    UserApdater userApdater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quanlykhachhang, container, false);
        recyclerViewUser = (RecyclerView) view.findViewById(R.id.recycler_view_user);

        Query query = Utility.AllUser().orderBy("ten", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class).build();
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(getContext()));
        userApdater = new UserApdater(options, getContext());
        recyclerViewUser.setAdapter(userApdater);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        userApdater.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        userApdater.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        userApdater.notifyDataSetChanged();
    }
}
