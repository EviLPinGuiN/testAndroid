package ru.kpfu.itis.archgis.model.data.details;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.general.Image;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class PhotoDetails extends RealmObject {


    @PrimaryKey
    private long id;
    private String monumentElement;
    private String foreshortening;
    private Image photo;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMonumentElement() {
        return monumentElement;
    }

    public void setMonumentElement(String monumentElement) {
        this.monumentElement = monumentElement;
    }

    public String getForeshortening() {
        return foreshortening;
    }

    public void setForeshortening(String foreshortening) {
        this.foreshortening = foreshortening;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }
}
