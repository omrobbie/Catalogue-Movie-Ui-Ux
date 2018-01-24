/*
 * Created by omrobbie.
 * Copyright (c) 2018. All rights reserved.
 * Last modified 11/11/17 3:14 PM.
 */

package com.omrobbie.cataloguemovieuiux.feature.favorite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.omrobbie.cataloguemovieuiux.BuildConfig;
import com.omrobbie.cataloguemovieuiux.DetailActivity;
import com.omrobbie.cataloguemovieuiux.R;
import com.omrobbie.cataloguemovieuiux.model.ResultsItem;
import com.omrobbie.cataloguemovieuiux.util.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by omrobbie on 10/11/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Cursor list;

    public FavoriteAdapter(Cursor items) {
        replaceAll(items);
    }

    public void replaceAll(Cursor items) {
        list = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_detail_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.getCount();
    }

    private ResultsItem getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new ResultsItem(list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_poster)
        ImageView img_poster;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_overview)
        TextView tv_overview;

        @BindView(R.id.tv_release_date)
        TextView tv_release_date;

        @BindView(R.id.btn_detail)
        Button btn_detail;

        @BindView(R.id.btn_share)
        Button btn_share;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ResultsItem item) {
            tv_title.setText(item.getTitle());
            tv_overview.setText(item.getOverview());
            tv_release_date.setText(DateTime.getLongDate(item.getReleaseDate()));
            Glide.with(itemView.getContext())
                    .load(BuildConfig.BASE_URL_IMG + "w154" + item.getPosterPath())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .centerCrop()
                    )
                    .into(img_poster);

            btn_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.MOVIE_ITEM, new Gson().toJson(item));
                    itemView.getContext().startActivity(intent);
                }
            });

            btn_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TITLE, item.getTitle());
                    intent.putExtra(Intent.EXTRA_SUBJECT, item.getTitle());
                    intent.putExtra(Intent.EXTRA_TEXT, item.getTitle() + "\n\n" + item.getOverview());
                    itemView.getContext().startActivity(Intent.createChooser(intent, itemView.getResources().getString(R.string.label_share)));
                }
            });
        }
    }
}
