package ru.kpfu.itis.archgis;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.jakewharton.picasso.OkHttp3Downloader;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by DNS1 on 15.05.2017.
 */

public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = this;

        setupRealm();

        setupPicasso();

//        RepositoryProvider.init();
    }

    private void setupPicasso() {
        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this))
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    private void setupRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }


    @NonNull
    public static Context getContext() {
        return sContext;
    }

}
