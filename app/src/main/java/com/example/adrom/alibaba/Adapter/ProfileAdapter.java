package com.example.adrom.alibaba.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrom.alibaba.Model.Profile;
import com.example.adrom.alibaba.R;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {

    private List<Profile> profiles;
    private Context context;
    public ProfileAdapter( List<Profile> profiles ) {
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.profile_item,viewGroup,false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder profileViewHolder, int i) {
        int row = i+1;
        if( row % 2 == 0 ){
            profileViewHolder.parent.setBackgroundColor(ContextCompat.getColor(context,R.color.color_gray200));
        }else{
            profileViewHolder.parent.setBackgroundColor(ContextCompat.getColor(context,R.color.color_default));
        }
        Profile profile = profiles.get(i);
        profileViewHolder.txtTitle.setText(profile.getTitle());
        profileViewHolder.txtValue.setText(profile.getValue());
    }

    @Override
    public int getItemCount() {
        return this.profiles.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle,txtValue;
        ConstraintLayout parent;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = (TextView)itemView.findViewById(R.id.txt_profileItem_title);
            txtValue = (TextView)itemView.findViewById(R.id.txt_profileItem_value);
            parent = (ConstraintLayout)itemView.findViewById(R.id.cl_profile_parent);
        }
    }
}
