package ru.kpfu.itis.archgis.screen.map;

import android.support.annotation.NonNull;


import ru.kpfu.itis.archgis.repository.RepositoryProvider;

/**
 * Created by DNS1 on 03.06.2017.
 */

public class SearchMapResearchPresenter implements SearchMapPresenter {

    private final SearchMapView mView;

    public SearchMapResearchPresenter(@NonNull SearchMapView view) {
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideResearchRepository()
                .getAllResearchResponse()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::loadResearchResponse, throwable -> mView.showError());
    }
}
