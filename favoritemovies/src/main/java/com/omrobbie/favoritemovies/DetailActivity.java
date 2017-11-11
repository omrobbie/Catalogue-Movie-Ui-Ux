package com.omrobbie.favoritemovies;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.omrobbie.favoritemovies.provider.FavoriteModel;
import com.omrobbie.favoritemovies.util.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_backdrop)
    ImageView iv_backdrop;

    @BindView(R.id.iv_poster)
    ImageView iv_poster;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_release_date)
    TextView tv_release_date;

    @BindView(R.id.tv_vote)
    TextView tv_vote;

    @BindView(R.id.tv_overview)
    TextView tv_overview;

    private FavoriteModel item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        loadData();
        storeData();
    }

    private void loadData() {
        Uri uri = getIntent().getData();
        if (uri == null) return;
        Cursor cursor = getContentResolver().query(
                uri,
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) item = new FavoriteModel(cursor);
            cursor.close();
        }
    }

    private void storeData() {
        if (item == null) return;

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w342/" + item.getBackdropPath())
                .into(iv_backdrop);

        Glide.with(this)
                .load("http://image.tmdb.org/t/p/w92" + item.getPosterPath())
                .into(iv_poster);

        tv_title.setText(item.getTitle());
        tv_release_date.setText(DateTime.getLongDate(item.getReleaseDate()));
        tv_vote.setText(String.valueOf(item.getVoteAverage()));
        tv_overview.setText(item.getOverview());
    }
}
