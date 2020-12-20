package com.journeyloginc.invisibleapps.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.journeyloginc.invisibleapps.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    //webview
    public WebView mWebView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview_home);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe);
        mWebView.loadUrl("https://journeyloginc.com");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        // on refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mWebView.reload();
            }
        });
        //on refresh end

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView mWebView, String url){
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return v;
    }
}