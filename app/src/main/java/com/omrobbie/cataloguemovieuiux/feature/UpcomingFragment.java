package com.omrobbie.cataloguemovieuiux.feature;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omrobbie.cataloguemovieuiux.R;
import com.omrobbie.cataloguemovieuiux.adapter.MovieAdapter;
import com.omrobbie.cataloguemovieuiux.api.APIClient;
import com.omrobbie.cataloguemovieuiux.model.UpcomingModel;
import com.omrobbie.cataloguemovieuiux.util.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.rv_upcoming)
    RecyclerView rv_upcoming;

    private MovieAdapter adapter;

    private Call<UpcomingModel> apiCall;
    private APIClient apiClient = new APIClient();

    private static String BUNDLE = "bundle";
    private Parcelable state;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        context = view.getContext();

        unbinder = ButterKnife.bind(this, view);

        if (savedInstanceState != null) state = savedInstanceState.getParcelable(BUNDLE);

        setupList();
        loadData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (apiCall != null) apiCall.cancel();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE, rv_upcoming.getLayoutManager().onSaveInstanceState());
    }

    private void setupList() {
        adapter = new MovieAdapter();
        rv_upcoming.setLayoutManager(new LinearLayoutManager(context));
        rv_upcoming.setAdapter(adapter);
    }

    private void loadData() {
        apiCall = apiClient.getService().getUpcomingMovie(Language.getCountry());
        apiCall.enqueue(new Callback<UpcomingModel>() {
            @Override
            public void onResponse(Call<UpcomingModel> call, Response<UpcomingModel> response) {
                if (response.isSuccessful()) {
                    adapter.replaceAll(response.body().getResults());

                    if (state != null) rv_upcoming.getLayoutManager().onRestoreInstanceState(state);
                } else loadFailed();
            }

            @Override
            public void onFailure(Call<UpcomingModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    private void loadFailed() {
        Toast.makeText(context, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }
}
