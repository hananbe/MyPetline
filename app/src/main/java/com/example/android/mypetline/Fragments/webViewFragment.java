package com.example.android.mypetline.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.android.mypetline.R;

public class webViewFragment  extends Fragment {
  private WebView mWebView;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v= inflater.inflate(R.layout.web_view_activity, parent, false);
        mWebView =(WebView) v.findViewById(R.id.rssWebView);
        String url= "https://www.google.com";
        if (getArguments() != null)
            url = getArguments().getString("url");

        mWebView.loadUrl(url);

        WebSettings webSettings=mWebView.getSettings();

        mWebView.setWebViewClient(new WebViewClient());

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}