package com.codepath.kpu.feed.network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kpu on 2/14/16.
 */
public class NFArticleProvider {
    final private String BASE_URI = "http://api.nytimes.com/svc/search/v2/articlesearch.json";

    private AsyncHttpClient client;

    public interface NFOnArticlesFetched {
        void onSuccess();
        void onFailure(String errorString);
    }

    public NFArticleProvider() {
        client = new AsyncHttpClient();
    }

    public void fetchArticlesWithQuery(String query, final NFOnArticlesFetched callback) {
        RequestParams params = new RequestParams();
        params.put("api-key", "ce93835d601ae5a1a881f510397ffd0a:5:74407583");
        params.put("page", 0);
        params.put("q", query);

        client.get(BASE_URI, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
