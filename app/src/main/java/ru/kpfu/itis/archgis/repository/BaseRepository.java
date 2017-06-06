package ru.kpfu.itis.archgis.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by DNS1 on 18.05.2017.
 */

public abstract class BaseRepository {

    protected long nextKey(Realm realm, final Class<? extends RealmObject> c){
        Number maxId = realm.where(c).max("id");
        long nextId = (maxId == null) ? 1 : maxId.longValue() + 1;
        return nextId;
    }


    protected  void executeTransaction(@NonNull Realm.Transaction transaction) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(transaction);
        } catch (Throwable e) {
//            L.e("executeTransaction", e);
        } finally {
            close(realm);
        }
    }

    protected  void close(@Nullable Realm realm) {
        if (realm != null) {
            realm.close();
        }
    }
}
