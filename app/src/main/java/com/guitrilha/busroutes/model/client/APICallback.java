package com.guitrilha.busroutes.model.client;

import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class APICallback<T> {

    public List<T> itens;

    public String errorMsg;

    public Exception exception;
}
