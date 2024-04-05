package com.example.nhom11_duan1.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom11_duan1.AddSanPhamActivity;
import com.example.nhom11_duan1.DanhMucActivity;
import com.example.nhom11_duan1.R;

public class fragment_QuanLyDanhMuc extends Fragment {
    ImageView iPhone,Oppo,SamSung,Xiaomi,Asus,RealMe;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quanhlydanhmuc,container,false);
        iPhone = (ImageView) view.findViewById(R.id.imgApple);
        Oppo = (ImageView) view.findViewById(R.id.imgOppo);
        SamSung = (ImageView) view.findViewById(R.id.imgSamSung);
        Xiaomi = (ImageView) view.findViewById(R.id.imgXiaoMi);
        Asus = (ImageView) view.findViewById(R.id.imgAsus);
        RealMe = (ImageView) view.findViewById(R.id.imgRealMe);
        iPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhMucActivity.class);
                intent.putExtra("text", "Điện Thoại iPhone");
                getActivity().startActivity(intent);
            }
        });
        Asus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhMucActivity.class);
                intent.putExtra("text", "Điện Thoại Asus");
                getActivity().startActivity(intent);
            }
        });
        Oppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhMucActivity.class);
                intent.putExtra("text", "Điện Thoại Oppo");
                getActivity().startActivity(intent);
            }
        });
        Xiaomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhMucActivity.class);
                intent.putExtra("text", "Điện Thoại Xiaomi");
                getActivity().startActivity(intent);
            }
        });
        SamSung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhMucActivity.class);
                intent.putExtra("text", "Điện Thoại SamSung");
                getActivity().startActivity(intent);
            }
        });
        RealMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DanhMucActivity.class);
                intent.putExtra("text", "Điện Thoại RealMe");
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
