package com.example.adrom.alibaba.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.adrom.alibaba.CitiesActivity;
import com.example.adrom.alibaba.HotelMapActivity;
import com.example.adrom.alibaba.R;


public class HotelFragment extends Fragment {
    private static final int REQUEST_DESTINATION_CODE = 152;
    View view;
    TextView txtDestination,txtDate;
    Button btnSearch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.hotel_fragment,container,false);
        setupViews();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_DESTINATION_CODE && resultCode==-1&& data!=null){
            txtDestination.setText(data.getExtras().getString("city"));
        }
    }

    private void setupViews() {
        txtDestination=(TextView)view.findViewById(R.id.edt_hotel_destination);
        txtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CitiesActivity.class);
                startActivityForResult(intent,REQUEST_DESTINATION_CODE);
                getActivity().overridePendingTransition(R.anim.inner_animation,R.anim.out_animation);
            }
        });
        txtDate=(TextView)view.findViewById(R.id.edt_hotel_date);
        btnSearch=(Button)view.findViewById(R.id.btn_hotel_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), HotelMapActivity.class);
                intent.putExtra("city",txtDestination.getText().toString());
                getContext().startActivity(intent);
            }
        });
    }
}
