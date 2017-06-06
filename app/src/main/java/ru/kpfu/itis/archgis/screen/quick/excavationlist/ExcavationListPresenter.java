package ru.kpfu.itis.archgis.screen.quick.excavationlist;

import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.common.CommonListView;

/**
 * Created by DNS1 on 30.05.2017.
 */

public class ExcavationListPresenter {

    private final CommonListView<ExcavationResponse> mView;


    public ExcavationListPresenter(@NonNull CommonListView<ExcavationResponse> view) {

        mView = view;
    }

    public void init(String author, String year) {
        RepositoryProvider.provideExcavationRepository()
                .excavations(author, year)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showItems, throwable -> mView.showError());
    }


    public void onItemClick(ExcavationResponse item) {
        mView.showDetails(item);
    }

}
