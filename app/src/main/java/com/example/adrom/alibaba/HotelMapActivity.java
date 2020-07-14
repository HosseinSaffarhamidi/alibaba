package com.example.adrom.alibaba;

import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

import com.example.adrom.alibaba.Fragment.ShowHotelListFragment;
import com.example.adrom.alibaba.Model.Hotel;
import ir.parsijoo.map.android.Controls.ZoomLevel;
import ir.parsijoo.map.android.Viewer;

public class HotelMapActivity extends AppCompatActivity {
    private String city = "";
    Viewer viewer;
    double latitude, longitude;
    ImageView imgBack;
    FloatingActionButton fab;
    List<Hotel>hotels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_map);
        setupViews();
        city = getIntent().getExtras().getString("city");
        getHotels(city);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("hotels", (ArrayList<? extends Parcelable>) hotels);
                ShowHotelListFragment showHotelListFragment=new ShowHotelListFragment();
                showHotelListFragment.setArguments(bundle);
                transaction.add(R.id.frame_hotel_fragmentParent,showHotelListFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void setupViews() {
        hotels=new ArrayList<>();
        fab=(FloatingActionButton)findViewById(R.id.fab_hotel_showList);
        imgBack=(ImageView)findViewById(R.id.img_hotel_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewer = (Viewer) findViewById(R.id.map_hotelMap_map);
        viewer.setFirstLoadCallBack(new MapView.OnFirstLayoutListener() {
            @Override
            public void onFirstLayout(View v, int left, int top, int right, int bottom) {
                //viewer.setStartPosition(new GeoPoint(latitude,longitude), ZoomLevel.City_3);
            }
        });

    }

    private void getHotels(String city) {
        String url = "http://adromsh.ir/alibaba/hotel.php?city=" + city;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Hotel hotel = new Hotel();
                        hotel.setId(jsonObject.getString("id"));
                        hotel.setName(jsonObject.getString("name"));
                        hotel.setCity(jsonObject.getString("city"));
                        hotel.setStar(jsonObject.getString("star"));
                        hotel.setBedCount(jsonObject.getString("bed_count"));
                        hotel.setImage(jsonObject.getString("image"));
                        hotel.setLat(jsonObject.getString("lat"));
                        hotel.setLang(jsonObject.getString("lang"));
                        hotel.setPrice(jsonObject.getString("price"));
                        longitude = Double.parseDouble(hotel.getLang());
                        latitude = Double.parseDouble(hotel.getLat());
                        viewer.addMarker(new GeoPoint(latitude,longitude));

                        hotels.add(hotel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                viewer.animateToPosition(new GeoPoint(latitude,longitude));
                viewer.setZoom(ZoomLevel.City_3);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}

