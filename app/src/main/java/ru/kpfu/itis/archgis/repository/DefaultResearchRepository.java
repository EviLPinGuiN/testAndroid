package ru.kpfu.itis.archgis.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Research;
import ru.kpfu.itis.archgis.model.response.ResearchResponse;
import ru.kpfu.itis.archgis.repository.cache.RealmCacheErrorHandler;
import ru.kpfu.itis.archgis.repository.cache.RealmRewriteCache;
import ru.kpfu.itis.archgis.repository.impl.ResearchRepository;
import ru.kpfu.itis.archgis.repository.RxUtils;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class DefaultResearchRepository extends BaseRepository implements ResearchRepository {
    @Override
    public void saveResearch(@NonNull final Research data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, Research.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
        });
    }

    @Override
    public Research getResearchById(long id) {
        Realm realm = Realm.getDefaultInstance();
        Research inMemory = null;
        try {
            Research research = realm.where(Research.class).equalTo("id", id).findFirst();
            inMemory = realm.copyFromRealm(research);
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.close();
        return inMemory;
    }

    @Override
    public List<Research> getAllResearhes(@NonNull Realm realm) {
        return realm.where(Research.class).findAll();
    }

    @Override
    public Observable<List<ResearchResponse>> researches(String author, String year) {
        return ApiFactory.getResearchService()
                .getResearches(year, author)
                .flatMap(new RealmRewriteCache<>(ResearchResponse.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(ResearchResponse.class))
                .compose(RxUtils.async());

    }

    @Override
    public Observable<List<ResearchResponse>> getAllResearchResponse() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ResearchResponse> results = realm.where(ResearchResponse.class).findAll();
        return Observable.just(realm.copyFromRealm(results));
    }


}
