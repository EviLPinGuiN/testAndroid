package ru.kpfu.itis.archgis.repository.impl;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.kpfu.itis.archgis.model.data.general.Excavation;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;

/**
 * Created by DNS1 on 04.06.2017.
 */

public interface ExcavationRepository {


    void saveExcavation(@NonNull Excavation data);

    Observable<List<ExcavationResponse>> excavations(String author, String year);

    Observable<List<ExcavationResponse>> getAllExcavationResponse();
}
