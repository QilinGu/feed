package com.codepath.kpu.feed.network;

import android.text.TextUtils;

import com.codepath.kpu.feed.models.NFArticle;
import com.codepath.kpu.feed.models.NFArticlesResponse;
import com.codepath.kpu.feed.models.NFSearchSettingsModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kpu on 2/14/16.
 */
public class NFArticleProvider {
    final private String BASE_URI = "http://api.nytimes.com/svc/search/v2/articlesearch.json";
    final private String API_KEY = "ce93835d601ae5a1a881f510397ffd0a:5:74407583";

    private AsyncHttpClient client;

    public interface NFOnArticlesFetched {
        void onSuccess(List<NFArticle> fetchedArticles);
        void onFailure(String errorString);
    }

    public NFArticleProvider() {
        client = new AsyncHttpClient();
    }

    public void fetchArticles(String query, NFSearchSettingsModel searchSettingsModel, int page, final NFOnArticlesFetched callback) {
        RequestParams params = new RequestParams();
        params.put(NFArticleConstants.NF_REQUEST_PARAM_API_KEY, API_KEY);
        params.put(NFArticleConstants.NF_REQUEST_PARAM_PAGE, String.valueOf(page));
        params.put(NFArticleConstants.NF_REQUEST_PARAM_QUERY, query);
        params.put(NFArticleConstants.NF_REQUEST_PARAM_BEGIN_DATE, searchSettingsModel.getBeginDateString());

        if (searchSettingsModel.sortOrder != NFSearchSettingsModel.SortOrder.DEFAULT) {
            params.put(NFArticleConstants.NF_REQUEST_PARAM_SORT, searchSettingsModel.sortOrder.getParameter());
        }

        List<String> selectedCategories = searchSettingsModel.getSelectedCategories();
        if (selectedCategories.size() > 0) {
            String fqString = String.format("news_desk:(%s)", TextUtils.join(" ", selectedCategories));
            params.put(NFArticleConstants.NF_REQUEST_PARAM_FIELD_QUERY,fqString);
        }

        List<String> requestFields = NFArticleConstants.getRequestFields();
        params.put(NFArticleConstants.NF_REQUEST_PARAM_FIELD_LIST, TextUtils.join(",", requestFields));

        client.get(BASE_URI, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                List<NFArticle> fetchedArticles = NFArticlesResponse.parseJSON(response).getArticles();
                callback.onSuccess(fetchedArticles);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                callback.onFailure(responseString);
            }
        });
    }
}
