package com.example.adrom.alibaba.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import com.example.adrom.alibaba.Adapter.PassengerAdapter;
import com.example.adrom.alibaba.R;

public class PassengersFragment extends Fragment {
    View view;
    EditText edtName, edtCode;
    RecyclerView recyclerView;
    Button btnAdd;
    List<String> names;
    PassengerAdapter passengerAdapter;
    OnPassengerNameReceive onPassengerNameReceive;
    public static String number="";
    public static String price="";
    public static String staticName="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.passenger_fragment, container, false);
        setupViews();
        number=getArguments().getString("number");
        price=getArguments().getString("price");
        return view;
    }

    public void setOnPassengerNameReceive(OnPassengerNameReceive onPassengerNameReceive) {
        this.onPassengerNameReceive = onPassengerNameReceive;
    }

    private void setupViews() {

        names=new ArrayList<>();
        btnAdd = (Button) view.findViewById(R.id.btn_passengerFragment_add);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_pasengerFragment_passengerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        edtName = (EditText) view.findViewById(R.id.edt_passenegerFragment_name);
        edtCode = (EditText) view.findViewById(R.id.edt_passengerFragment_code);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.add(edtName.getText().toString());
                passengerAdapter=new PassengerAdapter(getContext(),names);
                passengerAdapter.setOnAdapterNameClick(new PassengerAdapter.OnAdapterNameClick() {
                    @Override
                    public void onNameClick(String name) {
                        onPassengerNameReceive.onNameReceive(name);
                        staticName=name;
                    }
                });
                recyclerView.setAdapter(passengerAdapter);
                passengerAdapter.notifyDataSetChanged();
            }
        });

    }

    public interface OnPassengerNameReceive{
        void onNameReceive(String name);
    }


}
