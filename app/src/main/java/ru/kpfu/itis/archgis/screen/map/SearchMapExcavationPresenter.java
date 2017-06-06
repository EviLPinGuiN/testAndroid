package ru.kpfu.itis.archgis.screen.map;

import android.support.annotation.NonNull;

import ru.kpfu.itis.archgis.repository.RepositoryProvider;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class SearchMapExcavationPresenter implements SearchMapPresenter {
    private final SearchMapView mView;

    public SearchMapExcavationPresenter(@NonNull SearchMapView view) {
        mView = view;
    }

    public void init() {
        RepositoryProvider.provideExcavationRepository()
                .getAllExcavationResponse()
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::loadExcavationResponse, throwable -> mView.showError());
    }
}
