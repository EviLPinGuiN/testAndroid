package ru.kpfu.itis.archgis.screen.quick.radiocarbonlist;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.operators.observable.ObservableToList;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.response.RadiocarbonDate;
import ru.kpfu.itis.archgis.model.response.RadiocarbonResponse;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.common.CommonListView;

/**
 * Created by DNS1 on 30.05.2017.
 */

public class RadiocarbonListPresenter {

    private final CommonListView<RadiocarbonResponse> mView;


    public RadiocarbonListPresenter(@NonNull CommonListView<RadiocarbonResponse> view) {
        mView = view;
    }

    public void init(String name) {
        RepositoryProvider.provideRadiocarbonRepository()
                .radiocarbons(name)
                .flatMapIterable(list -> list)
                .map(RadiocarbonDate::getRadiocarbonResponse)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .toList()
                .subscribe(mView::showItems, throwable -> mView.showError());
    }

    public void onItemClick(RadiocarbonResponse item) {
        mView.showDetails(item);
    }
}
