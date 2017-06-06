package ru.kpfu.itis.archgis.model.data.general;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.screen.common.ListItem;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class Author extends RealmObject implements ListItem,Serializable {

    @PrimaryKey
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("birth")
    private String year;


//    public void update(Author date){
//        this.name = date.getName();
//    }

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

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public List<String> getX() {
        return null;
    }

    @Override
    public List<String> getY() {
        return null;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
