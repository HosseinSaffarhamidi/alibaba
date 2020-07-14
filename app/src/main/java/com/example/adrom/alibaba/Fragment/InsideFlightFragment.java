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
import android.widget.EditText;
import android.widget.TextView;

import com.example.adrom.alibaba.CitiesActivity;
import com.example.adrom.alibaba.DetailActivity;
import com.example.adrom.alibaba.MainActivity;
import com.example.adrom.alibaba.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

public class InsideFlightFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    private static int REQUEST_ORIGIN_CODE = 185;
    private static int REQUEST_DESTINATION_CODE = 195;
    View view;
    Button btnSearch;
    TextView edtSource,edtDestination,edtDate;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.inside_flight_fragment,container,false) ;
        btnSearch =(Button)view.findViewById(R.id.btn_insideFlight_search);
        edtSource =(TextView)view.findViewById(R.id.edt_insideFlight_source);
        edtDestination =(TextView)view.findViewById(R.id.edt_insideFlight_destination);
        edtDate =(TextView)view.findViewById(R.id.edt_insideFlight_date);
        edtSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CitiesActivity.class);
                startActivityForResult(intent,REQUEST_ORIGIN_CODE);
                getActivity().overridePendingTransition(R.anim.inner_animation,R.anim.out_animation);
            }
        });

        edtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CitiesActivity.class);
                startActivityForResult(intent,REQUEST_DESTINATION_CODE);
                getActivity().overridePendingTransition(R.anim.inner_animation,R.anim.out_animation);
            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersianCalendar persianCalendar = new PersianCalendar();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        InsideFlightFragment.this,
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
                intent.putExtra("type","flight");
                intent.putExtra("origin",edtSource.getText().toString());
                intent.putExtra("destination",edtDestination.getText().toString());
                intent.putExtra("date",edtDate.getText().toString());
                startActivity(intent);
            }
        });

        return  view;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REQUEST_ORIGIN_CODE && resultCode == -1 && data != null ){
            edtSource.setText(data.getExtras().getString("city"));
        }else if( requestCode == REQUEST_DESTINATION_CODE && resultCode == -1 && data != null ){
            edtDestination.setText(data.getExtras().getString("city"));
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
        edtDate.setText(dayOfMonth+" "+monthName+" "+year);
    }
}
