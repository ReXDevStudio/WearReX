package cn.rexwear.wearrex.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import cn.rexwear.wearrex.R;

/**
 * Created by XC-Qan on 2022/5/23.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class DialogUtils {
    @SuppressLint("StaticFieldLeak")
    private static DialogUtils instance;
    Context context;

    public DialogUtils(Context context) {
        this.context = context;
    }

    public static DialogUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DialogUtils(context);
        }
        return instance;
    }

    /**
     * @return alertDialog对话框Object
     */
    public AlertDialog LoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = View.inflate(context, R.layout.loading_dialog, null);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        return alertDialog;
    }
}
