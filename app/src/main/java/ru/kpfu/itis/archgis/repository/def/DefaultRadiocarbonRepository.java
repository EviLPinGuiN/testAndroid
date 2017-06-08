package ru.kpfu.itis.archgis.repository.def;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Radiocarbon;
import ru.kpfu.itis.archgis.model.response.RadiocarbonDate;
import ru.kpfu.itis.archgis.model.response.RadiocarbonResponse;
import ru.kpfu.itis.archgis.repository.BaseRepository;
import ru.kpfu.itis.archgis.repository.RxUtils;
import ru.kpfu.itis.archgis.repository.cache.RealmCacheErrorHandler;
import ru.kpfu.itis.archgis.repository.cache.RealmRewriteCache;
import ru.kpfu.itis.archgis.repository.impl.RadiocarbonRepository;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class DefaultRadiocarbonRepository extends BaseRepository implements RadiocarbonRepository {


    @Override
    public void saveRadiocarbon(@NonNull final Radiocarbon data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, Radiocarbon.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
        });
    }

    @Override
    public Radiocarbon getRadiocarbonById(long id) {
        Realm realm = Realm.getDefaultInstance();
        Radiocarbon inMemory = null;
        try {
            Radiocarbon radiocarbon = realm.where(Radiocarbon.class).equalTo("id", id).findFirst();
            inMemory = realm.copyFromRealm(radiocarbon);
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.close();
        return inMemory;
    }

    @Override
    public List<Radiocarbon> getAllRadiocarbons(Realm realm) {
        return realm.where(Radiocarbon.class).findAll();
    }

    @Override
    public Observable<List<RadiocarbonDate>> radiocarbons(String name) {
        return ApiFactory.getRadiocarbonService()
                .getRadiocarbons(name)
                .flatMap(new RealmRewriteCache<>(RadiocarbonDate.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(RadiocarbonDate.class))
                .compose(RxUtils.async());

    }

    @Override
    public Observable<List<RadiocarbonDate>> getAllRadiocarbonResponse() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<RadiocarbonDate> results = realm.where(RadiocarbonDate.class).findAll();
        return Observable.just(realm.copyFromRealm(results));
    }


}
