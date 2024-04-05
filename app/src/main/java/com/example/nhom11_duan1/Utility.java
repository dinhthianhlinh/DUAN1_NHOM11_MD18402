package com.example.nhom11_duan1;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import java.text.SimpleDateFormat;

public class Utility {
    static void showToast(Context context,String messenger){
        Toast.makeText(context, messenger, Toast.LENGTH_SHORT).show();
    }
    public static CollectionReference getCollectionReference(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("San Pham");
    }
    public static String documentID(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("San Pham").getId();
    }

    public static String timestampToString(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy").format(timestamp.toDate());
    }
    public static String CurrentUserID(){
        return FirebaseAuth.getInstance().getUid();
    }
    public static DocumentReference CurrentUserDetail(){
        return FirebaseFirestore.getInstance().collection("User").document(CurrentUserID());
    }
    public static CollectionReference AllUser(){
        return FirebaseFirestore.getInstance().collection("users");
    }
    public static CollectionReference HoaDon(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("Hoa Don")
                .document(firebaseUser.getUid()).collection("HoaDon Cua Toi");
    }
    public static CollectionReference HoaDon1(){
        return FirebaseFirestore.getInstance().collection("Hoa Don 1");
    }
    public static void setAnh(Context context, Uri imgUri, ImageView imageView){
        Glide.with(context).load(imgUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
    public static StorageReference layAnh(String docID){
        DocumentReference documentReference = Utility.getCollectionReference().document(docID);
        return FirebaseStorage.getInstance().getReference().child(String.valueOf(Utility.getCollectionReference()));
    }
    public static StorageReference layAnhKhac(String anhkhac){
        return FirebaseStorage.getInstance().getReference().child("imgAnhSanPham").child(anhkhac);
    }
}
