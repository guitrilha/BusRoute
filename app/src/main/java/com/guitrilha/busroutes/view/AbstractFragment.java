package com.guitrilha.busroutes.view;

import android.os.Bundle;
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
        onLayoutInflate();
        callPresenterOnCreate(savedInstanceState);
        if(savedInstanceState!=null){
            mPresenter.onRestoreInstanceState(savedInstanceState);
        }
    }

    protected void onLayoutInflate() {

    }

    private void callPresenterOnCreate(Bundle savedInstanceState) {
        Bundle extras = getArguments();
        if (extras != null) {
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

    protected abstract Class<T> getPresenterClazz();

    private void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    public T getPresenter() {
        return mPresenter;
    }
}
