package ru.kpfu.itis.archgis.screen.show.author;

import android.support.annotation.NonNull;

import org.json.JSONException;

import java.io.IOException;

import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;

/**
 * Created by DNS1 on 04.06.2017.
 */

public class AuthorShowPresenter {


    private final GeneralView<Author> mView;


    public AuthorShowPresenter(@NonNull GeneralView<Author> view) {
        mView = view;
    }

    public void init(Long id) throws IOException, JSONException {
        RepositoryProvider.provideAuthorRepository()
                .getGeneral(id)
                .subscribe(mView::showInfo, throwable -> mView.showError(throwable));
    }



}
