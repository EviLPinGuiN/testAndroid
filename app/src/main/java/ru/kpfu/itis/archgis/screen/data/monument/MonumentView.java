package ru.kpfu.itis.archgis.screen.data.monument;


import ru.kpfu.itis.archgis.model.data.general.CulturalLayer;
import ru.kpfu.itis.archgis.model.data.general.Monument;

/**
 * Created by DNS1 on 05.06.2017.
 */

public interface MonumentView {

    Monument validatesMonument();

    CulturalLayer getCulturalLayer();
}
