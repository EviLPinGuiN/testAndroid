package ru.kpfu.itis.archgis.screen.data.publication;

import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationView;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentView;
import ru.kpfu.itis.archgis.screen.data.radiocarbon.RadiocarbonView;
import ru.kpfu.itis.archgis.screen.data.research.ResearchView;

/**
 * Created by DNS1 on 06.06.2017.
 */

public class RadiocarbonPresenter implements BasePresenter {



    private final ResearchView researchView;
    private final MonumentView monumentView;
    private final ExcavationView excavationView;
    private final RadiocarbonView radiocarbonView;

    public RadiocarbonPresenter(ResearchView researchView, MonumentView monumentView, ExcavationView excavationView, RadiocarbonView radiocarbonView) {
        this.researchView = researchView;
        this.monumentView = monumentView;
        this.excavationView = excavationView;
        this.radiocarbonView = radiocarbonView;
    }

    @Override
    public void save() {

    }
}
