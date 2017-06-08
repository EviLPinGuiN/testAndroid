package ru.kpfu.itis.archgis.screen.common;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

import ru.kpfu.itis.archgis.utils.widget.BaseAdapter;


/**
 * Created by DNS1 on 27.05.2017.
 */
public class CommonAdapter<T extends ListItem> extends BaseAdapter<ItemHolder,  T> {

    public CommonAdapter(@NonNull List<T> items) {
        super(items);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ListItem item = getItem(position);
        holder.bind(item);
    }


}
