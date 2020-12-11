package com.tollywood2bollywood.t2bliveapp.Other;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by pooja on 18-03-2018.
 */

public class Popup {
    private ProgressDialog pDialog;
    private Context context;

    public Popup(Context context) {
        this.context = context;
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setMessage("Please wait...");
    }

    public ProgressDialog getpDialog() {
        return pDialog;
    }

    public void setpDialog(ProgressDialog pDialog) {
        this.pDialog = pDialog;
    }

    public void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    public void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
