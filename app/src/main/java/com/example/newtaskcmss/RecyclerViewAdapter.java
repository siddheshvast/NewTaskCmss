package com.example.newtaskcmss;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newtaskcmss.models.Nearbyplaces;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private final Nearbyplaces nearbyplaces;


    private final Context context;


    private final Double latitude;
    private final Double longitude;

    double unit ;


    public RecyclerViewAdapter(Nearbyplaces nearbyplaces, Context context, Double latitude, Double longitude) {
        this.nearbyplaces = nearbyplaces;
        this.context = context;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list_row, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        Location startPoint = new Location("locationA");
//        startPoint.setLatitude(latitude);
//        startPoint.setLongitude(longitude);
//
//        Location endPoint = new Location("locationB");
//        endPoint.setLatitude(nearbyplaces.getResults().get(position).getPosition().getLat());
//        endPoint.setLongitude(nearbyplaces.getResults().get(position).getPosition().getLon());
//
//        double distance = startPoint.distanceTo(endPoint) / 1000;
//
//        @SuppressLint("DefaultLocale") String sValue = (String) String.format("%.2f", distance);
//        Double newValue = Double.parseDouble(sValue);



        double theta = nearbyplaces.getResults().get(0).getPosition().getLon() - nearbyplaces.getResults().get(1).getPosition().getLon();
        double dist = Math.sin(deg2rad(nearbyplaces.getResults().get(0).getPosition().getLat()))
                * Math.sin(deg2rad(nearbyplaces.getResults().get(1).getPosition().getLat()))
                + Math.cos(deg2rad(nearbyplaces.getResults().get(0).getPosition().getLat()))
                * Math.cos(deg2rad(nearbyplaces.getResults().get(1).getPosition().getLat()))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        unit = dist * 1.609344;


//        if (String == "KM") {
//            dist = dist * 1.609344;
//        } else if (unit == "N") {
//            dist = dist * 0.8684;
//        }


//        double theta = 19.33955 - 19.28212;
//        double dist = Math.sin(deg2rad(72.79198)) * Math.sin(deg2rad(72.7864)) + Math.cos(deg2rad(72.79198)) * Math.cos(deg2rad(72.7864)) * Math.cos(deg2rad(theta));
//        dist = Math.acos(dist);
//        dist = deg2rad(dist);
//        dist = dist * 60 * 1.1515;
//        unit = dist * 1.609344;






        holder.txtStoreName.setText("Point Name :- " + nearbyplaces.getResults().get(position).getPoi().getName());
        holder.txtStoreAddr.setText("Longitude :- " + nearbyplaces.getResults().get(position).getPosition().getLat().toString() + "\n" + "Latitude :- " + nearbyplaces.getResults().get(position).getPosition().getLon().toString());
        holder.txtStoreDist.setText("Distance between them   :- " + unit + " (KM) ");

        Log.d("TAG", "lon0: "+nearbyplaces.getResults().get(0).getPosition().getLon());
        Log.d("TAG", "lon1: "+nearbyplaces.getResults().get(1).getPosition().getLon());

    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    public int getItemCount() {
//        if (nearbyplaces != null) {
//            if (nearbyplaces.getResults().size() > 2) {
//                return 2;
//            }
//        }
//        return 0;

        if (nearbyplaces != null) {
            if (nearbyplaces.getResults().size() > 2)
                return 2;
            return nearbyplaces.getResults().size();
        }
        return 0;


    }

//    private double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
//
//        double theta = nearbyplaces.getResults().get(position).getPosition().getLon() - nearbyplaces.getResults().get(position).getPosition().getLon();
//        double dist = Math.sin(deg2rad(nearbyplaces.getResults().get(position).getPosition().getLat())) * Math.sin(deg2rad(nearbyplaces.getResults().get(position).getPosition().getLat())) + Math.cos(deg2rad(nearbyplaces.getResults().get(position).getPosition().getLat())) * Math.cos(deg2rad(nearbyplaces.getResults().get(position).getPosition().getLat())) * Math.cos(deg2rad(theta));
//        dist = Math.acos(dist);
//        dist = deg2rad(dist);
//        dist = dist * 60 * 1.1515;
//        if (unit == "K") {
//            dist = dist * 1.609344;
//        } else if (unit == "N") {
//            dist = dist * 0.8684;
//        }
//        return (dist);
//
//
//
//    }



    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txtStoreName;
        TextView txtStoreAddr;
        TextView txtStoreDist;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.txtStoreDist = itemView.findViewById(R.id.txtStoreDist);
            this.txtStoreName = itemView.findViewById(R.id.txtStoreName);
            this.txtStoreAddr = itemView.findViewById(R.id.txtStoreAddr);


        }


    }

}

