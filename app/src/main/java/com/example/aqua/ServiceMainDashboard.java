package com.example.aqua;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;


public class ServiceMainDashboard extends AppCompatActivity {

    private static final String TAG = "ServiceMainDashboard";
    public static String myIp = "192.168.10.4";
    String url = "http://"+ServiceMainDashboard.myIp+"/aqua/public/api/territories";
    String serviceUrl = "http://"+ServiceMainDashboard.myIp+"/aqua/public/api/service";
    String searchUrl = "http://"+ServiceMainDashboard.myIp+"/aqua/public/api/servicesearch";
    private ArrayList<Territory> territoryArrayList;
    private ArrayList<ServiceSingle> serviceSingleArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> service_names = new ArrayList<String>();
    private Spinner areaSpinner, serviceSpinner;
    TextView territoryId, serviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main_dashboard);

        areaSpinner = findViewById(R.id.areaSpinner);
        serviceSpinner = findViewById(R.id.serviceSpinner);

        territoryId = findViewById(R.id.territory_id);
        serviceId = findViewById(R.id.service_id);

        retrieveArea();
        retrieveService();
    }

    private void retrieveArea() {
        StringRequest fetchAreaRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    territoryArrayList = new ArrayList<>();
                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        Territory territory = new Territory();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        territory.setName(jsonObject.getString("Name"));

                        territory.setTerritoryId(jsonObject.getString("TerritoryId"));
                        territory.setCityId("CityId");
                        territoryArrayList.add(territory);
                        areaSpinner.setSelection(i);

                    }

                    for (int i = 0; i < territoryArrayList.size(); i++){
                        names.add(territoryArrayList.get(i).getName().toString());

                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ServiceMainDashboard.this, simple_spinner_item, names);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    areaSpinner.setAdapter(spinnerArrayAdapter);
                    areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String tutorialsName = parent.getItemAtPosition(position).toString();
                            Log.i(TAG, ""+territoryArrayList.get(position).getTerritoryId());
                            territoryId.setText(String.valueOf(territoryArrayList.get(position).getTerritoryId()));
//                            Toast.makeText(ServiceMainDashboard.this, ""+territoryId.getText().toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView <?> parent) {
                        }
                    });

//                Toast.makeText(ServiceMainDashboard.this, ""+areaId, Toast.LENGTH_SHORT).show                       ();


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ServiceMainDashboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(fetchAreaRequest);

        Log.i(TAG, ""+territoryId);

    }

    private void retrieveService()
    {
        StringRequest fetchServiceRequest = new StringRequest(Request.Method.GET, serviceUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    serviceSingleArrayList = new ArrayList<>();
                    JSONArray jsonArray = obj.getJSONArray("response");

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        ServiceSingle serviceSingle = new ServiceSingle();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        serviceSingle.setServiceName(jsonObject.getString("Name"));

                        serviceSingle.setServiceId(jsonObject.getString("ServiceId"));
                        serviceSingleArrayList.add(serviceSingle);
                        serviceSpinner.setSelection(i);

                    }

                    for (int i = 0; i < serviceSingleArrayList.size(); i++){
                        service_names.add(serviceSingleArrayList.get(i).getServiceName().toString());

                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(ServiceMainDashboard.this, simple_spinner_item, service_names);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    serviceSpinner.setAdapter(spinnerArrayAdapter);
                    serviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String tutorialsName = parent.getItemAtPosition(position).toString();
                            Log.i(TAG, ""+serviceSingleArrayList.get(position).getServiceId());
                            serviceId.setText(String.valueOf(serviceSingleArrayList.get(position).getServiceId()));
//                            Toast.makeText(ServiceMainDashboard.this, ""+serviceId.getText().toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView <?> parent) {
                        }
                    });

//                Toast.makeText(ServiceMainDashboard.this, ""+areaId, Toast.LENGTH_SHORT).show                       ();


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ServiceMainDashboard.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(fetchServiceRequest);

        Log.i(TAG, ""+serviceId);

    }

    public void searchButton(View view)
    {
        String terrId = territoryId.getText().toString();
        String serId = serviceId.getText().toString();

        Intent intent = new Intent(this, ViewAllServices.class);
        intent.putExtra("TerritoryId", terrId);
        intent.putExtra("ServiceId", serId);
        startActivity(intent);

    }



}