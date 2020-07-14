package com.example.adrom.alibaba.Fragment;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.adrom.alibaba.MainActivity;
import com.example.adrom.alibaba.R;
import com.example.adrom.alibaba.Receiver.CheckInternetConnectionReceiver;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends Fragment {

    Timer timer;
    private ImageView imgLeft,imgRight;
    private View view;
    private LinearLayout linearErrorConnection;
    private CheckInternetConnectionReceiver checkInternetConnectionReceiver;
    private Handler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         checkInternetConnectionReceiver = new CheckInternetConnectionReceiver();
        getContext().registerReceiver(checkInternetConnectionReceiver,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.purge();
        timer.cancel();
        getContext().unregisterReceiver(checkInternetConnectionReceiver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.splash_screen,container,false);
        setupViews();
        leftAnimation();
        rightAnimation();
        checkInternetConnectionReceiver.setOnDisconnectErrorReceive(new CheckInternetConnectionReceiver.OnCheckConnection() {
            @Override
            public void onErrorReceive() {
                linearErrorConnection.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceive() {
                 timer = new Timer();
                 timer.schedule(new TimerTask() {
                     @Override
                     public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.fragmentManager.beginTransaction().remove(SplashFragment.this).commit();
                                timer.purge();
                                timer.cancel();
                            }
                        });
                     }
                 } , 5000, 1);

            }
        });
        return  view;
    }

    private void setupViews(){
        handler = new Handler();
        linearErrorConnection = (LinearLayout)view.findViewById(R.id.linear_splash_errorConnection);
        imgLeft = (ImageView)view.findViewById(R.id.img_splash_left);
        imgRight = (ImageView)view.findViewById(R.id.img_splash_right);
    }

    private void leftAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        RotateAnimation rotateAnimation=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        AlphaAnimation alphaAnimation=new AlphaAnimation(1,0.7f);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        imgLeft.setAnimation(animationSet);
    }
    private void rightAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        RotateAnimation rotateAnimation=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new AnticipateOvershootInterpolator());
        AlphaAnimation alphaAnimation=new AlphaAnimation(1,0.7f);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);
        imgRight.setAnimation(animationSet);
    }

    @Override
    public void onDestroyView() {
        timer.purge();
        timer.cancel();
        super.onDestroyView();
    }


}
