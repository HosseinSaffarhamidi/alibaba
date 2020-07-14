package com.example.adrom.alibaba.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adrom.alibaba.R;

public class BusInformationFragment extends Fragment {
    View view;
    TextView txtEmail, txtName, txtPrice;
    SharedPreferences sharedPreferences;
    OnPayButtonClick onPayButtonClick;
    Button btnPay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bus_information_fragment, container, false);
        setupViews();
        return view;
    }

    private void setupViews() {
        btnPay = (Button) view.findViewById(R.id.btn_busInformationFragment_submit);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPayButtonClick.onPayClicked(txtPrice.getText().toString());
            }
        });
        sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        txtEmail = (TextView) view.findViewById(R.id.txt_busInformationFragment_email);
        txtName = (TextView) view.findViewById(R.id.txt_busInformationFragment_name);
        txtPrice = (TextView) view.findViewById(R.id.txt_busInformationFragment_price);

        String email = sharedPreferences.getString("email", "");
        if (email.equals("")) {
            txtEmail.setText("adromsh@gmail.com");//باید دیالوگ login رو صدا بزنیم
        } else {
            txtEmail.setText(email);
        }

        txtName.setText(PassengersFragment.staticName);
        txtPrice.setText(PassengersFragment.price);


    }

    public void setOnPayButtonClick(OnPayButtonClick onPayButtonClick) {
        this.onPayButtonClick = onPayButtonClick;
    }

    public interface OnPayButtonClick {
        void onPayClicked(String amount);

    }
}

