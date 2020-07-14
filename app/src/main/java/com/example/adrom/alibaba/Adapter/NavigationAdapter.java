package com.example.adrom.alibaba.Adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.adrom.alibaba.Fragment.LoginDialog;
import com.example.adrom.alibaba.MainActivity;
import com.example.adrom.alibaba.Model.NavigationModel;
import com.example.adrom.alibaba.ProfileActivity;
import com.example.adrom.alibaba.R;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationViewHolder> {
    private Context context;
    private List<NavigationModel> models;
    OnDialogDismissed onDialogDismissed;
    SharedPreferences sharedPreferences;

    public NavigationAdapter(Context context, List<NavigationModel> models) {
        this.models = models;
        this.context = context;
        sharedPreferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public NavigationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_navigation_menu, viewGroup, false);
        return new NavigationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NavigationViewHolder navigationViewHolder, int i) {
        NavigationModel model = models.get(i);
        navigationViewHolder.txtTitle.setText(model.getTitle());
        navigationViewHolder.imgIcon.setImageResource(model.getDrawable());
        navigationViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (navigationViewHolder.txtTitle.getText().equals("پروفایل کاربری")) {
                    String email=sharedPreferences.getString("email","");
                    if (email.equals("")) {
                        LoginDialog loginDialog = new LoginDialog();
                        loginDialog.show(MainActivity.fragmentManager, null);
                        loginDialog.setOnSignupClicked(new LoginDialog.OnSignupClicked() {
                            @Override
                            public void onClicked(String email) {
                                onDialogDismissed.onDismissed(email);
                            }
                        });
                    } else {
                        Intent intent=new Intent(context, ProfileActivity.class);
                        intent.putExtra("email",email);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void setOnDialogDismissed(OnDialogDismissed onDialogDismissed) {
        this.onDialogDismissed = onDialogDismissed;
    }

    public class NavigationViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        ImageView imgIcon;
        ConstraintLayout parent;

        public NavigationViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_navigationItem_title);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_navigationItem_icon);
            parent = (ConstraintLayout) itemView.findViewById(R.id.cl_navigationItem_parent);
        }
    }

    public interface OnDialogDismissed {
        void onDismissed(String email);
    }
}
