package com.example.adrom.alibaba.Fragment;


import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.example.adrom.alibaba.BusInformationActivity;
import com.example.adrom.alibaba.R;

public class PayFragment extends Fragment {
    View view;
    WebView webView;
    OnREFIDReceived onREFIDReceived;
    @SuppressLint("JavascriptInterface")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.pay_fragment, container, false);
        setupViews();
        return view;
    }

    private void setupViews() {
        String url = "http://adromsh.ir/alibaba/send.php";
        webView = (WebView) view.findViewById(R.id.wv_pay_webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.addJavascriptInterface(new JavaScriptInterface(),"Android");
        try {
            String postData = "Amount=" + URLEncoder.encode("100", "UTF-8") + "&Description=" + URLEncoder.encode("test", "UTF-8");
            webView.postUrl(url, postData.getBytes());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                if(Build.VERSION.SDK_INT>=19){
                    webView.evaluateJavascript("document.getElementsByTagName('html')[0].innerHTML", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
                            JsonReader jsonReader=new JsonReader(new StringReader(value));
                            jsonReader.setLenient(true);
                            try {
                                if(jsonReader.peek()!= JsonToken.NULL){
                                    if(jsonReader.peek()==JsonToken.STRING){
                                        String html=jsonReader.nextString();
                                        if(html!=null){
                                            String res= Html.fromHtml(html).toString();
                                            Log.i("LOG", "onReceiveValue: "+res);
                                            if(res.contains("REFID")){
                                                Toast.makeText(getContext(), "refid exist", Toast.LENGTH_SHORT).show();
                                                onREFIDReceived.onReceive(res);
                                            }
                                        }
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                                try {
                                    jsonReader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });

                }else{
                    webView.loadUrl("javascript:window.Android.get(document.getElementsByTagName('html')[0].innerHTML);");
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }

    public void setOnREFIDReceived(OnREFIDReceived onREFIDReceived) {
        this.onREFIDReceived = onREFIDReceived;
    }

    public class JavaScriptInterface {
        @JavascriptInterface
        public void get(String html) {
            String res= Html.fromHtml(html).toString();
            onREFIDReceived.onReceive(res);
        }
    }

    public interface OnREFIDReceived{
        void onReceive(String result);
    }
}
