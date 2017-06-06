package ru.kpfu.itis.archgis.screen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;

/**
 * Created by DNS1 on 06.06.2017.
 */

public class AuthorItemHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.up)
    ImageView imageView;

    @NonNull
    public static AuthorItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.search_option, null);
        AuthorItemHolder holder = new AuthorItemHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }

    public AuthorItemHolder(View view) {
        super(view);
    }

    public void bind(@NonNull Author item) {
        title.setText(item.getName());
    }
}
