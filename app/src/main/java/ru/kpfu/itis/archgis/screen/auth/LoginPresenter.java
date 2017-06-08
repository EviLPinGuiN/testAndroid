package ru.kpfu.itis.archgis.screen.auth;

import ru.kpfu.itis.archgis.model.Response;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;

/**
 * Created by DNS1 on 08.06.2017.
 */

public class LoginPresenter {


    private final LoginView mView;

    public LoginPresenter(LoginView mView) {
        this.mView = mView;
    }

    public void login(String email, String password){


        RepositoryProvider.provideUserRepository()
                .login(email, password)
                .subscribe(mView::handleResponse, mView::handleError);

    }


    public interface LoginView{

        void handleResponse(Response response);

        void handleError(Throwable error);
    }

}
