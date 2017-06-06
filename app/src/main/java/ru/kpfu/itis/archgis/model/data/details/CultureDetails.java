package ru.kpfu.itis.archgis.model.data.details;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class CultureDetails extends RealmObject{

    @PrimaryKey
    private long id;

    private String name;

    private String date;

    private String from;

    private String to;

    private String desc;



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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
