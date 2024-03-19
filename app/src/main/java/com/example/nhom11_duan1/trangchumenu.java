package com.example.nhom11_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom11_duan1.fragment.fragment_one;
import com.example.nhom11_duan1.fragment.fragment_five;
import com.example.nhom11_duan1.fragment.fragment_four;
import com.example.nhom11_duan1.fragment.fragment_one;
import com.example.nhom11_duan1.fragment.fragment_six;
import com.example.nhom11_duan1.fragment.fragment_thre;
import com.example.nhom11_duan1.fragment.fragment_thre;
import com.example.nhom11_duan1.fragment.fragment_two;
import com.google.android.material.navigation.NavigationView;

public class trangchumenu extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchumenu);
        //anh xa
        drawerLayout = findViewById(R.id.DrawerLayout1);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationview);

        /// setup toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        // nhan navigation
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment =null;
                if (item.getItemId()==R.id.one){
                    fragment = new fragment_one();
                }else if(item.getItemId()==R.id.two){
                    fragment = new fragment_two();
                }else if(item.getItemId()==R.id.thre){
                    fragment = new fragment_thre();
                }else if (item.getItemId()==R.id.four){
                    fragment = new fragment_four();
                }else if (item.getItemId()==R.id.five){
                    fragment = new fragment_five();
                }
                else if (item.getItemId()==R.id.six){
                    fragment = new fragment_six();
                }else {
                    fragment = new fragment_one();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearlayout,fragment)
                        .commit();


                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);

    }
}
//ccaaaaa
//caaaaaass