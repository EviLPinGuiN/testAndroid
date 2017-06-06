package ru.kpfu.itis.archgis.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Excavation;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;
import ru.kpfu.itis.archgis.repository.cache.RealmCacheErrorHandler;
import ru.kpfu.itis.archgis.repository.cache.RealmRewriteCache;
import ru.kpfu.itis.archgis.repository.impl.ExcavationRepository;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class DefaultExcavationRepository extends BaseRepository implements ExcavationRepository {


    @Override
    public  void saveExcavation(@NonNull final Excavation data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, Excavation.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
        });
    }

    @Override
    public Observable<List<ExcavationResponse>> excavations(String author, String year) {
        return ApiFactory.getQuickSearchService()
                .getExcavation(author, year)
                .flatMap(new RealmRewriteCache<>(ExcavationResponse.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(ExcavationResponse.class))
                .compose(RxUtils.async());

    }

    @Override
    public Observable<List<ExcavationResponse>> getAllExcavationResponse() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ExcavationResponse> results = realm.where(ExcavationResponse.class).findAll();
        return Observable.just(realm.copyFromRealm(results));
    }



}
