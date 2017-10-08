package com.example.sashok.university.activity;

/**
 * Created by sashok on 5.10.17.
 */

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sashok.university.R;
import com.example.sashok.university.helper.DetectConnection;

public class BrowserActivity extends AppCompatActivity {

    private String url;
    private WebView webView;
    private ProgressBar progressBar;
    private TextView title_toolbar;
    private String url_to_search=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browser_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        title_toolbar = (TextView) findViewById(R.id.page_name);
        url = getIntent().getStringExtra("url");


        if (TextUtils.isEmpty(url)) {
            url = "github.com/Sasha9535";
        }

        Uri uri = Uri.parse(url);
        if (uri.getScheme() == null) {
            uri = Uri.parse("http://" + url.replace(' ','&'));
        }


        webView = (WebView) findViewById(R.id.my_web_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        initWebView();
        if (url.indexOf(" ")!=-1){
            webView.loadUrl(("https://www.google.com/search?q=" + url_to_search));
        }
            webView.loadUrl(uri.toString());
    }

    private void initWebView() {

        CustomWebViewClient client = new CustomWebViewClient();
        webView.setWebViewClient(client);
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browser, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (!webView.canGoBack()) {
            menu.getItem(0).setEnabled(false);
            menu.getItem(0).getIcon().setAlpha(130);
        } else {
            menu.getItem(0).setEnabled(true);
            menu.getItem(0).getIcon().setAlpha(255);
        }

        if (!webView.canGoForward()) {
            menu.getItem(1).setEnabled(false);
            menu.getItem(1).getIcon().setAlpha(130);
        } else {
            menu.getItem(1).setEnabled(true);
            menu.getItem(1).getIcon().setAlpha(255);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        if (item.getItemId() == R.id.action_back) {
            back();
        }
        if (item.getItemId() == R.id.action_forward) {
            forward();
        }

        return super.onOptionsItemSelected(item);
    }

    private void back() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }

    private void forward() {
        if (webView.canGoForward()) {
            webView.goForward();
        }
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            title_toolbar.setText(Uri.parse(url).getEncodedAuthority());
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!DetectConnection.checkInternetConnection(getBaseContext())) {
                webView.loadUrl("file:///android_asset/error.html");
            } else {
                webView.loadUrl(url);
            }
            return true;
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            progressBar.setVisibility(View.GONE);
            if (!DetectConnection.checkInternetConnection(getBaseContext())) {
//                StringBuilder sb = new StringBuilder();
//                sb.append("<HTML><HEAD><LINK href=\"style.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
//                sb.append("<img src=\"paris.jpg\" style=\"width:304px;height:228px;    \">");
//                sb.append("</body></HTML>");
                webView.loadUrl("file:///android_asset/error.html");
//                webView.loadDataWithBaseURL("file:///android_asset/", sb.toString(), "text/html", "UTF-8", null);
            } else {
                webView.loadUrl("https://www.google.com/search?q=" + url_to_search);
            }


        }
    }


}