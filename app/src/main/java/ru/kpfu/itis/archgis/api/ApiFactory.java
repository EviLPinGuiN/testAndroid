package ru.kpfu.itis.archgis.api;


import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.realm.RealmList;
import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.kpfu.itis.archgis.App;

import ru.kpfu.itis.archgis.model.data.general.Coordinate;
import ru.kpfu.itis.archgis.model.response.CoordinateRealmListConverter;
import ru.kpfu.itis.archgis.utils.Constants;




public final class ApiFactory {

    private static OkHttpClient sClient;


    private static volatile SearchService searchService;
    private static volatile UserService userService;
    private static volatile ResearchService researchService;
    private static volatile AuthorService authorService;
    private static volatile Gson mgson;


    private ApiFactory() {
    }

    @NonNull
    public static SearchService getQuickSearchService() {
        SearchService service = searchService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = searchService;
                if (service == null) {
                    service = searchService = buildRetrofit().create(SearchService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static AuthorService getAuthorService() {
        AuthorService service = authorService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = authorService;
                if (service == null) {
                    service = authorService = buildRetrofit().create(AuthorService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static ResearchService getResearchService() {
        ResearchService service = researchService;
        Gson gson = mgson;
        if (gson ==null){
            synchronized (ApiFactory.class) {
                gson = mgson;
                if(gson == null){
                    gson = mgson = new GsonBuilder()
                            .registerTypeAdapter(new TypeToken<RealmList<Coordinate>>() {}.getType(),
                                    new CoordinateRealmListConverter())
                            .create();
                }
            }
        }
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = researchService;
                if (service == null) {
                    service = researchService = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .client(getClient())
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build()
                            .create(ResearchService.class);
                }
            }
        }
        return service;
    }

    @NonNull
    public static UserService getUserService() {
        UserService service = userService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = userService;
                if (service == null) {
                    service = userService = buildRetrofit().create(UserService.class);
                }
            }
        }
        return service;
    }


    public static void recreate() {
        sClient = null;
        sClient = getClient();
        searchService = buildRetrofit().create(SearchService.class);

    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }



    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor.create(App.getContext()))
                .build();
    }





}
