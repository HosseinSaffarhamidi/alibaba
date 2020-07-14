package com.example.adrom.alibaba.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.adrom.alibaba.Model.Chair;
import com.example.adrom.alibaba.R;

public class ChairItemAdapter extends RecyclerView.Adapter<ChairItemAdapter.ChairViewHolder> {
    private static final int LEFTCONST = 1;
    private static final int RIGHTCONST = 2;
    private static final int RIGHTONECONST = 3;
    private Context context;
    private ArrayList<Chair> chairs;
    private OnChairSelected onChairSelected;

    public ChairItemAdapter(Context context, ArrayList<Chair> chairs) {
        this.context = context;
        this.chairs = chairs;
    }

    @NonNull
    @Override
    public ChairViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.chair_item, viewGroup, false);
        return new ChairViewHolder(view);
    }

    public void setOnChairSelected(OnChairSelected onChairSelected){
        this.onChairSelected=onChairSelected;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChairViewHolder chairViewHolder, int i) {
        Chair chair = chairs.get(i);
        chairViewHolder.txtLeft.setText(chair.getLeft());
        if (chair.getLeftSituation().equals("0")) {
            chairViewHolder.txtLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_chair_empty_style));
        } else {
            chairViewHolder.txtLeft.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_call_style));

        }

        chairViewHolder.txtRight.setText(chair.getRight());
        if (chair.getRightSituation().equals("0")) {
            chairViewHolder.txtRight.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_chair_empty_style));
        } else {
            chairViewHolder.txtRight.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_call_style));

        }

        chairViewHolder.txtRightOne.setText(chair.getRightOne());
        if (chair.getRightOneSituation().equals("0")) {
            chairViewHolder.txtRightOne.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_chair_empty_style));
        } else {
            chairViewHolder.txtRightOne.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_call_style));

        }


        chairViewHolder.txtLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chairViewHolder.txtLeft.getContentDescription().equals("notSelected")){
                    chairViewHolder.txtLeft.setContentDescription("selected");
                    chairViewHolder.txtLeft.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_selected_chair));
                    onChairSelected.onSelected(chairViewHolder.txtLeft.getText().toString());
                }else{
                    chairViewHolder.txtLeft.setContentDescription("notSelected");
                    chairViewHolder.txtLeft.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_chair_empty_style));
                }

            }
        });
        chairViewHolder.txtRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chairViewHolder.txtRight.getContentDescription().equals("notSelected")){
                    chairViewHolder.txtRight.setContentDescription("selected");
                    chairViewHolder.txtRight.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_selected_chair));
                    onChairSelected.onSelected(chairViewHolder.txtRight.getText().toString());
                }else{
                    chairViewHolder.txtRight.setContentDescription("notSelected");
                    chairViewHolder.txtRight.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_chair_empty_style));
                }
            }
        });
        chairViewHolder.txtRightOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chairViewHolder.txtRightOne.getContentDescription().equals("notSelected")){
                    chairViewHolder.txtRightOne.setContentDescription("selected");
                    chairViewHolder.txtRightOne.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_selected_chair));
                    onChairSelected.onSelected(chairViewHolder.txtRightOne.getText().toString());
                }else{
                    chairViewHolder.txtRightOne.setContentDescription("notSelected");
                    chairViewHolder.txtRightOne.setBackground(ContextCompat.getDrawable(context,R.drawable.btn_chair_empty_style));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return chairs.size();
    }

    public class ChairViewHolder extends RecyclerView.ViewHolder {
        TextView txtRightOne, txtLeft, txtRight;
        ConstraintLayout parent;

        public ChairViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLeft = (TextView) itemView.findViewById(R.id.txt_chairItem_left);
            txtRightOne = (TextView) itemView.findViewById(R.id.txt_chairItem_rightOne);
            txtRight = (TextView) itemView.findViewById(R.id.txt_chairItem_right);
        }
    }

    public interface OnChairSelected{
        void onSelected(String chairNumber);
//        void onDeSelected();
    }
}