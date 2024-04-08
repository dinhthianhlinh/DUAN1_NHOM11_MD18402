package com.example.nhom11_duan1.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nhom11_duan1.R;
import com.example.nhom11_duan1.Utility;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class fragment_ThongKe extends Fragment {
    private Button startDateButton;
    private Button endDateButton;
    private TextView textView;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thongke, container, false);

        ///code
        startDateButton = view.findViewById(R.id.start_date_button);
        endDateButton = view.findViewById(R.id.end_date_button);
        textView = view.findViewById(R.id.text_view);

        db = FirebaseFirestore.getInstance();

        startDateButton.setOnClickListener(v -> showDatePicker(true));
        endDateButton.setOnClickListener(v -> showDatePicker(false));

        return view;
    }

    private void showDatePicker(boolean isStartDate) {
        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Tạo DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year1, month1, dayOfMonth1) -> {
            calendar.set(year1, month1, dayOfMonth1);
            Date selectedDate = calendar.getTime();
            updateButtonText(selectedDate, isStartDate ? startDateButton : endDateButton);
            if (startDateButton.getText() != null && endDateButton.getText() != null) {
                queryFirestoreByDate();
            }
        }, year, month, dayOfMonth);

        // Hiển thị DatePickerDialog
        datePickerDialog.show();
    }

    private void updateButtonText(Date selectedDate, Button button) {
        button.setText(android.text.format.DateFormat.format("dd/MM/yyyy", selectedDate));
    }

    private void queryFirestoreByDate() {
        // Lấy timestamp từ các button
        String startDateString = startDateButton.getText().toString();
        String endDateString = endDateButton.getText().toString();

        // Chuyển đổi các chuỗi ngày tháng thành Timestamp
        Timestamp startDate = convertToTimestamp(startDateString);
        Timestamp endDate = convertToTimestamp(endDateString);


        // Thực hiện truy vấn Firestore
        Utility.HoaDon1()
                .whereGreaterThanOrEqualTo("timestamp", startDate)
                .whereLessThanOrEqualTo("timestamp", endDate)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    int totalTongTien = 0; // Di chuyển biến ra ngoài vòng lặp để tính tổng chính xác
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.exists()) {
                            long soLuongLong = documentSnapshot.getLong("tongTienSP");
                            int soLuong = (int) soLuongLong;
                            totalTongTien += soLuong;
                        } else {
                            // Tài liệu không tồn tại
                        }
                    }
                    // Sau khi tính tổng, hiển thị kết quả trên textView
                    textView.setText(String.valueOf(totalTongTien));
                })
                .addOnFailureListener(e -> {
                    // Xử lý lỗi khi truy vấn dữ liệu
                });
    }

    // Phương thức để chuyển đổi chuỗi ngày tháng thành Timestamp
    private Timestamp convertToTimestamp(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = dateFormat.parse(dateString);
            return new Timestamp(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}