package com.example.aqua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceMainDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main_dashboard);
    }

    public void searchButton(View view)
    {
        startActivity(new Intent(this, ViewAllServices.class));
    }
}