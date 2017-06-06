package ru.kpfu.itis.archgis.screen.data.publication;

import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.culture.CultureView;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationView;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentView;
import ru.kpfu.itis.archgis.screen.data.research.ResearchView;

/**
 * Created by DNS1 on 06.06.2017.
 */

public class ResearchPresenter implements BasePresenter {


    private final ResearchView researchView;
    private final MonumentView monumentView;
    private final ExcavationView excavationView;
    private final CultureView cultureView;

    public ResearchPresenter(ResearchView researchView, MonumentView monumentView, ExcavationView excavationView, CultureView cultureView) {
        this.researchView = researchView;
        this.monumentView = monumentView;
        this.excavationView = excavationView;
        this.cultureView = cultureView;
    }

    @Override
    public void save() {

    }
}
