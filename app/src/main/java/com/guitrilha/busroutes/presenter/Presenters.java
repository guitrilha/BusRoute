package com.guitrilha.busroutes.presenter;

import android.content.Context;

import com.guitrilha.busroutes.presenter.exception.PresenterInstantiationException;

import java.util.HashMap;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class Presenters {

    private final HashMap<Class<? extends Presenter>, Class<? extends Presenter>> mPresentersMap;
    private Context mApplicationContext;

    public Presenters() {
        this.mPresentersMap = new HashMap<>();
    }

    public void setApplicationContext(Context applicationContext) {
        mApplicationContext = applicationContext;
    }

    public <T extends Presenter> void put(Class<T> abstractClazz, Class<? extends Presenter> concreteClazz) {
        mPresentersMap.put(abstractClazz, concreteClazz);
    }

    public <T extends Presenter> T getPresenter(Class<T> abstractClazz) {
        Class<T> concreteClazz = (Class<T>) mPresentersMap.get(abstractClazz);
        if (concreteClazz == null)
            throw new IllegalStateException("Need to configure a presenter for:" + abstractClazz.getName().toString());
        return tryInstantiatePresenter(concreteClazz);
    }

    private <T extends Presenter> T tryInstantiatePresenter(Class<T> concreteClazz) throws PresenterInstantiationException {
        try{
            return instantiatePresenter(concreteClazz);
        }catch (InstantiationException e) {
            throw new PresenterInstantiationException(concreteClazz.getName()+" must have an empty constructor", e);
        } catch (IllegalAccessException e) {
            throw new PresenterInstantiationException(concreteClazz.getName()+" must have an public empty constructor", e);
        }
    }

    private <T extends Presenter> T instantiatePresenter(Class<T> concreteClazz) throws IllegalAccessException, InstantiationException {
        T presenterInstance = concreteClazz.newInstance();
        presenterInstance.setApplicationContext(mApplicationContext);
        return presenterInstance;
    }
}
