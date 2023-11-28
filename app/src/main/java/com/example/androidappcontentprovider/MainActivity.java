package com.example.androidappcontentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        Button btnAfficher = findViewById(R.id.btnAfficher);
        btnAfficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayEmployeeList();
            }
        });
    }

    private void displayEmployeeList() {
        // Use the content resolver to query the content provider of the first application
        ContentResolver contentResolver = getContentResolver();
        Uri employeesUri = Uri.parse("content://com.demo.user.provider/employees");
        Cursor cursor = contentResolver.query(employeesUri, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder result = new StringBuilder();
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String department = cursor.getString(3);

                result.append("ID: ").append(id).append("\n")
                        .append("Name: ").append(name).append("\n")
                        .append("Age: ").append(age).append("\n")
                        .append("Department: ").append(department).append("\n\n");
            } while (cursor.moveToNext());

            tvResult.setText(result.toString());
            cursor.close();
        } else {
            tvResult.setText("No employees found");
        }
    }
}