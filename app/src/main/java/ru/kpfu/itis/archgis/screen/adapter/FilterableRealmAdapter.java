package ru.kpfu.itis.archgis.screen.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;

import java.util.List;
import android.widget.Filter;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by DNS1 on 06.06.2017.
 */

public class FilterableRealmAdapter <T extends RealmObject> extends ArrayAdapter<T> implements Filterable {

    final RealmResults<T> mRealmObjectList;
    List<T> mResults;

    public FilterableRealmAdapter(Context context, RealmResults<T> realmObjectList, AutoCompleteTextView refAutocomplete) {
        super(context, android.R.layout.simple_list_item_1);
        mRealmObjectList = realmObjectList;
        refAutocomplete.setAdapter(this);
    }

    @Override
    public int getCount() {
        return mResults == null ? 0 : mResults.size();
    }

    @Override
    public T getItem(int position) {
        return mResults == null ? null : mResults.get(position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return new FilterResults();
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (constraint != null) {
                    mResults = mRealmObjectList.where().contains("name", String.valueOf(constraint)).findAll();
                    notifyDataSetChanged();
                }
            }
        };
    }
}
