package ru.kpfu.itis.archgis.screen.quick.reportlist;

import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Report;
import ru.kpfu.itis.archgis.model.response.ReportResponse;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.common.CommonListView;

/**
 * Created by DNS1 on 30.05.2017.
 */

public class ReportListPresenter {


    private final CommonListView<ReportResponse> mView;


    public ReportListPresenter(@NonNull CommonListView<ReportResponse> view) {
        mView = view;
    }

    public void init(String author, String year) {
        RepositoryProvider.provideReportRepository()
                .reports(author, year)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showItems, throwable -> mView.showError());
    }


    public void onItemClick(ReportResponse item) {
        mView.showDetails(item);
    }


}
