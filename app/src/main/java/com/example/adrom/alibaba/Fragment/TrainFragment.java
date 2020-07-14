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
import com.example.adrom.alibaba.DetailActivity;
import com.example.adrom.alibaba.MainActivity;
import com.example.adrom.alibaba.R;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

public class TrainFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private static final int ORIGIN_REQUEST_CODE = 86;
    private static final int DESTINATION_REQUEST_CODE = 90;
    private View view;
    TextView edtOrigin,edtDestination,txtDate,txtPassengers;
    Button btnSeeach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.train_fragment,container,false) ;
        setupViews();
        return  view;
    }

    private void setupViews() {
        edtOrigin = (TextView)view.findViewById(R.id.edt_trainFragment_origin);
        edtDestination = (TextView)view.findViewById(R.id.edt_trainFragment_destination);
        txtDate = (TextView)view.findViewById(R.id.txt_trainFragment_date);
        txtPassengers = (TextView)view.findViewById(R.id.txt_trainFragment_passengers);
        btnSeeach = (Button)view.findViewById(R.id.btn_trainFragment_search);

        edtOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CitiesActivity.class);
                startActivityForResult(intent,ORIGIN_REQUEST_CODE);
            }
        });

        edtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CitiesActivity.class);
                startActivityForResult(intent,DESTINATION_REQUEST_CODE);
            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersianCalendar persianCalendar = new PersianCalendar();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        TrainFragment.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay());
                datePickerDialog.show(getActivity().getFragmentManager(),"DatepickerDialog");
            }
        });

        txtPassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PassengersDialog passengersDialog = new PassengersDialog();
                passengersDialog.show(MainActivity.fragmentManager,null);
                passengersDialog.setOnSubmitButtonClicked(new PassengersDialog.OnSubmitButtonClicked() {
                    @Override
                    public void onSubmitted(String count) {
                        txtPassengers.setText(count+" نفر ");
                    }
                });
            }
        });

        btnSeeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DetailActivity.class);
                intent.putExtra("type","train");
                intent.putExtra("origin",edtOrigin.getText().toString());
                intent.putExtra("destination",edtDestination.getText().toString());
                intent.putExtra("date",txtDate.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if( requestCode == ORIGIN_REQUEST_CODE && requestCode == -1 && data != null ){
            edtOrigin.setText(data.getExtras().getString("city"));
        }

        if( requestCode == DESTINATION_REQUEST_CODE && requestCode == -1 && data != null ){
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
        txtDate.setText(dayOfMonth+" "+monthName+" "+year);
    }
}
