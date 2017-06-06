package ru.kpfu.itis.archgis.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.screen.common.ListItem;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class ReportResponse extends RealmObject implements Serializable, ListItem {

    @PrimaryKey
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("year")
    private String year;
    @SerializedName("author")
    private String author;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getEpoch() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public List<String> getX() {
        return null;
    }

    @Override
    public List<String> getY() {
        return null;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
