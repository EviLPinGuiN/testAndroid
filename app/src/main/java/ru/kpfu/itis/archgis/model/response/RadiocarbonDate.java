package ru.kpfu.itis.archgis.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import ru.kpfu.itis.archgis.screen.common.ListItem;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class RadiocarbonDate extends RealmObject {

    @SerializedName("carbon")
    private RadiocarbonResponse radiocarbonResponse;
    @SerializedName("monX")
    private String x;
    @SerializedName("monY")
    private String y;


    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public RadiocarbonResponse getRadiocarbonResponse() {
        radiocarbonResponse.setN(getX());
        radiocarbonResponse.setE(getY());
        return radiocarbonResponse;
    }

    public void setRadiocarbonResponse(RadiocarbonResponse radiocarbonResponse) {
        this.radiocarbonResponse = radiocarbonResponse;
    }
}
