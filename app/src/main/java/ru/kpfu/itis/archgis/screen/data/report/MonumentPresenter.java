package ru.kpfu.itis.archgis.screen.data.report;

import ru.kpfu.itis.archgis.model.data.general.Monument;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.chs.ChsView;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationView;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentView;
import ru.kpfu.itis.archgis.screen.data.photo.PhotoView;
import ru.kpfu.itis.archgis.screen.data.plan.PlanView;
import ru.kpfu.itis.archgis.screen.data.research.ResearchView;

/**
 * Created by DNS1 on 05.06.2017.
 */

public class MonumentPresenter implements BasePresenter {


    private final ResearchView researchView;
    private final ChsView chsView;
    private final MonumentView monumentView;
    private final ExcavationView excavationView;
    private final PhotoView photoView;
    private final PlanView planView;


    public MonumentPresenter(ResearchView researchView, ChsView chsView, MonumentView monumentView, ExcavationView excavationView, PhotoView photoView, PlanView planView) {
        this.researchView = researchView;
        this.chsView = chsView;
        this.monumentView = monumentView;
        this.excavationView = excavationView;
        this.photoView = photoView;
        this.planView = planView;
    }

    @Override
    public void save() {

        Monument monument = new Monument();


        RepositoryProvider.provideMoumentRepository().saveMonument(monument);


    }
}
