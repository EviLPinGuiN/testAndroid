package ru.kpfu.itis.archgis.screen.quick.authorlist;

import android.support.annotation.NonNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.common.CommonListView;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class AuthorListPresenter {

    private final CommonListView<Author> mView;


    public AuthorListPresenter(@NonNull CommonListView<Author> view) {
        mView = view;
    }

    public void init(String author, String year) {
        RepositoryProvider.provideAuthorRepository()
                .authors(author, year)
                .doOnSubscribe(mView::showLoading)
                .doOnTerminate(mView::hideLoading)
                .subscribe(mView::showItems, throwable -> mView.showError());
    }


    public void onItemClick(Author author) {
        mView.showDetails(author);
    }


}
