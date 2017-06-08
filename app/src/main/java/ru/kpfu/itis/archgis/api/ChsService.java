package ru.kpfu.itis.archgis.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kpfu.itis.archgis.model.response.ChsResponse;

/**
 * Created by DNS1 on 08.06.2017.
 */

public interface ChsService {

    @GET("/search/okns")
    Observable<List<ChsResponse>> getCHS(@Query("needle") String name);

}
