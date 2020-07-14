package com.example.adrom.alibaba;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.adrom.alibaba.Model.BusTicket;
import com.example.adrom.alibaba.Model.Chair;

public class BusInformationActivity extends AppCompatActivity {
    BusTicket busTicket;
    TextView txtOD, txtToolbarDate,txtOrigin,txtTime,txtType,txtprice,txtOriginTerminal,txtDestinationTerminal
            ,txtDistance;
    ImageView imgToolbarBack;
    RecyclerView recyclerView;
    Button btnChooseChair;
    ArrayList<Chair> chairList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_information);
        setupViews();
        busTicket = getIntent().getExtras().getParcelable("busTicket");
        chairList=getIntent().getExtras().getParcelableArrayList("chairs");
        txtOD.setText(busTicket.getOrigin()+" - "+busTicket.getDestination());
        txtToolbarDate.setText(busTicket.getDate());
        txtOrigin.setText(busTicket.getOrigin()+" - "+busTicket.getOriginTerminal());
        txtTime.setText(busTicket.getTime());
        txtType.setText(busTicket.getType());
        txtprice.setText(busTicket.getPrice());
        txtOriginTerminal.setText(busTicket.getOriginTerminal());
        txtDestinationTerminal.setText(busTicket.getDestinationTermianl());
        txtDistance.setText(busTicket.getDistance()+" کیلومتر");

        imgToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViews() {
        txtOD=(TextView)findViewById(R.id.txt_busInformation_OD);
        txtToolbarDate=(TextView)findViewById(R.id.txt_busInformation_date);
        txtOrigin=(TextView)findViewById(R.id.txt_busInformation_origin);
        txtTime=(TextView)findViewById(R.id.txt_busInformation_time);
        txtType=(TextView)findViewById(R.id.txt_busInformation_type);
        txtprice=(TextView)findViewById(R.id.txt_busInformation_price);
        txtOriginTerminal=(TextView)findViewById(R.id.txt_busInformation_originTerminal);
        txtDestinationTerminal=(TextView)findViewById(R.id.txt_busInformation_destinationTerminal);
        txtDistance=(TextView)findViewById(R.id.txt_busInformation_distance);
        imgToolbarBack=(ImageView) findViewById(R.id.img_busInformation_back);
        recyclerView=(RecyclerView) findViewById(R.id.rv_busInformation_penaltyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        btnChooseChair=(Button) findViewById(R.id.btn_busInformation_chooseChair);
        btnChooseChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ChairActivity.class);
                intent.putParcelableArrayListExtra("chairs",chairList);
                intent.putExtra("price",busTicket.getPrice());
                startActivity(intent);
            }
        });

    }
}
