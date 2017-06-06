package ru.kpfu.itis.archgis.screen.common;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by DNS1 on 27.05.2017.
 */

public interface CommonListView<T> extends LoadingView {

    void showItems(@NonNull List<T> items);

    void showError(Throwable throwable);

    void showDetails(T item);

}
