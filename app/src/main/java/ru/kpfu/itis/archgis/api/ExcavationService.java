package ru.kpfu.itis.archgis.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;

/**
 * Created by DNS1 on 08.06.2017.
 */

public interface ExcavationService {

    @GET("/search/filter_excavations")
    Observable<List<ExcavationResponse>> getExcavation(@Query("author") String authorName, @Query("year") String date);

}
