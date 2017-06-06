package ru.kpfu.itis.archgis.screen.data.report;

import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationView;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentView;
import ru.kpfu.itis.archgis.screen.data.research.ResearchView;

/**
 * Created by DNS1 on 05.06.2017.
 */

public class ResearchPresenter implements BasePresenter {


    private final ResearchView researchView;
    private final MonumentView monumentView;
    private final ExcavationView excavationView;

    public ResearchPresenter(ResearchView researchView, MonumentView monumentView, ExcavationView excavationView) {
        this.researchView = researchView;
        this.monumentView = monumentView;
        this.excavationView = excavationView;
    }

    @Override
    public void save() {

    }
}
