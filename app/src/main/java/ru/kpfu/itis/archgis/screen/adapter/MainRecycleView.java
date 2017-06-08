package ru.kpfu.itis.archgis.screen.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.MainIcons;
import ru.kpfu.itis.archgis.screen.common.ListItem;

/**
 * Created by DNS1 on 08.06.2017.
 */

public class MainRecycleView extends RecyclerView.Adapter<MainItemHolder>{


    private List<MainIcons> mainIcons;

    public MainRecycleView(List<MainIcons> mainIcons) {
        this.mainIcons = mainIcons;
    }

    private OnMainClickListener listener;

    public void setListener(OnMainClickListener listener) {
        this.listener = listener;
    }

    public interface OnMainClickListener{
        void click(int position);
    }


    @Override
    public MainItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MainItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(MainItemHolder holder, int position) {
        String title = holder.cv.getResources().getString(mainIcons.get(position).getName());


        holder.title.setText(title);
        holder.imageView.setImageResource(mainIcons.get(position).getIcon());
        holder.cv.setOnClickListener(v -> {
            if (listener != null) {
                listener.click(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainIcons.size();
    }
}
