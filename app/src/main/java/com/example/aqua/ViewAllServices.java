package com.example.aqua;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class ViewAllServices extends AppCompatActivity {

    ListView listView;
    ServiceAdapter serviceAdapter;
    public static ArrayList<Service> serviceArrayList = new ArrayList<>();
    String url = "http://"+ServiceMainDashboard.myIp+"/aqua/public/api/servicelists";
    String searchUrl = "http://"+ServiceMainDashboard.myIp+"/aqua/public/api/servicesearch";
    Service service;
    String territoryId;
    String serviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_services);

        listView = findViewById(R.id.myListView);
        serviceAdapter = new ServiceAdapter(this, serviceArrayList);
        listView.setAdapter(serviceAdapter);

        Intent intent = getIntent();
        territoryId = intent.getStringExtra("TerritoryId");
        serviceId = intent.getStringExtra("ServiceId");
        retrieveData();
    }

    public void retrieveData()
    {
        StringRequest request = new StringRequest(Request.Method.POST, searchUrl,
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
                            Toast.makeText(ViewAllServices.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewAllServices.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("territory_id", territoryId);
                map.put("service_id", serviceId);
                return map;
            }
        }
                ;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}