package com.example.adrom.alibaba.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.adrom.alibaba.CitiesActivity;
import com.example.adrom.alibaba.DetailActivity;
import com.example.adrom.alibaba.Model.BusTicket;
import com.example.adrom.alibaba.Model.Chair;
import com.example.adrom.alibaba.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BusFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    private static final int REQUEST_ORIGIN_CODE = 185;
    private static final int REQUEST_DESTINATION_CODE = 195;
    private View view;
    Button btnSearch;
    TextView txtOrigin,txtDestination,txtDate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bus_fragment,container,false) ;
        setupViews();
        return  view;
    }

    private void setupViews() {
        btnSearch = (Button)view.findViewById(R.id.btn_insideFlight_search);
        txtOrigin = (TextView)view.findViewById(R.id.edt_insideFlight_source);
        txtDestination = (TextView)view.findViewById(R.id.edt_insideFlight_destination);
        txtDate = (TextView)view.findViewById(R.id.edt_insideFlight_date);

        txtOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CitiesActivity.class);
                startActivityForResult(intent,REQUEST_ORIGIN_CODE);
                getActivity().overridePendingTransition(R.anim.inner_animation,R.anim.out_animation);
            }
        });

        txtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CitiesActivity.class);
                startActivityForResult(intent,REQUEST_DESTINATION_CODE);
                getActivity().overridePendingTransition(R.anim.inner_animation,R.anim.out_animation);
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersianCalendar persianCalendar = new PersianCalendar();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        BusFragment.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay());
                datePickerDialog.show(getActivity().getFragmentManager(),"DatepickerDialog");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DetailActivity.class);
                intent.putExtra("type","bus");
                intent.putExtra("origin",txtOrigin.getText().toString());
                intent.putExtra("destination",txtDestination.getText().toString());
                intent.putExtra("date",txtDate.getText().toString());
                startActivity(intent);
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REQUEST_ORIGIN_CODE && resultCode == -1 && data != null ){
            txtOrigin.setText(data.getExtras().getString("city"));
        }else if( requestCode == REQUEST_DESTINATION_CODE && resultCode == -1 && data != null ){
            txtDestination.setText(data.getExtras().getString("city"));
        }
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String monthName = "";
        switch (monthOfYear){
            case 0:
                monthName = "فروردین";
                break;

            case 1:
                monthName = "اردیبهشت";
                break;

            case 2:
                monthName = "خرداد";
                break;

            case 3:
                monthName = "تیر";
                break;

            case 4:
                monthName = "مرداد";
                break;

            case 5:
                monthName = "شهریور";
                break;

            case 6:
                monthName = "مهر";
                break;

            case 7:
                monthName = "آبان";
                break;

            case 8:
                monthName = "آذر";
                break;

            case 9:
                monthName = "دی";
                break;

            case 10:
                monthName = "بهمن";
                break;

            case 11:
                monthName = "اسفند";
                break;
        }
        txtDate.setText(dayOfMonth+" "+monthName+" "+year);
    }
}
