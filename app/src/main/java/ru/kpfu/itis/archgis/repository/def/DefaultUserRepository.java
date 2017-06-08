package ru.kpfu.itis.archgis.repository.def;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.model.Response;
import ru.kpfu.itis.archgis.repository.RxUtils;
import ru.kpfu.itis.archgis.repository.impl.UserRepository;

/**
 * Created by DNS1 on 08.06.2017.
 */

public class DefaultUserRepository implements UserRepository {


    @Override
    public Observable<Response> login(String email, String password){
        RequestBody requestEmail = RequestBody.create(
                MediaType.parse("multipart/form-data"), email);
        RequestBody requestPassword = RequestBody.create(
                MediaType.parse("multipart/form-data"), password);
        return ApiFactory.getUserService()
                .login(requestEmail, requestPassword)
                .compose(RxUtils.async());
    }

}
