package ru.kpfu.itis.archgis.screen.data.photo;

import io.realm.RealmList;
import ru.kpfu.itis.archgis.model.data.general.Image;

/**
 * Created by DNS1 on 05.06.2017.
 */

public interface PhotoView {

    RealmList<Image> validatesPhotoList();

}
