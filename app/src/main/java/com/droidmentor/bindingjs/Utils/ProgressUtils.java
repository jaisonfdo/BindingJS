package com.droidmentor.bindingjs.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ProgressBar;

import com.droidmentor.bindingjs.R;


/**
 * Created by Jaison
 */

public class ProgressUtils {

    Context context;
    private ProgressDialog progress_dialog;

    public ProgressUtils(Context context) {
        this.context=context;
    }

    public static ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.progressdialog);
        // dialog.setMessage(Message);
        return dialog;
    }

    public void show_dialog(boolean isCancelable)
    {
        progress_dialog = ProgressDialog.show(context, null, null, true, isCancelable);
        progress_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress_dialog.setContentView(R.layout.progressdialog);
        ProgressBar progressbar=(ProgressBar)progress_dialog.findViewById(R.id.progressBar1);
        progress_dialog.setCancelable(isCancelable);
        progressbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#3F51B5"), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    public void dismiss_dialog()
    {
        if(progress_dialog!=null&& progress_dialog.isShowing())
            progress_dialog.dismiss();
    }

}
