package com.guitrilha.busroutes.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter;
import com.guitrilha.busroutes.view.AbstractActivity;
import com.guitrilha.busroutes.view.contract.DetailRouteView;
import com.guitrilha.busroutes.view.fragment.BaseDetailRouteViewImpl;
import com.guitrilha.busroutes.view.fragment.factory.DetailRouteFragmentFactory;

import static com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter.EXTRA_ROUTE_ID;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class DetailRouteViewImpl extends AbstractActivity<DetailRoutePresenter> implements DetailRouteView {

    @Override
    protected int getLayoutToInflate() {
        return R.layout.activity_detail_route;
    }

    @Override
    protected Class<DetailRoutePresenter> getPresenterClazz() {
        return DetailRoutePresenter.class;
    }

    @Override
    protected void onLayoutInflated() {
        super.onLayoutInflated();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showRouteName(String mRouteName) {
        getSupportActionBar().setTitle(mRouteName);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public BaseDetailRouteViewImpl getItem(int position) {
            BaseDetailRouteViewImpl fragment = DetailRouteFragmentFactory.createFragment(position);
            Bundle bundle = new Bundle();
            bundle.putLong(EXTRA_ROUTE_ID, getPresenter().getRouteId());
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return DetailRouteFragmentFactory.FRAGMENTS_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return DetailRouteFragmentFactory.createTitle(position, DetailRouteViewImpl.this);
        }
    }
}
