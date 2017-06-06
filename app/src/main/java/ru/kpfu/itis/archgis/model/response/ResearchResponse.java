package ru.kpfu.itis.archgis.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.general.Coordinate;
import ru.kpfu.itis.archgis.screen.common.ListItem;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class ResearchResponse extends RealmObject implements Serializable, ListItem {

    @PrimaryKey
    @SerializedName("resId")
    private Long id;
    @SerializedName("resName")
    private String name;
    @SerializedName("resYear")
    private String year;
    @SerializedName("resTypeName")
    private String type;
    @SerializedName("x")
    private RealmList<Coordinate> xList;
    @SerializedName("y")
    private RealmList<Coordinate> yList;
    @SerializedName("autName")
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
    public String getType() {
        return type;
    }

    @Override
    public String getEpoch() {
        return null;
    }

    public void setType(String type) {
        this.type = type;
    }


    public RealmList<Coordinate> getxList() {
        return xList;
    }

    public void setxList(RealmList<Coordinate> xList) {
        this.xList = xList;
    }

    public RealmList<Coordinate> getyList() {
        return yList;
    }

    public void setyList(RealmList<Coordinate> yList) {
        this.yList = yList;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public List<String> getX() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < xList.size(); i++) {
            if (xList.get(i).getCoord() != null)
                list.add(xList.get(i).getCoord());
        }
        return list;
    }

    @Override
    public List<String> getY() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < yList.size(); i++) {
            if (yList.get(i).getCoord() != null)
                list.add(yList.get(i).getCoord());
        }
        return list;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
