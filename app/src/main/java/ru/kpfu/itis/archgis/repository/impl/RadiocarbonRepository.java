package ru.kpfu.itis.archgis.repository.impl;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;
import ru.kpfu.itis.archgis.model.data.general.Radiocarbon;
import ru.kpfu.itis.archgis.model.response.RadiocarbonDate;
import ru.kpfu.itis.archgis.model.response.RadiocarbonResponse;

/**
 * Created by DNS1 on 16.05.2017.
 */

public interface RadiocarbonRepository {

    void saveRadiocarbon(Radiocarbon radiocarbon);

    Radiocarbon getRadiocarbonById(long id);

    List<Radiocarbon> getAllRadiocarbons(Realm realm);


    Observable<List<RadiocarbonDate>> radiocarbons(String name);

    Observable<List<RadiocarbonDate>> getAllRadiocarbonResponse();
}
