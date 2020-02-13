package com.marcoscg.licenser;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */

public class LicenserDialog extends Licenser {

    private Context context;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog alertDialog;
    private WebView webView;

    public LicenserDialog(Context context) {
        this(context, 0);
    }

    public LicenserDialog(Context context, @StyleRes int dialogTheme) {
        Activity activity = (Activity) context;
        if (activity == null)
            throw new NullPointerException("There is no activity attached to context");
        else if (activity instanceof AppCompatActivity) {
            this.context = context;

            alertDialogBuilder = new AlertDialog.Builder(context, dialogTheme);
            webView = new WebView(context);
            LinearLayout container = new LinearLayout(context);

            container.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,Utils.dpToPx(16),0,0);

            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            if (Build.VERSION.SDK_INT < 19)
                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            else webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            if (Build.VERSION.SDK_INT >= 21)
                webView.getSettings().setMixedContentMode(0);
            webView.setWebViewClient(new WebViewClient(){
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url != null && url.startsWith("http://") || url != null && url.startsWith("https://")) {
                        view.getContext().startActivity(
                                new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            container.addView(webView, layoutParams);
            alertDialogBuilder.setView(container);
        } else throw new NullPointerException("You need to use an AppCompatActivity");
    }

    public LicenserDialog setTitle(CharSequence title) {
        alertDialogBuilder.setTitle(title);
        return this;
    }

    public LicenserDialog setTitle(@StringRes int title) {
        setTitle(context.getResources().getString(title));
        return this;
    }

    public LicenserDialog setCancelable(boolean cancelable) {
        alertDialogBuilder.setCancelable(cancelable);
        return this;
    }

    public LicenserDialog setBackgroundColor(int backgroundColor) {
        super.setBackgroundColor(backgroundColor);

        return this;
    }

    public LicenserDialog setPositiveButton(CharSequence text, DialogInterface.OnClickListener onClickListener) {
        alertDialogBuilder.setPositiveButton(text, onClickListener);
        return this;
    }

    public LicenserDialog setPositiveButton(@StringRes int text, DialogInterface.OnClickListener onClickListener) {
        setPositiveButton(context.getResources().getString(text), onClickListener);
        return this;
    }

    public LicenserDialog setNegativeButton(CharSequence text, DialogInterface.OnClickListener onClickListener) {
        alertDialogBuilder.setNegativeButton(text, onClickListener);
        return this;
    }

    public LicenserDialog setNegativeButton(@StringRes int text, DialogInterface.OnClickListener onClickListener) {
        setNegativeButton(context.getResources().getString(text), onClickListener);
        return this;
    }

    public LicenserDialog setNeutralButton(CharSequence text, DialogInterface.OnClickListener onClickListener) {
        alertDialogBuilder.setNeutralButton(text, onClickListener);
        return this;
    }

    public LicenserDialog setNeutralButton(@StringRes int text, DialogInterface.OnClickListener onClickListener) {
        setNeutralButton(context.getResources().getString(text), onClickListener);
        return this;
    }

    public LicenserDialog setLibrary(Library library) {
        super.setLibrary(library);
        return this;
    }

    public LicenserDialog setCustomNoticeTitle(String noticeTitle) {
        super.setCustomNoticeTitle(noticeTitle);
        return this;
    }

    public LicenserDialog setCustomNoticeTitle(@StringRes int noticeTitleRes) {
        super.setCustomNoticeTitle(context.getResources().getString(noticeTitleRes));
        return this;
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public void show() {
        if (webView.getUrl() == null)
            webView.loadData(getHTMLContent(context), "text/html; charset=UTF-8", null);

        if (alertDialog == null)
            alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void dismiss() {
        alertDialog.dismiss();
    }

}
