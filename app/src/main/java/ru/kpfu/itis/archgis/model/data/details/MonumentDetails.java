package ru.kpfu.itis.archgis.model.data.details;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.general.CulturalLayer;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class MonumentDetails extends RealmObject {


    @PrimaryKey
    private long id;
    @SerializedName("monName")
    private String name;

    private String mapping;

    private String desc;

    private String N;

    private String E;

    private RealmList<CulturalLayer> layers;

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

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getN() {
        return N;
    }

    public void setN(String n) {
        N = n;
    }

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public RealmList<CulturalLayer> getLayers() {
        return layers;
    }

    public void setLayers(RealmList<CulturalLayer> layers) {
        this.layers = layers;
    }
}
