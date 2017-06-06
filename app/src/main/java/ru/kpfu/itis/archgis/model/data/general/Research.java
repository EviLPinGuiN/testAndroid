package ru.kpfu.itis.archgis.model.data.general;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.details.ExcavationItemDetails;
import ru.kpfu.itis.archgis.model.data.details.MonumentDetails;
import ru.kpfu.itis.archgis.model.data.details.ResearchDetails;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class Research extends RealmObject {

    @PrimaryKey
    private Long id;

    /**Research details**/
    private Author author;
    private RealmList<Author> authors;
    private Report report;
    private String contentType;
    private String contentDesc;

    private Publication publication;

    private RealmList<Monument> monument;

    private RealmList<Excavation> excavations;

    private RealmList<CulturalLayer> culturalLayers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public RealmList<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(RealmList<Author> authors) {
        this.authors = authors;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public RealmList<Monument> getMonument() {
        return monument;
    }

    public void setMonument(RealmList<Monument> monument) {
        this.monument = monument;
    }

    public RealmList<Excavation> getExcavations() {
        return excavations;
    }

    public void setExcavations(RealmList<Excavation> excavations) {
        this.excavations = excavations;
    }

    public RealmList<CulturalLayer> getCulturalLayers() {
        return culturalLayers;
    }

    public void setCulturalLayers(RealmList<CulturalLayer> culturalLayers) {
        this.culturalLayers = culturalLayers;
    }
}
