package com.guitrilha.busroutes.presenter.exception;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class PresenterInstantiationException extends RuntimeException {

    public PresenterInstantiationException() {
        super();
    }

    public PresenterInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
