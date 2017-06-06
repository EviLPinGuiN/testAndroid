package ru.kpfu.itis.archgis.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Query;
import retrofit2.http.GET;
import ru.kpfu.itis.archgis.model.response.ResearchResponse;

/**
 * Created by DNS1 on 01.06.2017.
 */

public interface ResearchService {

    @GET("/search/filter_res")
    Observable<List<ResearchResponse>> getResearches(@Query("year") String date, @Query("author") String authorName);


}
