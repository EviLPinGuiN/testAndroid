package ru.kpfu.itis.archgis.repository.impl;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import retrofit2.Call;
import ru.kpfu.itis.archgis.model.data.general.Author;

/**
 * Created by DNS1 on 16.05.2017.
 */

public interface AuthorRepository {

    void saveAuthor(Author author);

    Author getAuthorById(long id);

    List<Author> getAllAuthors(Realm realm);

    Observable<List<Author>> authors(String author, String year);

    Observable<Author> getGeneral(Long id) throws IOException, JSONException;
}
