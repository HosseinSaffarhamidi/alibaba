package com.example.adrom.alibaba;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.adrom.alibaba.Adapter.MyViewPagerAdapter;
import com.example.adrom.alibaba.Adapter.NavigationAdapter;
import com.example.adrom.alibaba.Fragment.BusFragment;
import com.example.adrom.alibaba.Fragment.HotelFragment;
import com.example.adrom.alibaba.Fragment.InsideFlightFragment;
import com.example.adrom.alibaba.Fragment.OutsideFlightFragment;
import com.example.adrom.alibaba.Fragment.SplashFragment;
import com.example.adrom.alibaba.Fragment.TrainFragment;
import com.example.adrom.alibaba.Model.NavigationModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imgHamberMenu;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txtNavigationEmail;
    RecyclerView recyclerViewMenu;
    List<NavigationModel> models;
    SplashFragment splashFragment;
    public static FragmentManager fragmentManager;
    NavigationAdapter navigationAdapter;
    DrawerLayout drawer;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setupViews();
        setNavigationView();
        showSplashFragment();

    }


    private void setNavigationView() {
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        String email=sharedPreferences.getString("email","");
        txtNavigationEmail.setText(email);
        models = new ArrayList<>();
        NavigationModel model = new NavigationModel();
        model.setDrawable(R.drawable.ic_profile_black_24dp);
        model.setTitle("پروفایل کاربری");

        NavigationModel model2 = new NavigationModel();
        model2.setDrawable(R.drawable.ic_passengers_black_24dp);
        model2.setTitle("لیست مسافران");

        NavigationModel model3 = new NavigationModel();
        model3.setDrawable(R.drawable.ic_money_black_24dp);
        model3.setTitle("سوابق تراکنش");

        NavigationModel model4 = new NavigationModel();
        model4.setDrawable(R.drawable.ic_shopping_cart_black_24dp);
        model4.setTitle("خرید های من");

        NavigationModel model5 = new NavigationModel();
        model5.setDrawable(R.drawable.ic_exit_to_app_black_24dp);
        model5.setTitle("خروج از حساب کاربری");
        models.add(model);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        models.add(model5);
        navigationAdapter=new NavigationAdapter(this,models);
        recyclerViewMenu.setAdapter(navigationAdapter);
        navigationAdapter.setOnDialogDismissed(new NavigationAdapter.OnDialogDismissed() {
            @Override
            public void onDismissed(String email) {
                drawer.closeDrawer(Gravity.RIGHT);
                txtNavigationEmail.setText(email);

                sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("email",email);
                editor.apply();
            }
        });
    }

    private void setupViews() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_main_parent);
        tabLayout = (TabLayout) findViewById(R.id.tab_content_tab);
        viewPager = (ViewPager) findViewById(R.id.vp_content_viewPager);
        tabLayout.setupWithViewPager(viewPager);
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFragment(new HotelFragment(), "هتل");
        myViewPagerAdapter.addFragment(new BusFragment(), "اتوبوس");
        myViewPagerAdapter.addFragment(new TrainFragment(), "قطار");
        myViewPagerAdapter.addFragment(new OutsideFlightFragment(), "پرواز خارجی");
        myViewPagerAdapter.addFragment(new InsideFlightFragment(), "پرواز داخلی");
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(4);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main_parent);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        txtNavigationEmail = (TextView) findViewById(R.id.txt_navigation_email);
        recyclerViewMenu = (RecyclerView) findViewById(R.id.rv_navigation_menu);
        recyclerViewMenu.setLayoutManager(new LinearLayoutManager(this));
        navigationView.setNavigationItemSelectedListener(this);
        imgHamberMenu = (ImageView) findViewById(R.id.img_toolbar_hamberMenu);
        imgHamberMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
    }

    private void showSplashFragment() {
        splashFragment = new SplashFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.drawer_main_parent, splashFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main_parent);
        drawer.closeDrawer(Gravity.RIGHT);
        return true;
    }
}
