package com.guitrilha.busroutes.view.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Guilherme on 10/10/2016.
 */

public class DateFormatter {

    private static final String DEFAULT_FORMAT = "dd/MM/yyyy";

    private SimpleDateFormat simpleDateFormat;
    private Context mContext;

    public DateFormatter(Context context) {
        super();
        mContext = context;
        defineFormatter();
    }

    public String format(Date value) {
        return this.simpleDateFormat.format(value);
    }

    public void defineFormatter() {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mContext);
        if (dateFormat != null) {
            this.simpleDateFormat = ((SimpleDateFormat) dateFormat);
        } else {
            this.simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
        }
    }
}
