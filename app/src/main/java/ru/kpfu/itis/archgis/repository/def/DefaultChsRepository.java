package ru.kpfu.itis.archgis.repository.def;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.details.SimpleChsDetails;
import ru.kpfu.itis.archgis.model.response.ChsResponse;
import ru.kpfu.itis.archgis.repository.BaseRepository;
import ru.kpfu.itis.archgis.repository.RxUtils;
import ru.kpfu.itis.archgis.repository.cache.RealmCacheErrorHandler;
import ru.kpfu.itis.archgis.repository.cache.RealmRewriteCache;
import ru.kpfu.itis.archgis.repository.impl.ChsRepository;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class DefaultChsRepository extends BaseRepository implements ChsRepository {

    @Override
    public  void saveChs(@NonNull final SimpleChsDetails data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, SimpleChsDetails.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
//            author.update(data);
        });
    }

    @Override
    public Observable<List<ChsResponse>> chses(String name) {
        return ApiFactory.getChsService()
                .getCHS(name)
                .flatMap(new RealmRewriteCache<>(ChsResponse.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(ChsResponse.class))
                .compose(RxUtils.async());

    }

    @Override
    public Observable<List<ChsResponse>> getAllChsResponse() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ChsResponse> results = realm.where(ChsResponse.class).findAll();
        return Observable.just(realm.copyFromRealm(results));
    }

}
