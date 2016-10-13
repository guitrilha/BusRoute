package com.guitrilha.busroutes.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.guitrilha.busroutes.Mvp;
import com.guitrilha.busroutes.presenter.Presenter;
import com.guitrilha.busroutes.presenter.Presenters;

/**
 * Created by Guilherme on 08/10/2016.
 */

public abstract class AbstractFragment<T extends Presenter> extends Fragment implements View<T> {

    private T mPresenter;

    public AbstractFragment() {
        injectPresenter();
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutToInflate = getLayoutToInflate();
        android.view.View view = inflater.inflate(layoutToInflate, container, false);
        return view;
    }

    protected abstract int getLayoutToInflate();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onLayoutInflated();
        callPresenterOnCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mPresenter.onRestoreInstanceState(savedInstanceState);
            mPresenter.onAfterStatedRestored();
        }
    }

    protected void onLayoutInflated() {

    }

    private void callPresenterOnCreate(Bundle savedInstanceState) {
        Bundle extras = getArguments();
        if (extras == null) {
            extras = new Bundle();
        }
        mPresenter.onCreate(extras, savedInstanceState);
    }


    private void injectPresenter() {
        Class<T> presenterClazz = getPresenterClazz();
        Mvp mvp = Mvp.getInstance();
        Presenters presenters = mvp.getPresenters();
        T presenter = presenters.getPresenter(presenterClazz);
        setPresenter(presenter);
        presenter.setView(this);

    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null)
            mPresenter.onSaveInstanceState(outState);
    }

    protected abstract Class<T> getPresenterClazz();

    private void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    public T getPresenter() {
        return mPresenter;
    }

    protected void setVisibleOrGone(int viewId, boolean visibleOrGone){
        if(visibleOrGone){
            getActivity().findViewById(viewId).setVisibility(android.view.View.VISIBLE);
        }else{
            getActivity().findViewById(viewId).setVisibility(android.view.View.GONE);
        }

    }
}
