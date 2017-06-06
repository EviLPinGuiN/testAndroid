package ru.kpfu.itis.archgis.model.data.general;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.details.ExcavationDetails;
import ru.kpfu.itis.archgis.model.data.details.MonumentDetails;
import ru.kpfu.itis.archgis.model.data.details.RadiocarbonDetails;
import ru.kpfu.itis.archgis.model.data.details.ResearchDetails;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class Radiocarbon extends RealmObject {

    @PrimaryKey
    private Long id;

    private Research research;

    private Monument monument;

    private Excavation excavation;

    private RealmList<RadiocarbonDetails>  radiocarbonDetails;

//    private ResearchDetails researchDetails;
//
//    private MonumentDetails monumentDetails;
//
//    private ExcavationDetails excavationDetails;


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

    public Monument getMonument() {
        return monument;
    }

    public void setMonument(Monument monument) {
        this.monument = monument;
    }

    public Excavation getExcavation() {
        return excavation;
    }

    public void setExcavation(Excavation excavation) {
        this.excavation = excavation;
    }

    public RealmList<RadiocarbonDetails> getRadiocarbonDetails() {
        return radiocarbonDetails;
    }

    public void setRadiocarbonDetails(RealmList<RadiocarbonDetails> radiocarbonDetails) {
        this.radiocarbonDetails = radiocarbonDetails;
    }
}
