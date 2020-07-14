 package com.example.adrom.alibaba;

 import android.support.v4.app.Fragment;
 import android.support.v4.app.FragmentManager;
 import android.support.v4.app.FragmentTransaction;
 import android.support.v4.content.res.ResourcesCompat;
 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.ImageView;
 import com.shuhart.stepview.StepView;
 import java.util.ArrayList;
 import com.example.adrom.alibaba.Fragment.BusInformationFragment;
 import com.example.adrom.alibaba.Fragment.ChairFragment;
 import com.example.adrom.alibaba.Fragment.PassengersFragment;
 import com.example.adrom.alibaba.Fragment.PayFragment;
 import com.example.adrom.alibaba.Fragment.PrintTicketFragment;
 import com.example.adrom.alibaba.Model.Chair;

 public class ChairActivity extends AppCompatActivity {

     StepView stepView;
     public static ArrayList<Chair> chairs;
     ImageView imgBack;
     String price;
     FragmentManager fragmentManager;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_chair);

         chairs = getIntent().getExtras().getParcelableArrayList("chairs");
         price = getIntent().getExtras().getString("price");

         stepView = (StepView) findViewById(R.id.step_view);
         stepView.getState()
                 .steps(new ArrayList<String>() {{
                     add("انتخاب صندلی");
                     add("افزودن مسافر");
                     add("تایید اطلاعات بلیط");
                     add("پرداخت");
                     add("چاپ بلیط");
                 }})
                 .stepsNumber(5)
                 .animationDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime))
                 .stepLineWidth(getResources().getDimensionPixelSize(R.dimen.dp1))
                 .textSize(getResources().getDimensionPixelSize(R.dimen.sp14))
                 .stepNumberTextSize(getResources().getDimensionPixelSize(R.dimen.sp16))
                 .typeface(ResourcesCompat.getFont(ChairActivity.this, R.font.font))
                 .commit();


         setupViews();
         showFragment();


     }

     private void showFragment() {

         final FragmentTransaction transaction = fragmentManager.beginTransaction();
         final ChairFragment chairFragment = new ChairFragment();


         chairFragment.setOnSubmitClicked(new ChairFragment.OnSubmitClicked() {
             @Override
             public void onClicked(final String number,final String price) {
                 FragmentTransaction addTransaction = fragmentManager.beginTransaction();
                 final PassengersFragment passengersFragment=new PassengersFragment();
                 final Bundle bundle=new Bundle();
                 bundle.putString("number",number);
                 bundle.putString("price",price);

                 passengersFragment.setOnPassengerNameReceive(new PassengersFragment.OnPassengerNameReceive() {
                     @Override
                     public void onNameReceive(String name) {
                         FragmentTransaction informationTransaction=fragmentManager.beginTransaction();
                         BusInformationFragment busInformationFragment=new BusInformationFragment();


                         busInformationFragment.setOnPayButtonClick(new BusInformationFragment.OnPayButtonClick() {
                             @Override
                             public void onPayClicked(String amount) {
                                 PayFragment payFragment=new PayFragment();
                                 FragmentTransaction payTransaction=fragmentManager.beginTransaction();
                                 payFragment.setOnREFIDReceived(new PayFragment.OnREFIDReceived() {
                                     @Override
                                     public void onReceive(String result) {
                                         FragmentTransaction printTransaction=fragmentManager.beginTransaction();
                                         printTransaction.addToBackStack(null);
                                         PrintTicketFragment printTicketFragment=new PrintTicketFragment();
                                         Bundle printBundle=new Bundle();
                                         bundle.putString("refId",result);
                                         printTicketFragment.setArguments(bundle);
                                         printTransaction.add(R.id.frame_chair_fragments,printTicketFragment);
                                         stepView.go(4,true);
                                         printTransaction.commit();
                                     }
                                 });


                                 payTransaction.add(R.id.frame_chair_fragments,payFragment);
                                 payTransaction.addToBackStack(null);
                                 stepView.go(3,true);
                                 payTransaction.commit();


                             }
                         });





                         informationTransaction.add(R.id.frame_chair_fragments,busInformationFragment);
                         informationTransaction.addToBackStack(null);
                         stepView.go(2,true);
                         informationTransaction.commit();


                     }
                 });



                 passengersFragment.setArguments(bundle);
                 addTransaction.add(R.id.frame_chair_fragments, passengersFragment);
                 addTransaction.addToBackStack(null);
                 addTransaction.commit();
                 stepView.go(1, true);


             }
         });


         Bundle bundle = new Bundle();
         bundle.putString("price", price);
         chairFragment.setArguments(bundle);
         transaction.add(R.id.frame_chair_fragments, chairFragment);
         transaction.commit();
     }

     private void setupViews() {
         fragmentManager = getSupportFragmentManager();
         imgBack = (ImageView) findViewById(R.id.img_chair_back);
         imgBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
     }


     @Override
     public void onBackPressed() {
         Fragment fragment = fragmentManager.findFragmentById(R.id.frame_chair_fragments);
         if (fragment instanceof ChairFragment) {
         } else if (fragment instanceof PassengersFragment) {
             stepView.go(0, true);
         }else if(fragment instanceof BusInformationFragment){
             stepView.go(1,true);
         }
         super.onBackPressed();

     }
 }

