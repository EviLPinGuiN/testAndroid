package ru.kpfu.itis.archgis.screen.map;

import android.support.annotation.NonNull;

import ru.kpfu.itis.archgis.repository.RepositoryProvider;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class SearchMapRadiocarbonPresenter implements SearchMapPresenter {

    private final SearchMapView mView;

    public SearchMapRadiocarbonPresenter(@NonNull SearchMapView view) {
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideRadiocarbonRepository()
                .getAllRadiocarbonResponse()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::loadRadiocarbonResponse, throwable -> mView.showError());
    }
}
