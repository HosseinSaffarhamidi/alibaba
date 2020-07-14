package com.example.adrom.alibaba.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adrom.alibaba.Adapter.ChairItemAdapter;
import com.example.adrom.alibaba.ChairActivity;
import com.example.adrom.alibaba.R;

public class ChairFragment extends Fragment {
    OnSubmitClicked onSubmitClicked;
    RecyclerView recyclerView;
    private String number="";
    TextView txtPrice;
    Button btnSubmit;
    String price;
    View view;
    ChairItemAdapter chairItemAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.chair_fragment,container,false);
        price=getArguments().getString("price");
        setupViews();
        txtPrice.setText(price);
        return view;

    }

    private void setupViews() {
        txtPrice=(TextView)view.findViewById(R.id.txt_chair_totalPrice);
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_chair_chairList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chairItemAdapter=new ChairItemAdapter(getContext(),ChairActivity.chairs);
        recyclerView.setAdapter(chairItemAdapter);

        chairItemAdapter.setOnChairSelected(new ChairItemAdapter.OnChairSelected() {
            @Override
            public void onSelected(String chairNumber) {
                number=chairNumber;
                btnSubmit.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.btn_call_style));
            }
        });
        btnSubmit=(Button)view.findViewById(R.id.btn_chair_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClicked.onClicked(number,price);
            }
        });
    }

    public void setOnSubmitClicked(OnSubmitClicked onSubmitClicked) {
        this.onSubmitClicked = onSubmitClicked;
    }

    public interface OnSubmitClicked{
        void onClicked(String number,String price);
    }
}
