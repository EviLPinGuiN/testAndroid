package ru.kpfu.itis.archgis.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;

/**
 * Created by DNS1 on 08.06.2017.
 */

public interface MonumentService {

    @GET("/search/filter_monuments")
    Observable<List<MonumentResponse>> getMonuments(@Query("name") String name, @Query("epoch") String epoch,
                                                    @Query("type") String type);


}
