package ru.kpfu.itis.archgis.repository.def;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.kpfu.itis.archgis.model.data.general.Artifact;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;
import ru.kpfu.itis.archgis.repository.BaseRepository;
import ru.kpfu.itis.archgis.repository.impl.ArtifactRepository;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class DefaultArtifactRepository extends BaseRepository implements ArtifactRepository {

    @Override
    public  void saveArtifact(@NonNull final Artifact data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, Artifact.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
//            Artifact artifact = realm.createObject(Artifact.class, nextId);
//            artifact.update(data);
        });
    }


    @Override
    public Artifact getArtifactById(long id) {
        Realm realm = Realm.getDefaultInstance();
        Artifact inMemory = null;
        try {
            Artifact artifact = realm.where(Artifact.class).equalTo("id", id).findFirst();
            inMemory = realm.copyFromRealm(artifact);
        } catch (Exception e) {
            e.printStackTrace();
        }
        realm.close();
        return inMemory;
    }

    @Override
    public Observable<List<Artifact>> getAllArtifacts() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Artifact> results = realm.where(Artifact.class).findAll();

        return Observable.just(realm.copyFromRealm(results));
    }



}
