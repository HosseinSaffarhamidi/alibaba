package com.example.adrom.alibaba;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.adrom.alibaba.Adapter.ProfileAdapter;
import com.example.adrom.alibaba.Model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private static final int  REQUEST_CODE= 491;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    List<Profile> profiles;
    FloatingActionButton fab;
    String email="";
    ProfileAdapter profileAdapter;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupViews();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                intent.putExtra("email",email);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

    }

    private void setupViews() {
        imgBack = (ImageView)findViewById(R.id.img_profile_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        fab = (FloatingActionButton)findViewById(R.id.fb_profile_edit);
        profiles = new ArrayList<>();
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        email = sharedPreferences.getString("email","");

        Profile defaultProfile = new Profile();
        defaultProfile.setTitle("موجودی حساب: ");
        defaultProfile.setValue("0 ریال");
        profiles.add(defaultProfile);

        Profile profile = new Profile();
        profile.setTitle("ایمیل: ");
        profile.setValue(email);
        profiles.add(profile);

        String mobile = sharedPreferences.getString("mobile","");
        String mw = sharedPreferences.getString("mw","");
        String code = sharedPreferences.getString("code","");
        String date = sharedPreferences.getString("date","");


        if( mobile.equals("") ){
            Profile profile2 = new Profile();
            profile2.setTitle("شماره همراه: ");
            profiles.add(profile2);
        }else{
            Profile profile2 = new Profile();
            profile2.setTitle("شماره همراه: ");
            profile2.setValue(mobile);
            profiles.add(profile2);
        }

        if( mw.equals("") ){
            Profile profile3 = new Profile();
            profile3.setTitle("جنسیت: ");
            profiles.add(profile3);
        }else{
            Profile profile3 = new Profile();
            profile3.setTitle("جنسیت: ");
            profile3.setValue(mw);
            profiles.add(profile3);
        }

        if( code.equals("") ){
            Profile profile4 = new Profile();
            profile4.setTitle("کد ملی: ");
            profiles.add(profile4);
        }else{
            Profile profile4 = new Profile();
            profile4.setTitle("کد ملی: ");
            profile4.setValue(code);
            profiles.add(profile4);
        }

        if( date.equals("") ){
            Profile profile5 = new Profile();
            profile5.setTitle("کد ملی: ");
            profiles.add(profile5);
        }else{
            Profile profile5 = new Profile();
            profile5.setTitle("تاریخ تولد: ");
            profile5.setValue(date);
            profiles.add(profile5);
        }


        recyclerView = (RecyclerView)findViewById(R.id.rv_profile_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileAdapter = new ProfileAdapter(profiles);
        recyclerView.setAdapter(profileAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( requestCode == REQUEST_CODE && requestCode == RESULT_OK && data != null ){
            Profile mobile =    data.getParcelableExtra("mobile");
            Profile mw =    data.getParcelableExtra("mw");
            Profile code =    data.getParcelableExtra("code");
            Profile date =    data.getParcelableExtra("date");

            profiles.clear();

            Profile defaultProfile = new Profile();
            defaultProfile.setTitle("موجودی حساب: ");
            defaultProfile.setValue("0 ریال");
            profiles.add(defaultProfile);

            Profile emailP = new Profile();
            emailP.setTitle("ایمیل: ");
            emailP.setValue(email);
            profiles.add(emailP);

            profiles.add(mobile);
            profiles.add(mw);
            profiles.add(code);
            profiles.add(date);

            profileAdapter.notifyDataSetChanged();

            SharedPreferences.Editor editor = sharedPreferences.edit() ;
            editor.putString("mobile",mobile.getValue());
            editor.putString("mw",mw.getValue());
            editor.putString("code",code.getValue());
            editor.putString("date",date.getValue());
            editor.apply();


        }
    }
}
