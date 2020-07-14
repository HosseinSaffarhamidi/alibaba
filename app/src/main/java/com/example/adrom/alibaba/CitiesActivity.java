package com.example.adrom.alibaba;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.adrom.alibaba.Adapter.CitiesAdapter;

public class CitiesActivity extends AppCompatActivity {
    private static final String URL = "http://adromsh.ir/alibaba/getcity.php";
    ImageView imgBack;
    EditText edtSearch;
    RecyclerView recyclerView;
    List<String> cities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        setupViews();
        getAllCity();

    }

    private void getAllCity() {
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("Adrom Response1: ", response.toString());
                for (int i = 0; i <response.length(); i++) {
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        String city=jsonObject.getString("title");
                        cities.add(city);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Log.e("Adrom Response30: ", "ok");
                recyclerView.setAdapter(new CitiesAdapter(cities, new CitiesAdapter.OnCitySelected() {
                    @Override
                    public void onSelected(String city) {
                        Intent intent=new Intent();
                        intent.putExtra("city",city);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }));
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void setupViews() {
        cities=new ArrayList<>();
        edtSearch=(EditText)findViewById(R.id.edt_cities_search);
        imgBack=(ImageView)findViewById(R.id.img_cities_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.rv_cities_city);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}