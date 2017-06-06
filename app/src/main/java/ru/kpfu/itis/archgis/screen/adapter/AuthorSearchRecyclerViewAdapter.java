package ru.kpfu.itis.archgis.screen.adapter;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;
import ru.kpfu.itis.archgis.model.data.general.Author;




/**
 * Created by DNS1 on 06.06.2017.
 */

public class AuthorSearchRecyclerViewAdapter extends
        RealmRecyclerViewAdapter<Author, AuthorItemHolder>
        implements Filterable{


    Realm realm;

    public AuthorSearchRecyclerViewAdapter(Realm realm, OrderedRealmCollection<Author> authors) {
        super(authors, true);
        this.realm = realm;
    }


    @Override
    public AuthorItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AuthorItemHolder.create(parent.getContext());
    }


    @Override
    public void onBindViewHolder(AuthorItemHolder holder, int position) {
        Author author = getData().get(position);
        holder.bind(author);
    }


    public void filterResults(String text) {
        text = text == null ? null : text.toLowerCase().trim();
        if(text == null || "".equals(text)) {
            updateData(realm.where(Author.class).findAll());
        } else {
            updateData(realm.where(Author.class)
                    .contains("name", text, Case.SENSITIVE)
                    .findAll());
        }
    }


    public Filter getFilter() {
        AuthorFilter filter = new AuthorFilter(this);
        return filter;
    }

    private class AuthorFilter
            extends Filter {
        private final AuthorSearchRecyclerViewAdapter adapter;

        private AuthorFilter(AuthorSearchRecyclerViewAdapter adapter) {
            super();
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return new FilterResults();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.filterResults(constraint.toString());
        }
    }



}
