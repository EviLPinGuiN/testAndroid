package ru.kpfu.itis.archgis.repository;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Report;
import ru.kpfu.itis.archgis.model.response.ReportResponse;
import ru.kpfu.itis.archgis.repository.cache.RealmCacheErrorHandler;
import ru.kpfu.itis.archgis.repository.cache.RealmRewriteCache;
import ru.kpfu.itis.archgis.repository.impl.ReportRepository;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class DefaultReportRepository extends BaseRepository implements ReportRepository {


    @Override
    public void saveReport(@NonNull final Report data) {
        executeTransaction(realm -> {
            long nextId = nextKey(realm, Report.class);
            data.setId(nextId);
            realm.insertOrUpdate(data);
        });
    }

    @Override
    public Observable<List<ReportResponse>> reports(String author, String year) {
        return ApiFactory.getQuickSearchService()
                .getReports(author, year)
                .flatMap(new RealmRewriteCache<>(ReportResponse.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(ReportResponse.class))
                .compose(RxUtils.async());

    }


}
