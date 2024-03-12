package com.example.nhom11_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
             FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
             if(firebaseUser == null){
                 startActivity(new Intent(MainActivity.this,LoginActivity.class));
             }else{
                 startActivity(new Intent(MainActivity.this,HomeActivity.class));
             }
             finish();
         }
     },1000);
    }
}