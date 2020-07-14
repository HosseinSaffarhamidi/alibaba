package com.example.adrom.alibaba.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.example.adrom.alibaba.Fragment.PassengersFragment;
import com.example.adrom.alibaba.R;

public class PassengerAdapter extends RecyclerView.Adapter<PassengerAdapter.PassengerViewHolder> {
    private OnAdapterNameClick onAdapterNameClick;
    private Context context;
    List<String> names;
    public PassengerAdapter(Context context,List<String> names){
        this.context=context;
        this.names=names;
    }
    @NonNull
    @Override
    public PassengerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.passenger_item,viewGroup,false);
        return new PassengerViewHolder(view);
    }

    public void setOnAdapterNameClick(OnAdapterNameClick onAdapterNameClick) {
        this.onAdapterNameClick = onAdapterNameClick;
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerViewHolder passengerViewHolder, int i) {
        final String name=names.get(i);
        passengerViewHolder.txtName.setText(name);
        passengerViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAdapterNameClick.onNameClick(name);
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class PassengerViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName;
        RelativeLayout parent;
        public PassengerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=(TextView)itemView.findViewById(R.id.txt_passengerItem_name);
            parent=(RelativeLayout)itemView.findViewById(R.id.rel_passengerItem_parent);
        }
    }

    public interface OnAdapterNameClick{
        void onNameClick(String name);
    }
}
