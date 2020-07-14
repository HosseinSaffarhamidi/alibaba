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

import java.util.ArrayList;

import com.example.adrom.alibaba.Adapter.HotelItemAdapter;
import com.example.adrom.alibaba.Model.Hotel;
import com.example.adrom.alibaba.R;

public class ShowHotelListFragment extends Fragment {
    View view;
    ArrayList<Hotel> hotels;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.show_list_fragment,container,false);
        setupViews();
        hotels=getArguments().getParcelableArrayList("hotels");
        recyclerView.setAdapter(new HotelItemAdapter(hotels,getContext()));
        return view;
    }

    private void setupViews() {
        recyclerView=(RecyclerView)view.findViewById(R.id.rv_showList_hotelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}

