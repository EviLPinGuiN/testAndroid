package ru.kpfu.itis.archgis.model.data.general;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.details.ExcavationItemDetails;
import ru.kpfu.itis.archgis.model.data.details.MonumentDetails;
import ru.kpfu.itis.archgis.model.data.details.PhotoDetails;
import ru.kpfu.itis.archgis.model.data.details.ResearchDetails;
import ru.kpfu.itis.archgis.model.data.details.SimpleChsDetails;
import ru.kpfu.itis.archgis.model.data.details.TopoplanDetails;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class Monument extends RealmObject {


    @PrimaryKey
    private Long id;

    private Research research;

    private RealmList<Excavation> excavations;

    private RealmList<PhotoDetails> photoDetails;

    private RealmList<TopoplanDetails> topoplanDetails;

    private SimpleChsDetails simpleChsDetails;

    /**Monument details **/
    @SerializedName("monName")
    private String name;
    private String mapping;
    private String desc;
    private String N;
    private String E;
    private RealmList<CulturalLayer> layers;

//    private ResearchDetails researchDetails;
//
//    private SimpleChsDetails simpleChsDetails;
//
//    private MonumentDetails monumentDetails;
//
//    private RealmList<ExcavationItemDetails> excavationItemDetails;
//
//    private RealmList<PhotoDetails> photoDetails;
//
//    private RealmList<TopoplanDetails> topoplanDetails;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Research getResearch() {
        return research;
    }

    public void setResearch(Research research) {
        this.research = research;
    }

    public RealmList<Excavation> getExcavations() {
        return excavations;
    }

    public void setExcavations(RealmList<Excavation> excavations) {
        this.excavations = excavations;
    }

    public RealmList<PhotoDetails> getPhotoDetails() {
        return photoDetails;
    }

    public void setPhotoDetails(RealmList<PhotoDetails> photoDetails) {
        this.photoDetails = photoDetails;
    }

    public RealmList<TopoplanDetails> getTopoplanDetails() {
        return topoplanDetails;
    }

    public void setTopoplanDetails(RealmList<TopoplanDetails> topoplanDetails) {
        this.topoplanDetails = topoplanDetails;
    }

    public SimpleChsDetails getSimpleChsDetails() {
        return simpleChsDetails;
    }

    public void setSimpleChsDetails(SimpleChsDetails simpleChsDetails) {
        this.simpleChsDetails = simpleChsDetails;
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
