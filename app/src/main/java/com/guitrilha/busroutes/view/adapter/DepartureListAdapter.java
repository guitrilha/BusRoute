package com.guitrilha.busroutes.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.model.entity.Stop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class DepartureListAdapter extends RecyclerView.Adapter<DepartureListAdapter.ViewHolder> {

    private List<Departure> mDepartureList;

    public DepartureListAdapter() {
        mDepartureList = new ArrayList<>();
    }

    public void setDepartureList(List<Departure> departureList) {
        if (departureList != null) {
            mDepartureList = departureList;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_departure_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Departure departure = mDepartureList.get(position);
        holder.fillIn(departure);
    }

    @Override
    public int getItemCount() {
        return mDepartureList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTime;

        public ViewHolder(View view) {
            super(view);
            mTime = (TextView) view.findViewById(R.id.Departure_time);
        }

        public void fillIn(Departure departure) {
            mTime.setText(departure.time);
        }
    }
}
