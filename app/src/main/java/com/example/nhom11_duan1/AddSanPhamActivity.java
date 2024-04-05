package com.example.nhom11_duan1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nhom11_duan1.DTO.SanPham;
import com.example.nhom11_duan1.fragment.fragment_QuanLySanPham;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class AddSanPhamActivity extends AppCompatActivity {
    EditText edtTenSP, edtGiaSP, edtMoTaSP;
    Spinner spinnerHangSP;
    ImageButton btnAddSP,btnBackSP;
    TextView tvAddSP,tvDelete;
    Button btnAnhSP;
    ImageView imgAnhSP;
    boolean isEditMode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_san_pham);
        anhxa();
        String tenSP = getIntent().getStringExtra("TenSP");
        int giaSP = getIntent().getIntExtra("giaSP", 0); // Sử dụng getIntExtra() để lấy số nguyên
        String motaSP = getIntent().getStringExtra("moTaSP");
        String hangSP = getIntent().getStringExtra("hangSP");
        String docID = getIntent().getStringExtra("docID");





        edtTenSP.setText(tenSP);
        edtGiaSP.setText(String.valueOf(giaSP)); // Chuyển số nguyên thành chuỗi trước khi đặt giá trị
        edtMoTaSP.setText(motaSP);
        btnBackSP.setOnClickListener(v -> {
            onBackPressed();
        });

        if(docID!= null && !docID.isEmpty()){
//123
            isEditMode = true;
        }
        if(isEditMode){
            tvAddSP.setText("Sửa Sản Phẩm");
            tvDelete.setVisibility(View.VISIBLE);
        }
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference;
                documentReference = Utility.getCollectionReference().document(docID);
                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utility.showToast(AddSanPhamActivity.this, "Xóa Sản Phẩm Thành Công");
                            finish();
                        } else {
                            Utility.showToast(AddSanPhamActivity.this, "Xóa Sản Phẩm Thất Bại");
                        }
                    }
                });
            }
        });

        // Tạo danh sách hãng sản phẩm
        List<String> hangSPList = new ArrayList<>();
        hangSPList.add("iPhone");
        hangSPList.add("SamSung");
        hangSPList.add("Oppo");
        hangSPList.add("Xiaomi");
        hangSPList.add("RealMe");
        hangSPList.add("Asus");

// Tạo ArrayAdapter và gán cho Spinner
        ArrayAdapter<String> hangSPAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hangSPList);
        hangSPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHangSP.setAdapter(hangSPAdapter);

// Tìm vị trí của hangSP trong danh sách và đặt vị trí đó cho Spinner
        int position = hangSPList.indexOf(hangSP);
        if (position != -1) {
            spinnerHangSP.setSelection(position);
        }


        btnAddSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TenSP = edtTenSP.getText().toString();
                int giaSP = Integer.valueOf(edtGiaSP.getText().toString());
                String hangSP = spinnerHangSP.getSelectedItem().toString(); // Lấy giá trị từ Spinner
                String MoTaSP = edtMoTaSP.getText().toString();

                if (TenSP.equals("") || MoTaSP.equals("")) {
                    if (TenSP.equals("")) {
                        edtTenSP.setError("Hãy Điền Tên Sản Phẩm");
                    } else {
                        edtTenSP.setError(null);
                    }
                    if (giaSP == 0) {
                        edtGiaSP.setError("Hãy Điền Giá Sản Phẩm");
                    } else {
                        edtGiaSP.setError(null);
                    }
                    if (MoTaSP.equals("")) {
                        edtMoTaSP.setError("Hãy Điền Mô Tả Sản Phẩm");
                    } else {
                        edtMoTaSP.setError(null);
                    }
                }

                SanPham sanPham = new SanPham(TenSP,giaSP , hangSP, MoTaSP);
                sanPham.settenSP(TenSP);
                sanPham.setgiaSP(giaSP);
                sanPham.sethangSP(hangSP);
                sanPham.setmoTaSP(MoTaSP);
                sanPham.setTimestamp(Timestamp.now());

                DocumentReference documentReference;
                if(isEditMode){
                    documentReference = Utility.getCollectionReference().document(docID);

                }else{
                    documentReference = Utility.getCollectionReference().document();

                }
                documentReference.set(sanPham).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Utility.showToast(AddSanPhamActivity.this, "Thêm Sản Phẩm Thành Công");
                            finish();
                        } else {
                            Utility.showToast(AddSanPhamActivity.this, "Thêm Sản Phẩm Thất Bại");
                        }
                    }
                });

            }
        });
    }
    void changeInprogress(boolean inProgress){
        if(inProgress){
            imgAnhSP.setVisibility(View.VISIBLE);
            btnAnhSP.setVisibility(View.GONE);
        }else{
            imgAnhSP.setVisibility(View.GONE);
            btnAnhSP.setVisibility(View.VISIBLE);
        }
    }
    void anhxa(){
        edtTenSP = findViewById(R.id.edtTenSP);
        edtGiaSP = findViewById(R.id.edtGiaSP);
        edtMoTaSP = findViewById(R.id.edtMoTaSP);
        spinnerHangSP = findViewById(R.id.spinnerHangSP); // Thay thế EditText bằng Spinner
        btnAddSP = findViewById(R.id.btnimgAddSP);
        tvAddSP = findViewById(R.id.tvAddSP);
        tvDelete = findViewById(R.id.tvDelete);
        btnBackSP = findViewById(R.id.btnBack);
    }
}
//123