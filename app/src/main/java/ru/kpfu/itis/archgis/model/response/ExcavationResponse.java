package ru.kpfu.itis.archgis.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.screen.common.ListItem;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class ExcavationResponse extends RealmObject implements Serializable,ListItem{

    @PrimaryKey
    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("author")
    private String author;
    @SerializedName("x")
    private String n;
    @SerializedName("y")
    private String e;
    @SerializedName("resYear")
    private String year;

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
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public List<String> getX() {
        List<String> list  = new ArrayList<>();
        if(getN()!=null){
            list.add(getN());
        }
        return list;
    }

    @Override
    public List<String> getY() {
        List<String> list  = new ArrayList<>();
        if(getE()!=null){
            list.add(getE());
        }
        return list;
    }

    @Override
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
