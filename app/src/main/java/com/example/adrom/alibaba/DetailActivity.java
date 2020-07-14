package com.example.adrom.alibaba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.adrom.alibaba.Adapter.BusTicketAdapter;
import com.example.adrom.alibaba.Adapter.TicketItemAdapter;
import com.example.adrom.alibaba.Adapter.TrainTicketAdapter;
import com.example.adrom.alibaba.Model.BusTicket;
import com.example.adrom.alibaba.Model.Chair;
import com.example.adrom.alibaba.Model.Ticket;
import com.example.adrom.alibaba.Model.TrainTicket;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView txtorder,txtFilter;
    private ImageView imgBack, imgIcon;
    private String type, origin, destination, date;
    private TextView txtOrigin, txtDestination, txtDate;
    private List<Ticket> tickets;
    private List<TrainTicket> trainTickets;
    List<BusTicket> busTickets;
    BusTicketAdapter busTicketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupViews();
        getMyIntent();


    }

    private void getAllFlightTicket(String originI, String destinationI, String dateI) {
        String url = "http://adromsh.ir/alibaba/flight.php?origin=" + originI + "&destination=" + destinationI + "&date=" + dateI;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        Ticket ticket = new Ticket();
                        ticket.setId(jsonObject.getString("id"));
                        ticket.setOrigin(jsonObject.getString("origin"));
                        ticket.setDestination(jsonObject.getString("destination"));
                        ticket.setOriginAirport(jsonObject.getString("origin_airport"));
                        ticket.setDestinationAirport(jsonObject.getString("destination_airport"));
                        ticket.setDate(jsonObject.getString("date"));
                        ticket.setType(jsonObject.getString("type"));
                        String serverKind = jsonObject.getString("kind");
                        String[] kinds = serverKind.split("/");
                        ticket.setKind1(kinds[0]);
                        ticket.setKind2(kinds[1]);
                        ticket.setCompany(jsonObject.getString("company"));
                        ticket.setFlightTime(jsonObject.getString("flight_time"));
                        ticket.setLandTime(jsonObject.getString("land_time"));
                        ticket.setCapacity(jsonObject.getString("capacity"));
                        ticket.setFlightId(jsonObject.getString("flight_id"));
                        ticket.setPriceYoung(jsonObject.getString("price_young"));
                        ticket.setPriceChild(jsonObject.getString("price_child"));
                        ticket.setPriceBaby(jsonObject.getString("price_baby"));

                        tickets.add(ticket);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setAdapter(new TicketItemAdapter(tickets));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setupViews() {
        busTickets = new ArrayList<>();
        trainTickets = new ArrayList<>();
        imgIcon = (ImageView) findViewById(R.id.img_detail_icon);
        imgBack = (ImageView) findViewById(R.id.img_detail_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tickets = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rv_detail_tickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtDate = (TextView) findViewById(R.id.txt_detail_date);
        txtDestination = (TextView) findViewById(R.id.txt_detail_destination);
        txtOrigin = (TextView) findViewById(R.id.txt_detail_origin);
        txtorder=(TextView)findViewById(R.id.txt_detail_order);
        txtFilter=(TextView)findViewById(R.id.txt_detail_filter);
        txtorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(DetailActivity.this,txtorder);
                popupMenu.getMenuInflater().inflate(R.menu.pupup_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if(menuItem.getTitle().equals("کمترین قیمت")){
                            Collections.sort(busTickets, new Comparator<BusTicket>() {
                                @Override
                                public int compare(BusTicket o1, BusTicket o2) {
                                    return Integer.parseInt(o1.getCapacity())-Integer.parseInt(o2.getCapacity());
                                }
                            });
                        }

                        busTicketAdapter.notifyDataSetChanged();
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

    }

    private void getMyIntent() {
        type = getIntent().getExtras().getString("type");
        origin = getIntent().getExtras().getString("origin");
        destination = getIntent().getExtras().getString("destination");
        date = getIntent().getExtras().getString("date");
        txtOrigin.setText(origin);
        txtDate.setText(date);
        txtDestination.setText(destination);

        if (type.equals("flight")) {
            getAllFlightTicket(origin, destination, date);
        }
        if (type.equals("train")) {
            getAllTrainTickets(origin, destination, date);
            imgIcon.setImageResource(R.drawable.ic_train_white_24dp);
        }
        if (type.equals("bus")) {
            getAllBusTickets(origin, destination, date);
            imgIcon.setImageResource(R.drawable.ic_directions_bus_white_24dp);
        }

    }

    private void getAllTrainTickets(String origin, String destination, String date) {
        String url = "http://adromsh.ir/alibaba/train.php?origin=" + origin + "&destination=" + destination + "&date=" + date;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        TrainTicket trainTicket = new TrainTicket();
                        trainTicket.setId(jsonObject.getString("id"));
                        trainTicket.setTrainId(jsonObject.getString("train_id"));
                        trainTicket.setOrigin(jsonObject.getString("origin"));
                        trainTicket.setDestination(jsonObject.getString("destination"));
                        trainTicket.setStartTitme(jsonObject.getString("start_time"));
                        trainTicket.setEndTime(jsonObject.getString("end_time"));
                        trainTicket.setDate(jsonObject.getString("date"));
                        trainTicket.setType(jsonObject.getString("type"));
                        trainTicket.setCapacity(jsonObject.getString("capacity"));
                        trainTicket.setCoupe_capacity(jsonObject.getString("coupe_capacity"));
                        trainTicket.setPrice(jsonObject.getString("price"));

                        trainTickets.add(trainTicket);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    recyclerView.setAdapter(new TrainTicketAdapter(DetailActivity.this, trainTickets));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private void getAllBusTickets(String origin, String destination, String date) {
        String url = "http://adromsh.ir/alibaba/bus.php?origin=" + origin + "&destination=" + destination +
                "&date=" + date;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        BusTicket busTicket = new BusTicket();
                        busTicket.setId(jsonObject.getString("id"));
                        busTicket.setTicketId(jsonObject.getString("ticket_id"));
                        busTicket.setOrigin(jsonObject.getString("origin"));
                        busTicket.setDestination(jsonObject.getString("destination"));
                        busTicket.setOriginTerminal(jsonObject.getString("origin_terminal"));
                        busTicket.setDestinationTermianl(jsonObject.getString("destination_terminal"));
                        busTicket.setDate(jsonObject.getString("date"));
                        busTicket.setTime(jsonObject.getString("time"));
                        busTicket.setType(jsonObject.getString("type"));
                        busTicket.setDistance(jsonObject.getString("distance"));
                        busTicket.setCapacity(jsonObject.getString("capacity"));


                        String chairs = jsonObject.getString("chairs");
                        JSONArray chairsArray = new JSONArray(chairs);
                        List<Chair> serverChair = new ArrayList<>();
                        for (int j = 0; j < chairsArray.length(); j++) {
                            JSONObject chaisModel = chairsArray.getJSONObject(j);
                            Chair chair = new Chair();
                            chair.setLeft(chaisModel.getString("left"));
                            chair.setLeftSituation(chaisModel.getString("situationLeft"));
                            chair.setRight(chaisModel.getString("right"));
                            chair.setRightSituation(chaisModel.getString("situationRight"));
                            chair.setRightOne(chaisModel.getString("rightOne"));
                            chair.setRightOneSituation(chaisModel.getString("situationOne"));
                            serverChair.add(chair);
                        }

                        busTicket.setChairs(serverChair);
                        busTicket.setPrice(jsonObject.getString("price"));

                        busTickets.add(busTicket);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                busTicketAdapter=new BusTicketAdapter(DetailActivity.this, busTickets);
                recyclerView.setAdapter(busTicketAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG", "onErrorResponse: " + error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}