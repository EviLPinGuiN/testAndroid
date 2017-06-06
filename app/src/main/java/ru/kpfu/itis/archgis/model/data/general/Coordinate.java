package ru.kpfu.itis.archgis.model.data.general;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class Coordinate extends RealmObject implements Serializable{

    private String coord;

    public Coordinate(String coord) {
        this.coord = coord;
    }

    public Coordinate() {
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }
}
