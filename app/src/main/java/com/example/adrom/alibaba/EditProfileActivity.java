package com.example.adrom.alibaba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adrom.alibaba.Fragment.InsideFlightFragment;
import com.example.adrom.alibaba.Model.Profile;
import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageView imgBack;
    EditText edtName,edtFamily,edtCode,edtMobile;
    TextView txtBirthDay;
    Button btnSave;
    AppCompatSpinner spinner;
    String email;
    ArrayAdapter arrayAdapter;
    String manOrWomanSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setupViews();
        email = getIntent().getExtras().getString("email");
    }

    private void setupViews() {
        btnSave = (Button)findViewById(R.id.btn_editProfile_save);
        imgBack = (ImageView)findViewById(R.id.img_edit_profile_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        edtName = (EditText)findViewById(R.id.edt_editProfile_name);
        edtFamily = (EditText)findViewById(R.id.edt_editProfile_family);
        edtCode = (EditText)findViewById(R.id.edt_editProfile_code);
        edtMobile = (EditText)findViewById(R.id.edt_editProfile_mobile);
        txtBirthDay = (TextView)findViewById(R.id.txt_editProfile_birthDay);
        txtBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersianCalendar persianCalendar = new PersianCalendar();
                DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                        EditProfileActivity.this,
                        persianCalendar.getPersianYear(),
                        persianCalendar.getPersianMonth(),
                        persianCalendar.getPersianDay());
                datePickerDialog.show(getFragmentManager(),"DatepickerDialog");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    saveProfileData(email,edtName.getText().toString(),edtFamily.getText().toString(),txtBirthDay.getText().toString(),
                            edtCode.getText().toString(),edtMobile.getText().toString(),manOrWomanSelected);
            }
        });
        final ArrayList<String> manOrWomanList = new ArrayList<>();
        manOrWomanList.add("مرد");
        manOrWomanList.add("زن");
        ArrayAdapter arrayAdapter = new ArrayAdapter(EditProfileActivity.this,android.R.layout.simple_spinner_item,manOrWomanList);
        spinner = (AppCompatSpinner)findViewById(R.id.sp_editProfile_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                manOrWomanSelected = manOrWomanList.get(position) ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void saveProfileData(final String email, final String name, final String family, final String birthDay, final String code , final String mobile, final String manOrWoman ){
        String url = "http://adromsh.ir/alibaba/updateuser.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if ( response.equals("ok") ){
                    Intent intent = new Intent();
                    Profile mobil = new Profile();
                    mobil.setTitle("شماره همراه: ");
                    mobil.setValue(mobile);
                    intent.putExtra("mobile",mobil);

                    Profile mw = new Profile();
                    mw.setTitle("جنسیت: ");
                    mw.setValue(manOrWoman);
                    intent.putExtra("mw",mw);

                    Profile codeP = new Profile();
                    codeP.setTitle("کد ملی: ");
                    codeP.setValue(code);
                    intent.putExtra("code",codeP);

                    Profile dateP = new Profile();
                    dateP.setTitle("تاریخ تولد: ");
                    dateP.setValue(manOrWoman);
                    intent.putExtra("date",dateP);

                    setResult(RESULT_OK,intent);
                    finish();


                }else{
                    Toast.makeText(EditProfileActivity.this,"مشکل در ثبت اطلاعات کاربری",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("LOG: ","onErrorResponse: "+error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("name",name);
                params.put("family",family);
                params.put("birthDay",birthDay);
                params.put("code",code);
                params.put("mobile",mobile);
                params.put("manorwoman",manOrWoman);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year + "/" + (monthOfYear+1) + "/" + dayOfMonth;
        txtBirthDay.setText(date);
    }
}
