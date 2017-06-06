package ru.kpfu.itis.archgis.repository.impl;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.kpfu.itis.archgis.model.data.general.Research;
import ru.kpfu.itis.archgis.model.response.ResearchResponse;

/**
 * Created by DNS1 on 16.05.2017.
 */

public interface ResearchRepository {

    void saveResearch(Research research);

    Research getResearchById(long id);

    List<Research> getAllResearhes(Realm realm);

    Observable<List<ResearchResponse>> researches(String author, String year);

    Observable<List<ResearchResponse>> getAllResearchResponse();

}
