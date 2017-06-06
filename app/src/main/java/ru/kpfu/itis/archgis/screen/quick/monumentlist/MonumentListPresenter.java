package ru.kpfu.itis.archgis.screen.quick.monumentlist;

import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.common.CommonListView;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class MonumentListPresenter {

    private final CommonListView<MonumentResponse> mView;


    public MonumentListPresenter(@NonNull CommonListView<MonumentResponse> view) {

        mView = view;
    }

    public void init(String name, String epoch, String type) {
        RepositoryProvider.provideMoumentRepository()
                .monuments(name, epoch, type)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showItems, throwable -> mView.showError());
    }


    public void onItemClick(MonumentResponse item) {
        mView.showDetails(item);
    }

}
