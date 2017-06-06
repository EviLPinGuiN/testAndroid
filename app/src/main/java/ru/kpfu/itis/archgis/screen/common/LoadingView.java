package ru.kpfu.itis.archgis.screen.common;

import io.reactivex.disposables.Disposable;

/**
 * Created by DNS1 on 27.05.2017.
 */

public interface LoadingView {

    void hideLoading();

    void showError();

    void showLoading(Disposable disposable);
}
