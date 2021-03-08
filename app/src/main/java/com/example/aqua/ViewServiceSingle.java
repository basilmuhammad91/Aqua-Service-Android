package com.example.aqua;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewServiceSingle extends AppCompatActivity {

    TextView service_name, contact_no, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service_single);

        Intent intent = getIntent();
        String serviceId = intent.getStringExtra("serviceId");
        String serviceName = intent.getStringExtra("serviceName");
        String contactNo = intent.getStringExtra("contactNo");
        String price2 = intent.getStringExtra("price");

        service_name = findViewById(R.id.service_name);
        contact_no = findViewById(R.id.contact_no);
        price = findViewById(R.id.price);

        service_name.setText("Service:  "+serviceName);
        contact_no.setText("Contact No:  "+contactNo);
        price.setText("Rs: "+price2+" per hour working charges");



    }
}