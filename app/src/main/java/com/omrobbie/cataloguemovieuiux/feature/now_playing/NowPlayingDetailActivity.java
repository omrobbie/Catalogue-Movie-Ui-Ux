package com.omrobbie.cataloguemovieuiux.feature.now_playing;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.omrobbie.cataloguemovieuiux.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class NowPlayingDetailActivity extends AppCompatActivity {

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.img_backdrop)
    ImageView img_backdrop;

    @BindView(R.id.img_poster)
    ImageView img_poster;

    @BindView(R.id.tv_release_date)
    TextView tv_release_date;

    @BindView(R.id.tv_vote)
    TextView tv_vote;

    @BindViews({
            R.id.img_star1,
            R.id.img_star2,
            R.id.img_star3,
            R.id.img_star4,
            R.id.img_star5
    })
    List<ImageView> img_vote;

    @BindView(R.id.tv_genres)
    TextView tv_genres;

    @BindView(R.id.tv_overview)
    TextView tv_overview;

    @BindView(R.id.img_poster_belongs)
    ImageView img_poster_belongs;

    @BindView(R.id.tv_title_belongs)
    TextView tv_title_belongs;

    @BindView(R.id.tv_budget)
    TextView tv_budget;

    @BindView(R.id.tv_revenue)
    TextView tv_revenue;

    @BindView(R.id.tv_companies)
    TextView tv_companies;

    @BindView(R.id.tv_countries)
    TextView tv_countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }
}
