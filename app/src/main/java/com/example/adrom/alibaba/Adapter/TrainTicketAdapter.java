package com.example.adrom.alibaba.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adrom.alibaba.Model.TrainTicket;
import com.example.adrom.alibaba.R;
import com.example.adrom.alibaba.TrainInformationActivity;

import java.util.List;

public class TrainTicketAdapter extends RecyclerView.Adapter<TrainTicketAdapter.TrainTicketViewHolder> {

    private Context context;
    private List<TrainTicket> trainTickets;

    public TrainTicketAdapter(Context context, List<TrainTicket> trainTickets){
        this.context = context;
        this.trainTickets = trainTickets;
    }

    @NonNull
    @Override
    public TrainTicketViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.train_item,viewGroup,false);
        return new TrainTicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainTicketViewHolder trainTicketViewHolder, int i) {
        final TrainTicket trainTicket = trainTickets.get(i);
        trainTicketViewHolder.id = trainTicket.getId();
        trainTicketViewHolder.txtType.setText("::"+trainTicket.getType());
        trainTicketViewHolder.txtStartTime.setText(trainTicket.getStartTitme());
        trainTicketViewHolder.txtEndTime.setText(trainTicket.getEndTime());
        trainTicketViewHolder.txtCapacity.setText(trainTicket.getCapacity());
        trainTicketViewHolder.txtPrice.setText(trainTicket.getPrice());
        trainTicketViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,TrainInformationActivity.class);
                intent.putExtra("trainTicket",trainTicket);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.trainTickets.size();
    }

    public class TrainTicketViewHolder extends RecyclerView.ViewHolder{

        String id;
        RelativeLayout parent;
        TextView txtType,txtStartTime,txtEndTime,txtCapacity,txtPrice;

        public TrainTicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtType = (TextView)itemView.findViewById(R.id.txt_trainItem_type);
            txtStartTime = (TextView)itemView.findViewById(R.id.txt_trainItem_startTime);
            txtEndTime = (TextView)itemView.findViewById(R.id.txt_trainItem_endTime);
            txtCapacity = (TextView)itemView.findViewById(R.id.txt_trainItem_capacity);
            txtPrice = (TextView)itemView.findViewById(R.id.txt_trainItem_price);
            parent = (RelativeLayout)itemView.findViewById(R.id.rel_trainItem_parent);
        }
    }
}
