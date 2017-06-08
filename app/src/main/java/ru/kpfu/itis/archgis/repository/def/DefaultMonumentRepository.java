package ru.kpfu.itis.archgis.repository.def;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Monument;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;
import ru.kpfu.itis.archgis.repository.BaseRepository;
import ru.kpfu.itis.archgis.repository.RxUtils;
import ru.kpfu.itis.archgis.repository.cache.RealmCacheErrorHandler;
import ru.kpfu.itis.archgis.repository.cache.RealmRewriteCache;
import ru.kpfu.itis.archgis.repository.impl.MonumentRepository;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class DefaultMonumentRepository extends BaseRepository implements MonumentRepository {


    @Override
    public void saveMonument(@NonNull final Monument data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, Monument.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
        });
    }

    @Override
    public Monument getMonumentById(long id) {
        Realm realm = Realm.getDefaultInstance();
        Monument inMemory = null;
        try {
            Monument author = realm.where(Monument.class).equalTo("id", id).findFirst();
            inMemory = realm.copyFromRealm(author);
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.close();
        return inMemory;
    }

    @Override
    public List<Monument> getAllMonuments(Realm realm) {
        return realm.where(Monument.class).findAll();
    }

    @Override
    public Observable<List<MonumentResponse>> monuments(String name, String epoch, String type) {
        return ApiFactory.getMonumentService()
                .getMonuments(name, epoch, type)
                .flatMap(new RealmRewriteCache<>(MonumentResponse.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(MonumentResponse.class))
                .compose(RxUtils.async());

    }

    @Override
    public Observable<List<MonumentResponse>> getAllMonumentResponse() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<MonumentResponse> results = realm.where(MonumentResponse.class).findAll();
        return Observable.just(realm.copyFromRealm(results));
    }

}
