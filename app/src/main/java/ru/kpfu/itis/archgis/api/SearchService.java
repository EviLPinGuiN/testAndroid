package ru.kpfu.itis.archgis.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Query;
import retrofit2.http.GET;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.model.response.ChsResponse;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;
import ru.kpfu.itis.archgis.model.response.RadiocarbonDate;
import ru.kpfu.itis.archgis.model.response.ReportResponse;
import ru.kpfu.itis.archgis.model.response.ResearchResponse;

/**
 * Created by DNS1 on 27.05.2017.
 */

public interface SearchService {



    @GET("/search/filter_monuments")
    Observable<List<MonumentResponse>> getMonuments(@Query("name") String name, @Query("epoch") String epoch,
                                                    @Query("type") String type);

    @GET("/search/filter_reports")
    Observable<List<ReportResponse>> getReports(@Query("author") String authorName, @Query("year") String date);

    @GET("/search/okns")
    Observable<List<ChsResponse>> getCHS(@Query("needle") String name);

    @GET("/search/filter_excavations")
    Observable<List<ExcavationResponse>> getExcavation(@Query("author") String authorName, @Query("year") String date);

    @GET("/search/filter_radiocarbons")
    Observable<List<RadiocarbonDate>> getRadiocarbons(@Query("name") String name);




}
