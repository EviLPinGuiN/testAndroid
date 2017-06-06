package ru.kpfu.itis.archgis.api;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.kpfu.itis.archgis.model.data.general.Author;

/**
 * Created by DNS1 on 04.06.2017.
 */

public interface AuthorService {

    @GET("/search/filter_authors")
    Observable<List<Author>> getAuthors(@Query("author") String authorName, @Query("year") String date);

//    @FormUrlEncoded
//    @POST("/hquery/read")
//    Observable<Author> getGeneral( @Field("limit") String limit,@Field("Author") String body);


    @POST("/hquery/read")
    Observable<Author> getGeneral( @Body RequestBody body);


}
