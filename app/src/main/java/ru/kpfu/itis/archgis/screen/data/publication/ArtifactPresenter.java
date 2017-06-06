package ru.kpfu.itis.archgis.screen.data.publication;

import ru.kpfu.itis.archgis.screen.data.artifact.ArtifactView;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationView;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentView;
import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.research.ResearchView;

/**
 * Created by DNS1 on 06.06.2017.
 */

public class ArtifactPresenter implements BasePresenter {


    private final ArtifactView artifactView;
    private final MonumentView monumentView;
    private final ExcavationView excavationView;
    private final ResearchView researchView;


    public ArtifactPresenter(ArtifactView artifactView, MonumentView monumentView, ExcavationView excavationView, ResearchView researchView) {
        this.artifactView = artifactView;
        this.monumentView = monumentView;
        this.excavationView = excavationView;
        this.researchView = researchView;
    }

    @Override
    public void save() {

    }
}
