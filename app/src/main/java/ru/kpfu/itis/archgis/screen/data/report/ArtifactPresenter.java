package ru.kpfu.itis.archgis.screen.data.report;

import io.realm.RealmList;
import ru.kpfu.itis.archgis.model.data.general.Artifact;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.model.data.general.Collection;
import ru.kpfu.itis.archgis.model.data.general.Excavation;
import ru.kpfu.itis.archgis.model.data.general.Monument;
import ru.kpfu.itis.archgis.model.data.general.Publication;
import ru.kpfu.itis.archgis.model.data.general.Report;
import ru.kpfu.itis.archgis.model.data.general.Research;
import ru.kpfu.itis.archgis.repository.RepositoryProvider;
import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.artifact.ArtifactView;
import ru.kpfu.itis.archgis.screen.data.collection.CollectionView;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationView;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentView;
import ru.kpfu.itis.archgis.screen.data.research.ResearchView;

/**
 * Created by DNS1 on 22.05.2017.
 */

public class ArtifactPresenter implements BasePresenter {


//    private final CommonCreateView view;

    private final ArtifactView artifactView;
    private final CollectionView collectionView;
    private final MonumentView monumentView;
    private final ExcavationView excavationView;
    private final ResearchView researchView;

    public ArtifactPresenter(ArtifactView artifactView, CollectionView collectionView, MonumentView monumentView, ExcavationView excavationView, ResearchView researchView) {
        this.artifactView = artifactView;
        this.collectionView = collectionView;
        this.monumentView = monumentView;
        this.excavationView = excavationView;
        this.researchView = researchView;
    }

    public void save() {


        Artifact artifact = artifactView.validatesData();
        Monument monument = monumentView.validatesMonument();
        Excavation excavation = excavationView.validatesExcavation();
        Collection collection = collectionView.validatesCollection();

        Author author = researchView.validatesAuthor();
        Report report = researchView.validatesReport();
        Publication publication = researchView.validatesPublication();
        RealmList<Author> col = researchView.validatesCollaboratorList();

        Research research = new Research();
        research.setAuthor(author);
        research.setAuthors(col);
        research.setReport(report);
        research.setPublication(publication);

        artifact.setResearch(research);
        artifact.setMonument(monument);
        artifact.setExcavation(excavation);
        artifact.setCollection(collection);

        monument.setResearch(research);

//        Artifact artifact = new Artifact();
//        ArtifactDetails artifactDetails = (ArtifactDetails) object.get(ArtifactFragment.class.getSimpleName());
//        MonumentDetails monumentDetails = (MonumentDetails) object.get(MonumentFragment.class.getSimpleName());
//        ExcavationDetails excavationDetails = (ExcavationDetails) object.get(ExcavationFragment.class.getSimpleName());
//        CollectionDetails collectionDetails = (CollectionDetails) object.get(CollectionFragment.class.getSimpleName());
//        artifact.setArtifactDetails(artifactDetails);
//        artifact.setMonumentDetails(monumentDetails);
//        artifact.setExcavationDetails(excavationDetails);
//        artifact.setCollectionDetails(collectionDetails);
        RepositoryProvider.provideAuthorRepository().saveAuthor(author);
        RepositoryProvider.provideResearchRepository().saveResearch(research);
        RepositoryProvider.provideMoumentRepository().saveMonument(monument);
        RepositoryProvider.provideExcavationRepository().saveExcavation(excavation);
//        RepositoryProvider
//        RepositoryProvider
        RepositoryProvider.provideArtifactRepository().saveArtifact(artifact);
    }


//    public void loadArtifacts() {
//
//        RepositoryProvider.provideArtifactRepository()
//                .getAllArtifacts()
//                .subscribe(artifactView::showArtifacts, throwable -> artifactView.showError());
//
//    }


}
