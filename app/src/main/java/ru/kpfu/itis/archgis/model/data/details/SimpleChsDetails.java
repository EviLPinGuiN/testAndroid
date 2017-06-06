package ru.kpfu.itis.archgis.model.data.details;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class SimpleChsDetails extends RealmObject {

    @PrimaryKey
    private long id;

    private String name;

    private String desc;

    private String security;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
}
