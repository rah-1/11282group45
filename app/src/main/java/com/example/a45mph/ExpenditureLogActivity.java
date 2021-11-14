package com.example.a45mph;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

public class ExpenditureLogActivity extends AppCompatActivity {
    private RecyclerView expendList;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_log);

        expendList = findViewById(R.id.expenditurelogrecyclerview);
        expendList.setLayoutManager(new LinearLayoutManager(this));
        expendList.setAdapter(new ExpenditureDataLogAdapter(this));
    }



}