package com.codepath.kpu.feed.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kpu on 2/14/16.
 */
public class NFArticle {
    private final String BASE_IMG_URL = "http://www.nytimes.com/";

    private String web_url;

    private String snippet;

    private String lead_paragraph;

    @SerializedName("abstract")
    private String summary;

    private Headline headline;

    private List<Multimedia> multimedia;

    private class Headline {
        private String main;

        public String getMain(){
            return main;
        }
    }

    private class Multimedia {
        private String url;
        private String type;
        private String subtype;
        private int width;
        private int height;

        public String getUrl() {
            return url;
        }
    }

    public String getURL() {
        return web_url;
    }

    public String getHeadline() {
        return headline != null ? headline.getMain() : null;
    }

    public String getImageURL() {
        return (multimedia != null && multimedia.size() > 0) ? BASE_IMG_URL + multimedia.get(0).getUrl() : null;
    }
}
