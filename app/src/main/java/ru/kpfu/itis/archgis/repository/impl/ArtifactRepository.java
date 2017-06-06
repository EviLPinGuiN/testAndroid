package ru.kpfu.itis.archgis.repository.impl;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.kpfu.itis.archgis.model.data.general.Artifact;

/**
 * Created by DNS1 on 16.05.2017.
 */

public interface ArtifactRepository {

    void saveArtifact(Artifact artifact);

    Artifact getArtifactById(long id);

    Observable<List<Artifact>> getAllArtifacts();
}
