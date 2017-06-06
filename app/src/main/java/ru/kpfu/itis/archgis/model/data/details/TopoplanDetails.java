package ru.kpfu.itis.archgis.model.data.details;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.general.Image;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class TopoplanDetails extends RealmObject{

    @PrimaryKey
    private long id;

    private String author;

    private String year;

    private Image plan;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Image getPlan() {
        return plan;
    }

    public void setPlan(Image plan) {
        this.plan = plan;
    }
}
