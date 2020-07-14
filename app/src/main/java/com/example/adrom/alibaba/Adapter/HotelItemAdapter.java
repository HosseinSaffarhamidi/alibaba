package com.example.adrom.alibaba.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.example.adrom.alibaba.Model.Hotel;
import com.example.adrom.alibaba.R;

public class HotelItemAdapter extends RecyclerView.Adapter<HotelItemAdapter.HotelItemViewHolder> {
    ArrayList<Hotel> hotels;
    Context context;
    public HotelItemAdapter(ArrayList<Hotel>hotels,Context context){
        this.hotels=hotels;
        this.context=context;
    }
    @NonNull
    @Override
    public HotelItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.show_hotel_list_item,viewGroup,false);
        return new HotelItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelItemViewHolder hotelItemViewHolder, int i) {
        Hotel hotel=hotels.get(i);
        hotelItemViewHolder.txtName.setText(hotel.getName());
        hotelItemViewHolder.txtPrice.setText(hotel.getPrice());
        hotelItemViewHolder.ratingBar.setRating(Float.parseFloat(hotel.getStar()));
        Picasso.get().load(hotel.getImage()).into(hotelItemViewHolder.image);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class HotelItemViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView txtName,txtPrice;
        RatingBar ratingBar;
        public HotelItemViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.img_showHotelList_image);
            txtName=(TextView)itemView.findViewById(R.id.txt_showHotelList_name);
            txtPrice=(TextView)itemView.findViewById(R.id.img_showHotelList_price);
            ratingBar=(RatingBar)itemView.findViewById(R.id.rating_showHotelList);
        }
    }
}