package com.droidmentor.bindingjs;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.droidmentor.bindingjs.Utils.NetworkCheck;
import com.droidmentor.bindingjs.Utils.ProgressUtils;

public class PaymentActivity extends AppCompatActivity {


    WebView webView;

    String embed_link;
    ProgressUtils progressUtils;
    JavaScriptReceiver javaScriptReceiver;

    @SuppressLint("AddJavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        progressUtils = new ProgressUtils(this);

        webView = (WebView) findViewById(R.id.webView);
        embed_link = "http://droidmentor-app-callback.surge.sh/";
        if (TextUtils.isEmpty(embed_link))
            onBackPressed();

        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);


        if (NetworkCheck.isNetworkAvailable(this)) {

            progressUtils.show_dialog(true);
            javaScriptReceiver = new JavaScriptReceiver(this);
            webView.addJavascriptInterface(javaScriptReceiver, "JSReceiver");
            webView.loadUrl(embed_link);
        }

        webView.setWebViewClient(new WebViewClient() {

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String requestUrl) {

                Log.i("loading", "" + requestUrl);
                view.loadUrl(requestUrl);
                return true;
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest req) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                shouldOverrideUrlLoading(view, req.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                Log.i("started", "" + url);

            }

            public void onPageFinished(WebView view, String url) {
                // ////Log.i("TAG", "Finished loading URL: " +url);
                Log.i("finished", "" + url);

                if (progressUtils != null && url.equals(embed_link)) {
                    progressUtils.dismiss_dialog();
                    progressUtils = null;
                }
            }

            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e("TAG", "Error: " + description);
                //Log.i("error", "" + failingUrl);

                if (progressUtils != null) {
                    progressUtils.dismiss_dialog();
                    progressUtils = null;
                    Toast.makeText(PaymentActivity.this, " Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of the WebView
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state of the WebView
        webView.restoreState(savedInstanceState);
    }


}
