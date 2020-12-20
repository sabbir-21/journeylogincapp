package com.journeyloginc.invisibleapps.ui.blogs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.journeyloginc.invisibleapps.R;
import com.journeyloginc.invisibleapps.ui.blogs.BlogsViewModel;

public class BlogsFragment extends Fragment {

    private BlogsViewModel blogsViewModel;
    //webview
    public WebView mWebView;
    private SwipeRefreshLayout swipeRefreshLayout;
    //custom error
    RelativeLayout relativeLayout;
    Button button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        mWebView = (WebView) v.findViewById(R.id.webview_home);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe);
        relativeLayout = (RelativeLayout) v.findViewById(R.id.no_internet_layout);
        button = (Button) v.findViewById(R.id.retry);
        mWebView.loadUrl("https://journeyloginc.com/blog");

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
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
            //swipe after refresh function
            public void onPageFinished(WebView mWebView, String url){
                swipeRefreshLayout.setRefreshing(false);
            } //swipe after..
            //on view page error

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                internetCheck();
                super.onReceivedError(view, request, error);
            }
        });
        internetCheck();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internetCheck();
            }
        });
        return v;
    }
    // custom error

    //custom error ends

    /*
    @Override
    public void onBackPressed(){

        if (webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }
     */
    //check internet connection
    public void internetCheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobiledata = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if(mobiledata.isConnected()){
            mWebView.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            mWebView.reload();

        }else if(wifi.isConnected()){
            mWebView.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            mWebView.reload();
        }else{
            mWebView.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            mWebView.reload();
        }

    } //end internet check
}