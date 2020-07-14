package com.example.adrom.alibaba;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrom.alibaba.Model.TrainTicket;

public class TrainInformationActivity extends AppCompatActivity {

    TextView txtToolbarOD,txtToolbarDate,txtType,txtPrice,txtTrainId,txtCoupeCapacity;
    ImageView imgToolbarBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_information);

        TrainTicket trainTicket = getIntent().getExtras().getParcelable("trainTicket");

        setupViews();
    }

    private void setupViews() {
        txtToolbarOD = (TextView)findViewById(R.id.txt_trainInformation_OD);
        txtToolbarDate = (TextView)findViewById(R.id.txt_trainInformation_date);
        txtType = (TextView)findViewById(R.id.txt_trainInformation_type);
        txtPrice = (TextView)findViewById(R.id.txt_trainInformation_price);
        txtTrainId = (TextView)findViewById(R.id.txt_trainInformation_trainId);
        txtCoupeCapacity = (TextView)findViewById(R.id.txt_trainInformation_coupeCapacity);
        imgToolbarBack = (ImageView)findViewById(R.id.img_trainInformation_back);
        imgToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
