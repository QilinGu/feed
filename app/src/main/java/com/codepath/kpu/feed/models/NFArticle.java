package com.codepath.kpu.feed.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kpu on 2/14/16.
 */
public class NFArticle {
    private String web_url;

    private String snippet;

    private String lead_paragraph;

    @SerializedName("abstract")
    private String summary;

    private Headline headline;

    private class Headline {
        private String main;

        public String getMain(){
            return main;
        }
    }
}
