package ru.kpfu.itis.archgis.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import ru.kpfu.itis.archgis.utils.PreferencesManager;

import static ru.kpfu.itis.archgis.utils.Constants.BASE_URL;
import static ru.kpfu.itis.archgis.utils.Constants.TOKEN_PREFIX;

/**
 * Created by DNS1 on 28.05.2017.
 */

public final class TokenInterceptor implements Interceptor {

    private Context context;

    private TokenInterceptor(Context context){
        this.context = context;
    }

    @NonNull
    public static Interceptor create(Context context) {
        return new TokenInterceptor(context);
    }



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String token = PreferencesManager.getToken(context);
        Headers.Builder header = new Headers.Builder();
        if (token != null && !token.isEmpty()) {
            header.add("Authorization", String.format("Bearer %s", token));
        }
        Request newRequest = request.newBuilder()
                .headers(header.build())
                .build();



        return chain.proceed(newRequest);
    }
}
