package com.example.adrom.alibaba.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrom.alibaba.R;

public class PassengersDialog extends DialogFragment {

    OnSubmitButtonClicked onSubmitButtonClicked;
    View view;
    TextView txtCountUsual;
    ImageView imgAdd,imgMinus;
    Button btnSubmit;
    int count = 1;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_pick_passengers,null);
        setupViews();
        builder.setView(view);
        return builder.create();
    }

    private void setupViews() {
        txtCountUsual = (TextView)view.findViewById(R.id.txt_dialogPassengers_count);

        imgAdd = (ImageView)view.findViewById(R.id.img_dialogPassengers_add);

        imgMinus = (ImageView)view.findViewById(R.id.img_dialogPassengers_remove);

        btnSubmit = (Button)view.findViewById(R.id.btn_dialogPassengers_submit);

        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                txtCountUsual.setText(count+"");
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( count != 1 ){
                    count--;
                }
                txtCountUsual.setText( count +"");
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitButtonClicked.onSubmitted(txtCountUsual.getText().toString());
                dismiss();
            }
        });
    }

    public void setOnSubmitButtonClicked(OnSubmitButtonClicked onSubmitButtonClicked){
        this.onSubmitButtonClicked = onSubmitButtonClicked;
    }

    public interface OnSubmitButtonClicked{
        void onSubmitted(String count);
    }
}
