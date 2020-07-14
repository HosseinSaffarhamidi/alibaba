package com.example.adrom.alibaba.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.example.adrom.alibaba.R;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private List<String> cities;
    private OnCitySelected onCitySelected;
    public CitiesAdapter(List<String> cities,OnCitySelected onCitySelected){
        this.cities=cities;
        this.onCitySelected=onCitySelected;
    }
    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.city_item,viewGroup,false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder citiesViewHolder, int i) {
        final String city=cities.get(i);
        citiesViewHolder.txtTitle.setText(city);
        citiesViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCitySelected.onSelected(city);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CitiesViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        RelativeLayout parent;
        public CitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=(TextView)itemView.findViewById(R.id.txt_cityItem_title);
            parent=(RelativeLayout)itemView.findViewById(R.id.rel_cityItem_parent);
        }
    }

    public interface OnCitySelected{
        void onSelected(String city);
    }
}