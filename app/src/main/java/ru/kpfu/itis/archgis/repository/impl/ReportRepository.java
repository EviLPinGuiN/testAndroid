package ru.kpfu.itis.archgis.repository.impl;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.kpfu.itis.archgis.model.data.general.Report;
import ru.kpfu.itis.archgis.model.response.ReportResponse;

/**
 * Created by DNS1 on 04.06.2017.
 */

public interface ReportRepository {


    void saveReport(@NonNull Report data);

    Observable<List<ReportResponse>> reports(String author, String year);
}
