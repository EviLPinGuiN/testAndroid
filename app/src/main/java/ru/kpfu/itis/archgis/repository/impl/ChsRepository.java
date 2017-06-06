package ru.kpfu.itis.archgis.repository.impl;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.kpfu.itis.archgis.model.data.details.SimpleChsDetails;
import ru.kpfu.itis.archgis.model.response.ChsResponse;

/**
 * Created by DNS1 on 04.06.2017.
 */

public interface ChsRepository {
    void saveChs(@NonNull SimpleChsDetails data);

    Observable<List<ChsResponse>> chses(String name);

    Observable<List<ChsResponse>> getAllChsResponse();
}
