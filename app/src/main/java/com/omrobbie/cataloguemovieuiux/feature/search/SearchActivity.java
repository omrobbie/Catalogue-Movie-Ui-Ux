package com.omrobbie.cataloguemovieuiux.feature.search;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.omrobbie.cataloguemovieuiux.R;
import com.omrobbie.cataloguemovieuiux.api.APIClient;
import com.omrobbie.cataloguemovieuiux.model.search.SearchModel;
import com.omrobbie.cataloguemovieuiux.util.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    public static final String MOVIE_TITLE = "movie_title";

    @BindView(R.id.rv_search)
    RecyclerView rv_search;

    private SearchAdapter adapter;

    private Call<SearchModel> apiCall;
    private APIClient apiClient = new APIClient();

    private static String BUNDLE = "bundle";
    private Parcelable state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        if (savedInstanceState != null) state = savedInstanceState.getParcelable(BUNDLE);

        setupList();

        String movie_title = getIntent().getStringExtra(MOVIE_TITLE);
        loadData(movie_title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null) apiCall.cancel();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE, rv_search.getLayoutManager().onSaveInstanceState());
    }

    private void setupList() {
        adapter = new SearchAdapter();
        rv_search.setLayoutManager(new LinearLayoutManager(this));
        rv_search.setAdapter(adapter);
    }

    private void loadData(String movie_title) {
        apiCall = apiClient.getService().getSearchMovie(movie_title, Language.getCountry());
        apiCall.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    adapter.replaceAll(response.body().getResults());

                    if (state != null) rv_search.getLayoutManager().onRestoreInstanceState(state);
                } else loadFailed();
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    private void loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }
}
