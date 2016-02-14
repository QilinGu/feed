package com.codepath.kpu.feed.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.codepath.kpu.feed.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NFArticleActivity extends AppCompatActivity {

    @Bind(R.id.wvArticle)
    WebView wvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hook up views with ButterKnife
        ButterKnife.bind(this);

        String url = getIntent().getStringExtra("url");
        wvArticle.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvArticle.loadUrl(url);
    }

}
