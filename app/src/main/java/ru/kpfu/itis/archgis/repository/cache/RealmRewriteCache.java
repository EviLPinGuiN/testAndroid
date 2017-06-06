package ru.kpfu.itis.archgis.repository.cache;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmModel;
import io.reactivex.functions.Function;

/**
 * Created by DNS1 on 27.05.2017.
 */

public class RealmRewriteCache<T extends RealmModel> implements Function<List<T>, Observable<List<T>>> {

    private final Class<T> mClass;

    public RealmRewriteCache(@NonNull Class<T> tClass) {
        mClass = tClass;
    }


    @Override
    public Observable<List<T>> apply(@io.reactivex.annotations.NonNull List<T> elements) throws Exception {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            realm.delete(mClass);
            realm.insert(elements);
        });
        return Observable.just(elements);
    }
}
