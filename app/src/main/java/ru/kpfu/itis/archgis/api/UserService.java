package ru.kpfu.itis.archgis.api;

/**
 * Created by DNS1 on 07.05.2017.
 */
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import ru.kpfu.itis.archgis.model.Response;
import ru.kpfu.itis.archgis.model.User;


public interface UserService {

    @POST("users")
    Observable<Response> register(@Body User user);

    @Multipart
    @POST("login")
    Observable<Response> login(@Part("username") RequestBody name, @Part("password") RequestBody pass);

    @GET("users/{email}")
    Observable<User> getProfile(@Path("email") String email);

}
