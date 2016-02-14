package com.codepath.kpu.feed.models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by kpu on 2/14/16.
 */
public class NFArticlesResponse {

    // Nested response object needed for gson to function properly
    private Response response;

    public static NFArticlesResponse parseJSON(JSONObject response) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        NFArticlesResponse articlesResponse = gson.fromJson(response.toString(), NFArticlesResponse.class);
        return articlesResponse;
    }

    public List<NFArticle> getArticles() {
        return response.getDocs();
    }

    private class Response {
        private List<NFArticle> docs;

        public List<NFArticle> getDocs() {
            return docs;
        }
    }
}
