package ru.kpfu.itis.archgis.screen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;

/**
 * Created by DNS1 on 08.06.2017.
 */

public class MainItemHolder extends RecyclerView.ViewHolder{


    @BindView(R.id.cv_tv_main)
    TextView title;
    @BindView(R.id.cv_iv_create_main)
    ImageView imageView;
    @BindView(R.id.card_view_main) CardView cv;


    @NonNull
    public static MainItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.nav_item, null);
        MainItemHolder holder = new MainItemHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }

    public MainItemHolder(View view) {
        super(view);

    }

}
