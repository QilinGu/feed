package com.codepath.kpu.feed.network;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kpu on 2/14/16.
 */
public class NFArticleConstants {

    public static final String NF_REQUEST_PARAM_API_KEY = "api-key";
    public static final String NF_REQUEST_PARAM_PAGE = "page";
    public static final String NF_REQUEST_PARAM_QUERY = "q";
    public static final String NF_REQUEST_PARAM_FIELD_QUERY = "fq";
    public static final String NF_REQUEST_PARAM_BEGIN_DATE = "begin_date";
    public static final String NF_REQUEST_PARAM_SORT = "sort";
    public static final String NF_REQUEST_PARAM_FIELD_LIST = "fl";

    public static final String NF_REQUEST_CATEGORY_WORLD = "World";
    public static final String NF_REQUEST_CATEGORY_US = "U.S.";
    public static final String NF_REQUEST_CATEGORY_BUSINESS = "Business";
    public static final String NF_REQUEST_CATEGORY_TECH = "Technology";
    public static final String NF_REQUEST_CATEGORY_SCIENCE = "Science";
    public static final String NF_REQUEST_CATEGORY_SPORTS = "Sports";
    public static final String NF_REQUEST_CATEGORY_ARTS = "Arts";

    public static final String NF_ARTICLE_WEB_URL = "web_url";
    public static final String NF_ARTICLE_SNIPPET = "snippet";
    public static final String NF_ARTICLE_LEAD_PARAGRAPH = "lead_paragraph";
    public static final String NF_ARTICLE_ABSTRACT = "abstract";
    public static final String NF_ARTICLE_HEADLINE = "headline";
    public static final String NF_ARTICLE_MULTIMEDIA = "multimedia";

    public static List<String> getRequestFields() {
        List<String> requestFields = new ArrayList<>();
        requestFields.add(NF_ARTICLE_WEB_URL);
        requestFields.add(NF_ARTICLE_SNIPPET);
        requestFields.add(NF_ARTICLE_LEAD_PARAGRAPH);
        requestFields.add(NF_ARTICLE_ABSTRACT);
        requestFields.add(NF_ARTICLE_HEADLINE);
        requestFields.add(NF_ARTICLE_MULTIMEDIA);

        return requestFields;
    }
}
