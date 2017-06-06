package ru.kpfu.itis.archgis.screen.data.research;

import io.realm.RealmList;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.model.data.general.Publication;
import ru.kpfu.itis.archgis.model.data.general.Report;

/**
 * Created by DNS1 on 05.06.2017.
 */

public interface ResearchView {

    Author validatesAuthor();

    RealmList<Author> validatesCollaboratorList();

    Report validatesReport();

    Publication validatesPublication();

}
