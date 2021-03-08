package com.example.aqua;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewAllServices extends AppCompatActivity {

    ListView listView;
    ServiceAdapter serviceAdapter;
    public static ArrayList<Service> serviceArrayList = new ArrayList<>();
    String url = "http://192.168.10.5/aqua/public/api/servicelists";
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_services);

        listView = findViewById(R.id.myListView);
        serviceAdapter = new ServiceAdapter(this, serviceArrayList);
        listView.setAdapter(serviceAdapter);
        retrieveData();
    }

    public void retrieveData()
    {
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        serviceArrayList.clear();
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            JSONArray jsonArray1 = jsonObject1.getJSONArray("response");

                            for (int i = 0; i < jsonArray1.length();i++)
                            {

                                JSONObject object = jsonArray1.getJSONObject(i);
                                String ServiceListsId = object.getString("ServiceListsId");
                                String UserId = object.getString("UserId");
                                String ServiceId = object.getString("ServiceId");
                                String Address = object.getString("Price"); //Address is same as price
                                String full_name = object.getString("full_name");
                                String service_name = object.getString("service_name");
                                String phone = object.getString("phone");
                                String territory_id = object.getString("territory_id");
                                String territory_name = object.getString("territory_name");

                                service = new Service(ServiceListsId, UserId, ServiceId, Address, full_name, service_name, phone, territory_id, territory_name);
                                serviceArrayList.add(service);
                                serviceAdapter.notifyDataSetChanged();

                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewAllServices.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}