package ru.kpfu.itis.archgis.screen.map;

import java.util.List;

import ru.kpfu.itis.archgis.model.response.ChsResponse;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;
import ru.kpfu.itis.archgis.model.response.RadiocarbonDate;
import ru.kpfu.itis.archgis.model.response.RadiocarbonResponse;
import ru.kpfu.itis.archgis.model.response.ResearchResponse;
import ru.kpfu.itis.archgis.screen.common.LoadingView;

/**
 * Created by DNS1 on 03.06.2017.
 */

public interface SearchMapView extends LoadingView {


    void loadChsResponse(List<ChsResponse> responses);

    void loadMonumentResponse(List<MonumentResponse> responses);

    void loadExcavationResponse(List<ExcavationResponse> responses);

    void loadRadiocarbonResponse(List<RadiocarbonDate> responses);

    void loadResearchResponse(List<ResearchResponse> responses);

}
