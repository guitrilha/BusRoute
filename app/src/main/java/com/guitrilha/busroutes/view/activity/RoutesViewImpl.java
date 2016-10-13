package com.guitrilha.busroutes.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.presenter.contract.RoutesPresenter;
import com.guitrilha.busroutes.view.AbstractActivity;
import com.guitrilha.busroutes.view.adapter.RouteListAdapter;
import com.guitrilha.busroutes.view.adapter.holder.RouteListHolder;
import com.guitrilha.busroutes.view.contract.RoutesView;

import java.util.List;

import static com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter.EXTRA_ROUTE_ID;
import static com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter.EXTRA_ROUTE_NAME;

public class RoutesViewImpl extends AbstractActivity<RoutesPresenter>
        implements RoutesView, RouteListHolder {

    private RouteListAdapter mRoutesListAdapter;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<RoutesPresenter> getPresenterClazz() {
        return RoutesPresenter.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        handleIntent(getIntent());
    }

    @Override
    protected void onLayoutInflated() {
        super.onLayoutInflated();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_maps);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoutesViewImpl.this, MapsViewImpl.class);
                startActivity(intent);
            }
        });

        mRoutesListAdapter = new RouteListAdapter(RoutesViewImpl.this, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.routes_list);
        recyclerView.setAdapter(mRoutesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RoutesViewImpl.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getPresenter().onSearchRoute(query);
        }
    }

    @Override
    public void showRoutes(List<Route> routes, String query) {
        if (!routes.isEmpty()) {
            setVisibleOrGone(R.id.textview_streetName, true);
            ((TextView) findViewById(R.id.textview_streetName)).setText(query);
            setVisibleOrGone(R.id.routes_list, true);
            mRoutesListAdapter.setRouteList(routes);
        } else {
            setVisibleOrGone(R.id.routes_list, false);
            showNoRoutesFound(query);
        }
    }

    @Override
    public void onSearchRoutesStarted() {
        setVisibleOrGone(R.id.layout_info_msg, false);
        setVisibleOrGone(R.id.textview_streetName, false);
        setVisibleOrGone(R.id.routes_list, false);
        setVisibleOrGone(R.id.progressBar, true);
    }

    @Override
    public void onSearchRoutesFinished() {
        setVisibleOrGone(R.id.progressBar, false);
    }

    private void showNoRoutesFound(String query) {
        showErrorMessage(getString(R.string.error_no_routes_found, query));
    }

    @Override
    public void showUnexpectedError() {
        showErrorMessage(getString(R.string.error_unexpected));
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        setVisibleOrGone(R.id.layout_info_msg, true);
        setVisibleOrGone(R.id.textview_info, false);
        setVisibleOrGone(R.id.textview_error, true);
        TextView errorTextView = (TextView) findViewById(R.id.textview_error);
        errorTextView.setText(errorMsg);
    }

    @Override
    public void onRouteClicked(Route route) {
        Intent intent = new Intent(RoutesViewImpl.this, DetailRouteViewImpl.class);
        intent.putExtra(EXTRA_ROUTE_ID, route.id);
        intent.putExtra(EXTRA_ROUTE_NAME, route.longName);
        startActivity(intent);
    }
}
