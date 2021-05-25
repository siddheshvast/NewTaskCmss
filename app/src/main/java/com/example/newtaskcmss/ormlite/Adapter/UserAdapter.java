package com.example.newtaskcmss.ormlite.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newtaskcmss.R;
import com.example.newtaskcmss.ormlite.UserItemDB;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<UserItemDB> userItemDBList;
    private Context mContext;


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView name, index;

        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txtStoreName1);
            //age = (TextView) view.findViewById(R.id.txtStoreAddr1);
            index = (TextView) view.findViewById(R.id.txtStoreDist1);

        }
    }


    public UserAdapter(List<UserItemDB> userItemDBList, Context context) {

        this.userItemDBList = userItemDBList;
        this.mContext = context;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_row, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        final UserItemDB user = userItemDBList.get(position);

        holder.name.setText(user.getName());
        holder.index.setText(user.getIndex());
       // holder.age.setText(user.getAge());





    }

    public void updateUserList(List<UserItemDB> newlist) {
        userItemDBList.clear();
        userItemDBList.addAll(newlist);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userItemDBList.size();
    }






}
