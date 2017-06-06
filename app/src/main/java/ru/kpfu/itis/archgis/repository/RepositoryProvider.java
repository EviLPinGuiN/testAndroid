package ru.kpfu.itis.archgis.repository;

import android.support.annotation.NonNull;

import ru.kpfu.itis.archgis.model.data.general.Artifact;
import ru.kpfu.itis.archgis.repository.impl.ArtifactRepository;
import ru.kpfu.itis.archgis.repository.impl.AuthorRepository;
import ru.kpfu.itis.archgis.repository.impl.ChsRepository;
import ru.kpfu.itis.archgis.repository.impl.CollectionRepository;
import ru.kpfu.itis.archgis.repository.impl.CultureRepository;
import ru.kpfu.itis.archgis.repository.impl.ExcavationRepository;
import ru.kpfu.itis.archgis.repository.impl.MonumentRepository;
import ru.kpfu.itis.archgis.repository.impl.RadiocarbonRepository;
import ru.kpfu.itis.archgis.repository.impl.ReportRepository;
import ru.kpfu.itis.archgis.repository.impl.ResearchRepository;

/**
 * Created by DNS1 on 03.06.2017.
 */

public final class RepositoryProvider {

    private static ResearchRepository sResearchRepository;

    private static RadiocarbonRepository sRadiocarbonRepository;

    private static MonumentRepository sMonumentRepository;

    private static ChsRepository sChsRepository;

    private static ExcavationRepository sExcavationRepository;

    private static AuthorRepository sAuthorRepository;

    private static ReportRepository sReportRepository;

    private static ArtifactRepository sArtifactRepository;

    private static CollectionRepository sCollectionRepository;
    private static CultureRepository sCultureRepository;




    private RepositoryProvider() {
    }




    @NonNull
    public static ResearchRepository provideResearchRepository() {
        if (sResearchRepository == null) {
            sResearchRepository = new DefaultResearchRepository();
        }
        return sResearchRepository;
    }

    @NonNull
    public static ArtifactRepository provideArtifactRepository() {
        if (sArtifactRepository == null) {
            sArtifactRepository = new DefaultArtifactRepository();
        }
        return sArtifactRepository;
    }

    @NonNull
    public static RadiocarbonRepository provideRadiocarbonRepository() {
        if (sRadiocarbonRepository == null) {
            sRadiocarbonRepository = new DefaultRadiocarbonRepository();
        }
        return sRadiocarbonRepository;
    }

    @NonNull
    public static MonumentRepository provideMoumentRepository() {
        if (sMonumentRepository == null) {
            sMonumentRepository = new DefaultMonumentRepository();
        }
        return sMonumentRepository;
    }

    @NonNull
    public static ChsRepository provideChsRepository() {
        if (sChsRepository == null) {
            sChsRepository = new DefaultChsRepository();
        }
        return sChsRepository;
    }

    @NonNull
    public static ExcavationRepository provideExcavationRepository() {
        if (sExcavationRepository == null) {
            sExcavationRepository = new DefaultExcavationRepository();
        }
        return sExcavationRepository;
    }

    @NonNull
    public static AuthorRepository provideAuthorRepository() {
        if (sAuthorRepository == null) {
            sAuthorRepository = new DefaultAuthorRepository();
        }
        return sAuthorRepository;
    }

    @NonNull
    public static ReportRepository provideReportRepository() {
        if (sReportRepository == null) {
            sReportRepository = new DefaultReportRepository();
        }
        return sReportRepository;
    }


    public static void setResearchRepository(ResearchRepository repository) {
        sResearchRepository = repository;
    }

}
