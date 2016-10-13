package com.guitrilha.busroutes.view.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.view.adapter.holder.RouteListHolder;
import com.guitrilha.busroutes.view.util.DateFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.ViewHolder> {

    private RouteListHolder mRouteListHolder;
    private List<Route> mRouteList;
    private Context mContext;

    public RouteListAdapter(Context context, RouteListHolder routeListHolder) {
        mRouteList = new ArrayList<>();
        mContext = context;
        mRouteListHolder = routeListHolder;
    }

    public void setRouteList(List<Route> routeList) {
        if (routeList != null) {
            mRouteList = routeList;
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Route route = mRouteList.get(position);
        holder.fillIn(route);
    }

    @Override
    public int getItemCount() {
        return mRouteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mShortName;
        private TextView mLongName;
        private TextView mLastModifiedDate;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mShortName = (TextView) view.findViewById(R.id.Route_shortName);
            mLongName = (TextView) view.findViewById(R.id.Route_longName);
            mLastModifiedDate = (TextView) view.findViewById(R.id.Route_lastModifiedDate);
        }

        public void fillIn(Route route) {
            mShortName.setText(route.shortName);
            mLongName.setText(route.longName);
            DateFormatter dateFormatter = new DateFormatter(mContext);
            mLastModifiedDate.setText(mContext.getString(R.string.route_lastModifiedDate, dateFormatter.format(route.lastModifiedDate)));
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.route_item:
                    Route currentRoute = mRouteList.get(getAdapterPosition());
                    mRouteListHolder.onRouteClicked(currentRoute);
                    break;
            }
        }
    }
}
