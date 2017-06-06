package ru.kpfu.itis.archgis.screen.quick.researchlist;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.response.ResearchResponse;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.common.CommonListView;

/**
 * Created by DNS1 on 30.05.2017.
 */

public class ResearchListPresenter {

    private final CommonListView<ResearchResponse> mView;


    public ResearchListPresenter(@NonNull CommonListView<ResearchResponse> view) {
        mView = view;
    }

    public void init(String author, String year) {

        RepositoryProvider.provideResearchRepository()
                .researches(author, year)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showItems, throwable -> mView.showError());
    }

    public void onItemClick(ResearchResponse item) {
        mView.showDetails(item);
    }


}
