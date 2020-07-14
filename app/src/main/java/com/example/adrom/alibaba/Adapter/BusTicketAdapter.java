package com.example.adrom.alibaba.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adrom.alibaba.BusInformationActivity;
import com.example.adrom.alibaba.Model.BusTicket;
import com.example.adrom.alibaba.R;

import java.util.ArrayList;
import java.util.List;

public class BusTicketAdapter extends RecyclerView.Adapter<BusTicketAdapter.BusTicketViewHolder> {

    private Context context;
    private List<BusTicket> busTickets;

    public BusTicketAdapter(Context context,List<BusTicket> busTickets){
        this.context = context;
        this.busTickets = busTickets;
    }

    @NonNull
    @Override
    public BusTicketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bus_item,viewGroup,false);
        return new BusTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusTicketViewHolder busTicketViewHolder, int i) {
       final BusTicket busTicket = busTickets.get(i);
       busTicketViewHolder.txtOrigin.setText(busTicket.getOrigin());
       busTicketViewHolder.txtOriginTerminal.setText(busTicket.getOriginTerminal());
       busTicketViewHolder.txtDestinationTerminal.setText(busTicket.getDestinationTermianl());
       busTicketViewHolder.txtType.setText(busTicket.getType());
       busTicketViewHolder.txtPrice.setText(busTicket.getPrice());
       busTicketViewHolder.txtCapacity.setText("ظرفیت موجود: "+busTicket.getCapacity()+" نفر");
       busTicketViewHolder.parent.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context,BusInformationActivity.class);
               intent.putExtra("busTicket",busTicket);
               intent.putParcelableArrayListExtra("chairs", (ArrayList<? extends Parcelable>) busTicket.getChairs());
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return busTickets.size();
    }

    public class BusTicketViewHolder extends RecyclerView.ViewHolder{

        TextView txtOriginTerminal,txtDestinationTerminal,txtType,txtPrice,txtOrigin,txtCapacity;
        RelativeLayout parent;

        public BusTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrigin = (TextView)itemView.findViewById(R.id.txt_busItem_origin);
            txtOriginTerminal = (TextView)itemView.findViewById(R.id.txt_busItem_originTerminal);
            txtDestinationTerminal = (TextView)itemView.findViewById(R.id.txt_busItem_destinationTerminal);
            txtType = (TextView)itemView.findViewById(R.id.txt_busItem_type);
            txtCapacity = (TextView)itemView.findViewById(R.id.txt_busItem_capacity);
            txtPrice = (TextView)itemView.findViewById(R.id.txt_busItem_price);
            parent = (RelativeLayout)itemView.findViewById(R.id.rel_busItem_parent);
        }
    }
}
