package com.marcoscg.licenser

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Base64
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.marcoscg.licenser.Utils.dpToPx

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */
class LicenserDialog(val context: Context?, @StyleRes dialogTheme: Int = 0) : Licenser() {

    private var alertDialogBuilder: AlertDialog.Builder? = null
    private var alertDialog: AlertDialog? = null
    private var webView: WebView? = null

    fun setTitle(title: CharSequence?): LicenserDialog {
        alertDialogBuilder?.setTitle(title)
        return this
    }

    fun setTitle(@StringRes title: Int): LicenserDialog {
        setTitle(context?.resources?.getString(title))
        return this
    }

    fun setCancelable(cancelable: Boolean): LicenserDialog {
        alertDialogBuilder?.setCancelable(cancelable)
        return this
    }

    override fun setBackgroundColor(backgroundColor: Int): LicenserDialog {
        super.setBackgroundColor(backgroundColor)
        return this
    }

    fun setPositiveButton(text: CharSequence?, onClickListener: DialogInterface.OnClickListener?): LicenserDialog {
        alertDialogBuilder?.setPositiveButton(text, onClickListener)
        return this
    }

    fun setPositiveButton(@StringRes text: Int, onClickListener: DialogInterface.OnClickListener?): LicenserDialog {
        setPositiveButton(context?.resources?.getString(text), onClickListener)
        return this
    }

    fun setNegativeButton(text: CharSequence?, onClickListener: DialogInterface.OnClickListener?): LicenserDialog {
        alertDialogBuilder?.setNegativeButton(text, onClickListener)
        return this
    }

    fun setNegativeButton(@StringRes text: Int, onClickListener: DialogInterface.OnClickListener?): LicenserDialog {
        setNegativeButton(context?.resources?.getString(text), onClickListener)
        return this
    }

    fun setNeutralButton(text: CharSequence?, onClickListener: DialogInterface.OnClickListener?): LicenserDialog {
        alertDialogBuilder?.setNeutralButton(text, onClickListener)
        return this
    }

    fun setNeutralButton(@StringRes text: Int, onClickListener: DialogInterface.OnClickListener?): LicenserDialog {
        setNeutralButton(context?.resources?.getString(text), onClickListener)
        return this
    }

    override fun setLibrary(library: Library): LicenserDialog {
        super.setLibrary(library)
        return this
    }

    override fun setCustomNoticeTitle(noticeTitle: String): LicenserDialog {
        super.setCustomNoticeTitle(noticeTitle)
        return this
    }

    fun setCustomNoticeTitle(@StringRes noticeTitleRes: Int): LicenserDialog {
        super.setCustomNoticeTitle(context?.resources?.getString(noticeTitleRes).orEmpty())
        return this
    }

    fun show() {
        if (webView?.url == null) {
            webView?.loadData(Base64.encodeToString(getDialogHTMLContent(context).toByteArray(), Base64.NO_PADDING),
                    "text/html; charset=UTF-8", "base64")
        }

        if (alertDialog == null)
            alertDialog = alertDialogBuilder?.create()

        alertDialog?.show()
    }

    fun dismiss() {
        alertDialog?.dismiss()
    }

    init {
        when (context as Activity?) {
            is AppCompatActivity -> {
                alertDialogBuilder = AlertDialog.Builder(context!!, dialogTheme) // Context should not be null here
                webView = WebView(context)
                val container = LinearLayout(context)

                container.orientation = LinearLayout.VERTICAL
                val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                layoutParams.setMargins(0, dpToPx(16), 0, 0)

                webView?.settings?.useWideViewPort = true
                webView?.settings?.loadWithOverviewMode = true

                if (Build.VERSION.SDK_INT < 19)
                    webView?.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                else webView?.setLayerType(View.LAYER_TYPE_HARDWARE, null)

                if (Build.VERSION.SDK_INT >= 21)
                    webView?.settings?.mixedContentMode = 0

                webView?.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                        return if (url.startsWith("http://") || url.startsWith("https://")) {
                            view.context.startActivity(
                                    Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                            true
                        } else {
                            false
                        }
                    }
                }

                container.addView(webView, layoutParams)
                alertDialogBuilder?.setView(container)
            }
            null -> throw NullPointerException("There is no activity attached to context")
            else -> throw NullPointerException("You need to use an AppCompatActivity")
        }
    }
}