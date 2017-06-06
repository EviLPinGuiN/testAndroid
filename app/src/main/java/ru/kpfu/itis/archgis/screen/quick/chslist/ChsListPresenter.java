package ru.kpfu.itis.archgis.screen.quick.chslist;

import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.response.ChsResponse;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.common.CommonListView;

/**
 * Created by DNS1 on 30.05.2017.
 */

public class ChsListPresenter {

    private final CommonListView<ChsResponse> mView;
    private CompositeDisposable mSubscriptions;

    public ChsListPresenter(@NonNull CommonListView<ChsResponse> view) {
        mSubscriptions = new CompositeDisposable();
        mView = view;
    }

    public void init(String name) {

        RepositoryProvider.provideChsRepository()
                .chses(name)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showItems, throwable -> mView.showError());

    }


    public void onItemClick(ChsResponse item) {
        mView.showDetails(item);
    }

}
