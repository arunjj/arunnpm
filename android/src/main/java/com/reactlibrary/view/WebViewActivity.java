
package com.reactlibrary.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.reactlibrary.R;

import org.json.JSONException;
import org.json.JSONObject;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView wv1 = (WebView) findViewById(R.id.webView);
//        wv1.setWebViewClient(new MyBrowser());

       // wv1.getSettings().setLoadsImagesAutomatically(true);
        wv1.getSettings().setJavaScriptEnabled(true);
        //wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wv1.loadUrl(getIntent().getStringExtra("url"));
        wv1.addJavascriptInterface(new WebAppInterface(this), "HTMLOUT");
         wv1.setWebViewClient(new WebViewClient() {
             @Override
             public boolean shouldOverrideUrlLoading(WebView view, String url) {
                 view.loadUrl(url);
                 return true;
             }
             @Override
             public void onPageStarted(WebView view, String url, Bitmap favicon) {
                 super.onPageStarted(view, url, favicon);

             }
             @Override
             public void onPageFinished(WebView view, String url) {
                 super.onPageFinished(view, url);
                 Log.e("URL","URL TESTING "+url);
                 view.loadUrl("javascript:window.HTMLOUT.processData(document.getElementById('result').value);");            }

             @Override
             public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                 Toast.makeText(getApplicationContext(), "Oh no! "+description , Toast.LENGTH_SHORT).show();
                 super.onReceivedError(view, errorCode, description,failingUrl);
             }
         });
    }

    public void tetsme(View view) {
        Intent intentWithResult = new Intent();
        intentWithResult.putExtra("result", "Welcome");
        setResult(Activity.RESULT_OK, intentWithResult);
    }

    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface   // must be added for API 17 or higher
        public void processData(String result) throws JSONException {
            Log.d("Payment Response-->>>", "...>>>>>"+result);
            //JSONObject object = new JSONObject(toast);
            Intent intentWithResult = new Intent();
            intentWithResult.putExtra("result", result);
            setResult(Activity.RESULT_OK, intentWithResult);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            sendErrorMessage("ERROR_USER_CANCELLED","User Cancelled Transaction");
        } catch (Exception ex) {
            sendErrorMessage("ERROR_GENERAL",ex.getMessage());
        }
    }

    public void sendErrorMessage(final String errorType, final String errorMsg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(WebViewActivity.this);
        builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e("Error Message","Testing");
                        Intent intentWithResult= new Intent();
                        intentWithResult.putExtra("ErrorType",errorType);
                        intentWithResult.putExtra("ErrorMsg",errorMsg);
                        setResult(Activity.RESULT_CANCELED,intentWithResult);
                        dialog.dismiss();
                        finish();

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}