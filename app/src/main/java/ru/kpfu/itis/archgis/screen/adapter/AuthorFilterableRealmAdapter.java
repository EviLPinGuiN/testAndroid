package ru.kpfu.itis.archgis.screen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.RealmResults;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;

/**
 * Created by DNS1 on 06.06.2017.
 */

public class AuthorFilterableRealmAdapter extends FilterableRealmAdapter<Author> {

    private AutoCompleteTextView mSearch;

    public AuthorFilterableRealmAdapter(Context context, RealmResults<Author> realmObjectList, AutoCompleteTextView refAutocomplete) {
        super(context, realmObjectList, refAutocomplete);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Author author = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.support_simple_spinner_dropdown_item, parent, false);
        }
        TextView textView = ((TextView) convertView.findViewById(R.id.title));
        textView.setText(author.getName());

//        ImageView up = (ImageView) convertView.findViewById(R.id.up);
//        up.setOnClickListener(view -> {
//            mSearch.setText(textView.getText().toString());
//            mSearch.setSelection(mSearch.getText().length());
//        });
        return convertView;
    }
}
