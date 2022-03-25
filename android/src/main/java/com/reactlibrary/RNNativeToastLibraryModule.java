
package com.reactlibrary;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.reactlibrary.view.*;

public class RNNativeToastLibraryModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  Promise promise;

  public RNNativeToastLibraryModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    getReactApplicationContext().addActivityEventListener(mActivityEventListener);
  }

  private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {
      @Override
      public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
          Log.e("RESULT CAME HERE","SUCCESS");
          try{
            String responsedata = data.getStringExtra("result");
            promise.resolve(responsedata);
          }
          catch(Exception e){}
      }
  };

  @ReactMethod
  public void startPayment(String paymenturl, Promise promise) {
        this.promise = promise;
         Log.e("Passed variable",""+name+" "+location);
        Intent intent = (new Intent(getReactApplicationContext(), com.reactlibrary.view.WebViewActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).putExtra("url",""+paymenturl));
        getReactApplicationContext().getCurrentActivity().startActivityForResult(intent, 101);
  }

  @Override
  public String getName() {
    return "RNNativeToastLibrary";
  }
}