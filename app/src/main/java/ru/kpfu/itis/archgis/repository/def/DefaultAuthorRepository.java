package ru.kpfu.itis.archgis.repository.def;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import okhttp3.RequestBody;
import retrofit2.Call;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.repository.BaseRepository;
import ru.kpfu.itis.archgis.repository.RxUtils;
import ru.kpfu.itis.archgis.repository.cache.RealmCacheErrorHandler;
import ru.kpfu.itis.archgis.repository.cache.RealmRewriteCache;
import ru.kpfu.itis.archgis.repository.impl.AuthorRepository;
import ru.kpfu.itis.archgis.utils.Constants;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class DefaultAuthorRepository extends BaseRepository implements AuthorRepository {


    @Override
    public  void saveAuthor(@NonNull final Author data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, Author.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
//            author.update(data);
        });
    }

    @Override
    public Author getAuthorById(long id) {
        Realm realm = Realm.getDefaultInstance();
        Author inMemory = null;
        try {
            Author author = realm.where(Author.class).equalTo("id", id).findFirst();
            inMemory = realm.copyFromRealm(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.close();
        return inMemory;
    }

    @Override
    public List<Author> getAllAuthors(@NonNull Realm realm) {
        return realm.where(Author.class).findAll();
    }


    @Override
    public Observable<List<Author>> authors(String author, String year) {
        return ApiFactory.getAuthorService()
                .getAuthors(author, year)
                .flatMap(new RealmRewriteCache<>(Author.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(Author.class))
                .compose(RxUtils.async());

    }

    @Override
    public Observable<Author> getGeneral(Long id) throws IOException, JSONException {

        String json2 = "{\"author:Author\":{\"id\":" +id + ",\"select\":\"*\"}}";
        Number t = id;
        String json = new JSONObject()
                .put("author", new JSONObject()
                    .put("id", t)
                    .put("select", "*")).toString();

        String json3 = "{\"author:Author\": {\"id\": "+id+" , \"select\": \"*\"},}";

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/x-www-form-urlencoded"),
                json3);

        return ApiFactory.getAuthorService()
                .getGeneral( body)
                .compose(RxUtils.async());

    }


}
