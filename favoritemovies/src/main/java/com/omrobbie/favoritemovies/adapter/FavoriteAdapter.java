package com.omrobbie.favoritemovies.adapter;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.omrobbie.favoritemovies.DetailActivity;
import com.omrobbie.favoritemovies.R;
import com.omrobbie.favoritemovies.provider.FavoriteModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.omrobbie.favoritemovies.provider.DatabaseContract.CONTENT_URI;

/**
 * Created by omrobbie on 11/11/2017.
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
                        R.layout.activity_main_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private FavoriteModel getItem(int position) {
        if (!list.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }
        return new FavoriteModel(list);
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        else return list.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView iv_poster;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_overview)
        TextView tv_overview;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final FavoriteModel item) {
            tv_title.setText(item.getTitle());
            tv_overview.setText(item.getOverview());

            Glide.with(itemView.getContext())
                    .load("http://image.tmdb.org/t/p/w154/" + item.getPosterPath())
                    .into(iv_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.setData(Uri.parse(CONTENT_URI + "/" + item.getId()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
