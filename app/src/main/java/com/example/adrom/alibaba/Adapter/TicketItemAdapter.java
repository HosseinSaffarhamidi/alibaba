package com.example.adrom.alibaba.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adrom.alibaba.InformationActivity;
import com.example.adrom.alibaba.Model.Ticket;
import com.example.adrom.alibaba.R;

import java.util.List;

public class TicketItemAdapter extends RecyclerView.Adapter<TicketItemAdapter.TicketViewHolder> {
    private Context context;
    private List<Ticket> tickets;

    public TicketItemAdapter( List<Ticket> tickets ){
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_item,viewGroup,false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder ticketViewHolder, int i) {
        final Ticket ticket = this.tickets.get(i);
        ticketViewHolder.id = ticket.getId();
        ticketViewHolder.txtPrice.setText(ticket.getPriceYoung());
        ticketViewHolder.txtCapacity.setText(ticket.getCapacity()+" نفر");
        ticketViewHolder.txtCompany.setText(ticket.getCompany());
        ticketViewHolder.txtTime.setText(ticket.getFlightTime());
        ticketViewHolder.txtKind1.setText(ticket.getKind1());
        ticketViewHolder.txtKind2.setText(ticket.getKind2());
        ticketViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,InformationActivity.class);
                intent.putExtra("ticket",ticket);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("ticket",ticket);مخصوص ارسال برای فرگمنت هاست
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.tickets.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout parent;
        TextView txtPrice,txtTime,txtCompany,txtCapacity,txtKind1,txtKind2;
        String id;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtPrice = (TextView)itemView.findViewById(R.id.txt_detail_price);
            txtTime = (TextView)itemView.findViewById(R.id.txt_detail_time);
            txtCompany = (TextView)itemView.findViewById(R.id.txt_detail_company);
            txtCapacity = (TextView)itemView.findViewById(R.id.txt_detail_capacity);
            txtKind1 = (TextView)itemView.findViewById(R.id.txt_detail_kind1);
            txtKind2 = (TextView)itemView.findViewById(R.id.txt_detail_kind2);
            parent = (RelativeLayout)itemView.findViewById(R.id.rel_detail_itemParent);
        }
    }

}
