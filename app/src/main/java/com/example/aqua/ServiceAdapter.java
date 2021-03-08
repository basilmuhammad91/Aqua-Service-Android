package com.example.aqua;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ServiceAdapter extends ArrayAdapter<Service> {

    Context context;
    List<Service> arrayListService;

    public ServiceAdapter(@NonNull Context context, List<Service> arrayListService) {
        super(context, R.layout.custom_list_item, arrayListService);

        this.context = context;
        this.arrayListService = arrayListService;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, null, true);

        TextView tvFullName =view.findViewById(R.id.txt_full_name);
        TextView tvServiceName = view.findViewById(R.id.txt_service_name);
        TextView tvContact = view.findViewById(R.id.txt_contact);
        TextView tvPrice = view.findViewById(R.id.txt_price);
        Button book_btn = view.findViewById(R.id.btn_book);

        tvFullName.setText(arrayListService.get(position).getFull_name());
        tvServiceName.setText("Service:  "+arrayListService.get(position).getService_name());
        tvContact.setText("Contact No: "+arrayListService.get(position).getPhone());
        tvPrice.setText("Rs: "+arrayListService.get(position).getAddress()+" per hour working charges");

        String serviceId = arrayListService.get(position).getServiceId();
        String serviceName = arrayListService.get(position).getService_name();
        String contactNo = arrayListService.get(position).getPhone();
        String price = arrayListService.get(position).getAddress();

        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewServiceSingle.class);
                intent.putExtra("serviceId", serviceId);
                intent.putExtra("serviceName", serviceName);
                intent.putExtra("contactNo", contactNo);
                intent.putExtra("price", price);
                v.getContext().startActivity(intent);
            }
        });


        return view;
    }
}
