package ru.kpfu.itis.archgis.model.data.general;

import io.realm.RealmObject;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class Image extends RealmObject{

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
