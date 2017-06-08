package ru.kpfu.itis.archgis.repository.impl;

import io.reactivex.Observable;
import ru.kpfu.itis.archgis.model.Response;

/**
 * Created by DNS1 on 08.06.2017.
 */

public interface UserRepository {
    Observable<Response> login(String email, String password);
}
