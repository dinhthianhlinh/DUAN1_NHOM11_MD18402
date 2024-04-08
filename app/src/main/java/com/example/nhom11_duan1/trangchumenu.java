package com.example.nhom11_duan1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhom11_duan1.fragment.fragment_quanLyKhachHang;
import com.example.nhom11_duan1.fragment.fragment_QuanLyDanhSach;
import com.example.nhom11_duan1.fragment.fragment_QuanLyDonHang;
import com.example.nhom11_duan1.fragment.fragment_QuanLyDanhMuc;
import com.example.nhom11_duan1.fragment.fragment_ThongKe;
import com.example.nhom11_duan1.fragment.fragment_QuanLySanPham;
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
        Fragment defaultFragment = new fragment_QuanLySanPham();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.linearlayout, defaultFragment)
                .commit();

        // nhan navigation
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment =null;
                DrawerLayout drawerLayout = findViewById(R.id.DrawerLayout1); // Lấy tham chiếu của DrawerLayout

                if (item.getItemId() == R.id.quanLyKhachHang) {
                    fragment = new fragment_quanLyKhachHang();
                } else if (item.getItemId() == R.id.quanLySanPham) {
                    fragment = new fragment_QuanLySanPham();
                } else if (item.getItemId() == R.id.thongKe) {
                    fragment = new fragment_ThongKe();
                } else if (item.getItemId() == R.id.quanLyDonHang) {
                    fragment = new fragment_QuanLyDonHang();
                } else if (item.getItemId() == R.id.quanLyDanhMuc) {
                    fragment = new fragment_QuanLyDanhMuc();
                } else {
                    fragment = new fragment_quanLyKhachHang();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.linearlayout, fragment)
                        .commit();

                drawerLayout.closeDrawer(GravityCompat.START); // Đóng thanh bar sau khi chọn mục menu

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