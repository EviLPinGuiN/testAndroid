package ru.kpfu.itis.archgis.screen.map;

import android.support.annotation.NonNull;

import ru.kpfu.itis.archgis.repository.RepositoryProvider;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class SearchMapMonumentPresenter implements SearchMapPresenter {
    private final SearchMapView mView;

    public SearchMapMonumentPresenter(@NonNull SearchMapView view) {
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideMoumentRepository()
                .getAllMonumentResponse()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::loadMonumentResponse, throwable -> mView.showError());
    }
}
