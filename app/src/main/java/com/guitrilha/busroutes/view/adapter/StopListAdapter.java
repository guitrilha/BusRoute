package com.guitrilha.busroutes.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.model.entity.Stop;
import com.guitrilha.busroutes.view.adapter.holder.RouteListHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class StopListAdapter extends RecyclerView.Adapter<StopListAdapter.ViewHolder> {

    private List<Stop> mStopList;
    private Context mContext;

    public StopListAdapter(Context context) {
        mStopList = new ArrayList<>();
        mContext = context;
    }

    public void setStopList(List<Stop> stopList) {
        if (stopList != null) {
            mStopList = stopList;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stop_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stop stop = mStopList.get(position);
        holder.fillIn(stop);
    }

    @Override
    public int getItemCount() {
        return mStopList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mSequence;
        private TextView mName;

        public ViewHolder(View view) {
            super(view);
            mSequence = (TextView) view.findViewById(R.id.Stop_sequence);
            mName = (TextView) view.findViewById(R.id.Stop_name);
        }

        public void fillIn(Stop stop) {
            mSequence.setText(mContext.getString(R.string.stop_list_sequence, stop.sequence));
            mName.setText(stop.name);
        }
    }
}
