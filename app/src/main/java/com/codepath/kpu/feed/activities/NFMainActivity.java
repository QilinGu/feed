package com.codepath.kpu.feed.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.codepath.kpu.feed.NFArticlesAdapter;
import com.codepath.kpu.feed.NFEndlessScrollListener;
import com.codepath.kpu.feed.R;
import com.codepath.kpu.feed.fragments.NFSearchSettingsDialog;
import com.codepath.kpu.feed.models.NFArticle;
import com.codepath.kpu.feed.models.NFSearchSettingsModel;
import com.codepath.kpu.feed.network.NFArticleProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NFMainActivity extends AppCompatActivity implements NFSearchSettingsDialog.SearchSettingsDialogListener {

    @Bind(R.id.gvResults)
    GridView gvResults;

    private NFArticlesAdapter articlesAdapter;
    private NFArticleProvider articleProvider;

    private NFSearchSettingsModel searchSettingsModel;

    private String lastQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hook up views with ButterKnife
        ButterKnife.bind(this);

        // Create adapter and link it to the data source
        articlesAdapter = new NFArticlesAdapter(this, new ArrayList<NFArticle>());
        gvResults.setAdapter(articlesAdapter);

        // Initialize our network provider to make requests to New York Times Article Search API
        articleProvider = new NFArticleProvider();

        // Initialize search settings model
        searchSettingsModel = new NFSearchSettingsModel();

        lastQuery = "";

        // Hook up listener for grid click
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create an intent to display the article
                Intent i = new Intent(getApplicationContext(), NFArticleActivity.class);
                NFArticle article = articlesAdapter.getItem(position);
                i.putExtra("url", article.getURL());
                startActivity(i);
            }
        });

        gvResults.setOnScrollListener(new NFEndlessScrollListener(5) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                searchArticles(lastQuery, page);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                articlesAdapter.clear();
                searchArticles(query, 0);
                lastQuery = query;
                searchView.clearFocus();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            showSettingsDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void searchArticles(String query, int page) {
        articleProvider.fetchArticles(query, searchSettingsModel, page, new NFArticleProvider.NFOnArticlesFetched() {
            @Override
            public void onSuccess(List< NFArticle> fetchedArticles) {
                articlesAdapter.addAll(fetchedArticles);
            }

            @Override
            public void onFailure(String errorString) {
                // TODO: handle fetch failure
            }
        });
    }

    private void showSettingsDialog() {
        FragmentManager fm = getSupportFragmentManager();
        NFSearchSettingsDialog searchSettingsDialog = NFSearchSettingsDialog.newInstance(searchSettingsModel);
        searchSettingsDialog.show(fm, "fragment_search_settings_dialog");
    }

    @Override
    public void onSaveSearchSettings(NFSearchSettingsModel searchSettingsModel) {
        this.searchSettingsModel = searchSettingsModel;
    }
}
