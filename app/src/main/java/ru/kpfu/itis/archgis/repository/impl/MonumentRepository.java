package ru.kpfu.itis.archgis.repository.impl;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.kpfu.itis.archgis.model.data.general.Monument;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;

/**
 * Created by DNS1 on 16.05.2017.
 */

public interface MonumentRepository {

    void saveMonument(Monument monument);

    Monument getMonumentById(long id);

    List<Monument> getAllMonuments(Realm realm);

    Observable<List<MonumentResponse>> monuments(String name, String epoch, String type);

    Observable<List<MonumentResponse>> getAllMonumentResponse();
}
