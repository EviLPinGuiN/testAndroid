package ru.kpfu.itis.archgis.screen.common;

import java.util.List;

/**
 * Created by DNS1 on 27.05.2017.
 */

public interface ListItem {

    Long getId();

    String getName();

    String getType();

    String getEpoch();

    String getYear();

    String getAuthor();

    List<String> getX();

    List<String> getY();

}
