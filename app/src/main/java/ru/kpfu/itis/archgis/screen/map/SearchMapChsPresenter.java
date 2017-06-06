package ru.kpfu.itis.archgis.screen.map;

import android.support.annotation.NonNull;

import ru.kpfu.itis.archgis.repository.RepositoryProvider;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class SearchMapChsPresenter implements SearchMapPresenter {


    private final SearchMapView mView;

    public SearchMapChsPresenter(@NonNull SearchMapView view) {
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideChsRepository()
                .getAllChsResponse()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::loadChsResponse, throwable -> mView.showError());
    }
}
