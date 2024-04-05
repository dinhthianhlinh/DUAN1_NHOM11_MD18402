package com.example.nhom11_duan1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom11_duan1.DTO.User;
import com.example.nhom11_duan1.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class UserApdater extends FirestoreRecyclerAdapter<User,UserApdater.UserViewHolder> {
    Context context;
    public UserApdater(@NonNull FirestoreRecyclerOptions<User> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
        holder.tvUser.setText(model.getTen());
        holder.tvEmail.setText(model.getPhone());
        holder.tvPass.setText(model.getAdress());

    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUser, tvEmail, tvPass;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvTenNguoiDung);
            tvEmail = itemView.findViewById(R.id.tvEmailNguoiDung);
            tvPass = itemView.findViewById(R.id.tvPassNguoiDung);
        }
    }
}
