package ru.kpfu.itis.archgis.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import ru.kpfu.itis.archgis.screen.common.ListItem;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class MonumentResponse extends RealmObject implements ListItem, Serializable {

    @SerializedName("monId")
    private Long id;
    @SerializedName("en_cult")
    private String enCult;
    @SerializedName("x")
    private String n;
    @SerializedName("en_monType")
    private String enMonType;
    @SerializedName("epName")
    private String epoch;
    @SerializedName("y")
    private String e;
    @SerializedName("en_epName")
    private String enEpName;
    @SerializedName("ep")
    private Integer ep;
    @SerializedName("monType")
    private String type;
    @SerializedName("kId")
    private Integer kId;
    @SerializedName("autName")
    private String author;
    @SerializedName("cult")
    private String cult;
    @SerializedName("monTypeId")
    private Integer monTypeId;
    @SerializedName("monName")
    private String name;
    @SerializedName("resYear")
    private String year;


    @Override
    public Long getId() {
        return id;
    }


    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getEpoch() {
        return epoch;
    }

    @Override
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (getE()!= null){
            list.add(getE());
        }
        return list;
    }

    public String getEnCult() {
        return enCult;
    }

    public void setEnCult(String enCult) {
        this.enCult = enCult;
    }

    public String getEnMonType() {
        return enMonType;
    }

    public void setEnMonType(String enMonType) {
        this.enMonType = enMonType;
    }

    public String getEnEpName() {
        return enEpName;
    }

    public void setEnEpName(String enEpName) {
        this.enEpName = enEpName;
    }

    public Integer getEp() {
        return ep;
    }

    public void setEp(Integer ep) {
        this.ep = ep;
    }

    public Integer getkId() {
        return kId;
    }

    public void setkId(Integer kId) {
        this.kId = kId;
    }

    public String getCult() {
        return cult;
    }

    public void setCult(String cult) {
        this.cult = cult;
    }

    public Integer getMonTypeId() {
        return monTypeId;
    }

    public void setMonTypeId(Integer monTypeId) {
        this.monTypeId = monTypeId;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
