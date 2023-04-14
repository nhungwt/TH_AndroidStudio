package com.example.th_4_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddNewTaskActivity extends AppCompatActivity {
    Spinner spn_colab, spn_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);
        spn_status = findViewById(R.id.spn_status);
        spn_colab = findViewById(R.id.spn_colab);

        String[] st = {"A", "B", "C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_status, st);
        spn_status.setAdapter(adapter);

        ArrayAdapter<String> adapterCo = new ArrayAdapter<>(this, R.layout.item_status, getResources().getStringArray(R.array.colab));
        spn_status.setAdapter(adapterCo);
    }
}