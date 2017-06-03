package scalol.com.scalolclient.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;

/**
 * Created by durza9390 on 03.06.2017.
 */

public class Alerts {
    public static AlertDialog createDialog(AlertDialog.Builder builder, int message, int title){
        builder.setMessage(message).setTitle(title);
        return builder.create();
    }

    public static ProgressDialog createWaitingCircle(Activity a, String message, int title){
        ProgressDialog pd = new ProgressDialog(a);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle(title);
        pd.setMessage(message);
        pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));
        pd.setIndeterminate(true);
        return pd;
    }
}
