package com.example.adrom.alibaba.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrom.alibaba.Model.Penalty;
import com.example.adrom.alibaba.R;

import java.util.List;

public class PenaltyAdapter extends RecyclerView.Adapter<PenaltyAdapter.PenaltyViewHolder> {

    private List<Penalty> penalties;

    public PenaltyAdapter(List<Penalty> penalties) {
        this.penalties = penalties;
    }

    @NonNull
    @Override
    public PenaltyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.penalty_item,viewGroup,false);
        return new PenaltyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenaltyViewHolder penaltyViewHolder, int i) {
        Penalty penalty = penalties.get(i);
        penaltyViewHolder.txtRuleTitle.setText(penalty.getRuleTitle());
        penaltyViewHolder.txtPercentage.setText(penalty.getPenaltyPercentage()+"%");
    }

    @Override
    public int getItemCount() {
        return this.penalties.size();
    }

    public class PenaltyViewHolder extends RecyclerView.ViewHolder{

        TextView txtRuleTitle,txtPercentage;
        public PenaltyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRuleTitle = (TextView)itemView.findViewById(R.id.txt_penaltyItem_rule);
            txtPercentage = (TextView)itemView.findViewById(R.id.txt_penaltyItem_percentage);
        }
    }
}
