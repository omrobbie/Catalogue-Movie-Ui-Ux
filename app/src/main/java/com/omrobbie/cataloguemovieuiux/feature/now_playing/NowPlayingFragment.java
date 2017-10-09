package com.omrobbie.cataloguemovieuiux.feature.now_playing;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omrobbie.cataloguemovieuiux.R;
import com.omrobbie.cataloguemovieuiux.model.now_playing.ResultsItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.rv_now_playing)
    RecyclerView rv_now_playing;

    private NowPlayingAdapter adapter;

    private List<ResultsItem> list = new ArrayList<>();

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        context = view.getContext();

        unbinder = ButterKnife.bind(this, view);

        setupList();
        loadDummyData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupList() {
        adapter = new NowPlayingAdapter();
        rv_now_playing.setLayoutManager(new LinearLayoutManager(context));
        rv_now_playing.setAdapter(adapter);
    }

    private void loadDummyData() {
        list.clear();
        for (int i = 0; i <= 10; i++) {
            ResultsItem item = new ResultsItem();
            item.setPosterPath("/vSNxAJTlD0r02V9sPYpOjqDZXUK.jpg");
            item.setTitle("This is very very very long movie title that you can read " + i);
            item.setOverview("This is very very very long movie overview that you can read " + i);
            item.setReleaseDate("2016-04-1" + i);
            list.add(item);
        }
        adapter.replaceAll(list);
    }
}
