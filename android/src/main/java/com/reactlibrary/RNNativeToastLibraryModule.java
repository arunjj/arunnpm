
package com.reactlibrary;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableNativeMap;
import com.reactlibrary.view.*;
import com.facebook.react.bridge.WritableMap;

public class RNNativeToastLibraryModule extends ReactContextBaseJavaModule implements ActivityEventListener {

  private final ReactApplicationContext reactContext;
  Promise promise;
  int WEBVIEW_REQUEST_CODE = 1011;
  private String TAG="Payu";

    public RNNativeToastLibraryModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        getReactApplicationContext().addActivityEventListener(this);
      }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "parseActivityResult");
        if (requestCode == WEBVIEW_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED) {
            sendFailure(data.getStringExtra("ErrorType"),data.getStringExtra("ErrorMsg"));
        }else if (requestCode == WEBVIEW_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String result = data.getStringExtra("result");
            promise.resolve(result);
        }
        sendFailure(data.getStringExtra("ErrorType"), data.getStringExtra("ErrorMsg"));
    }

    public void onNewIntent(Intent intent) {
        //Log.e("WHAT NEW INTENT","Testing NEW INTENT");
    }


//    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
//      @Override
//      public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
//          Log.e("RESULT CAME HERE","SUCCESS");
//          try {
//              if (requestCode == WEBVIEW_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED) {
//                  sendFailure(data.getStringExtra("ErrorType"), data.getStringExtra("ErrorMsg"));
//              } else if (requestCode == WEBVIEW_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//                  String result = data.getStringExtra("result");
//                  Log.e("SUCCESS....",""+result);
//                  promise.resolve(result);
//              }
//          }
//          catch (Exception e) {
//
//          }
//      }
//  };


  public void sendFailure(String code, String message) {
        Log.e("Error Message",""+code+" "+message);
        promise.reject(code, message);
  }

  @ReactMethod
  public void startPayment(String paymenturl, Promise promise) {
        this.promise = promise;
         Log.e("Passed variable",""+paymenturl);
         Activity activity = getReactApplicationContext().getCurrentActivity();
         Intent intent = (new Intent(activity, WebViewActivity.class).putExtra("url",""+paymenturl).setFlags(0));
         activity.startActivityForResult(intent, WEBVIEW_REQUEST_CODE);
  }

  @Override
  public String getName() {
    return "RNNativeToastLibrary";
  }

//    @Override
//    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
//      try {
//              if (requestCode == WEBVIEW_REQUEST_CODE && resultCode == Activity.RESULT_CANCELED) {
//                  sendFailure(data.getStringExtra("ErrorType"), data.getStringExtra("ErrorMsg"));
//              } else if (requestCode == WEBVIEW_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//                  String result = data.getStringExtra("result");
//                  Log.e("SUCCESS....",""+result);
//                  promise.resolve(result);
//              }
//          }
//          catch (Exception e) {
//
//          }
//    }
//
//    @Override
//    public void onNewIntent(Intent data) {
//        try {
//
//                String result = data.getStringExtra("result");
//                Log.e("SUCCESS....!!!!!!!",""+result);
//                promise.resolve(result);
//
//        }
//        catch (Exception e) {
//
//        }
//    }
}