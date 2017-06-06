package ru.kpfu.itis.archgis.model.data.details;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.model.data.general.Publication;
import ru.kpfu.itis.archgis.model.data.general.Report;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class ResearchDetails extends RealmObject{

    @PrimaryKey
    private long id;

    private Author author;
    private RealmList<Author> authors;

    private Report report;

    private String contentType;
    private String contentDesc;


    private Publication publication;



    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
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
}
