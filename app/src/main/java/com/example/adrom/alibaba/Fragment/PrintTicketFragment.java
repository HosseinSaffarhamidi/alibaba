package com.example.adrom.alibaba.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrom.alibaba.R;

public class PrintTicketFragment extends Fragment {
    View view;
    TextView txtRefId, txtPrice, txtChair, txtName;
    String refId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.print_ticket_fragment, container, false);
        refId = getArguments().getString("refId");
        setupViews();
        return view;
    }

    private void setupViews() {
        txtChair = (TextView) view.findViewById(R.id.txt_print_chairNumber);
        txtChair.setText(PassengersFragment.number);

        txtPrice = (TextView) view.findViewById(R.id.txt_print_price);
        txtPrice.setText(PassengersFragment.price);

        txtRefId = (TextView) view.findViewById(R.id.txt_print_refId);
        txtRefId.setText(refId);
        txtName = (TextView) view.findViewById(R.id.txt_print_name);
        txtName.setText(PassengersFragment.staticName);

    }
}
