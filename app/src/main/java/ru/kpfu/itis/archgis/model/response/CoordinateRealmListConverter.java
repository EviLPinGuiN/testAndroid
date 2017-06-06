package ru.kpfu.itis.archgis.model.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import io.realm.RealmList;
import ru.kpfu.itis.archgis.model.data.general.Coordinate;

/**
 * Created by DNS1 on 01.06.2017.
 */

public class CoordinateRealmListConverter implements JsonSerializer<RealmList<Coordinate>>,
        JsonDeserializer<RealmList<Coordinate>> {

    @Override
    public JsonElement serialize(RealmList<Coordinate> src, Type typeOfSrc,
                                 JsonSerializationContext context) {
        JsonArray ja = new JsonArray();
        for (Coordinate coordinate : src) {
            ja.add(context.serialize(coordinate));
        }
        return ja;
    }


    @Override
    public RealmList<Coordinate> deserialize(JsonElement json, Type typeOfT,
                                             JsonDeserializationContext context)
            throws JsonParseException {
        RealmList<Coordinate> tags = new RealmList<>();
        JsonArray ja = json.getAsJsonArray();
        for (JsonElement je : ja) {
            if (je.getAsString().equals("") || je.isJsonNull() || je.getAsString().isEmpty() || je.getAsString() == null)
                tags.add(new Coordinate(""));
            else {
                tags.add(new Coordinate(je.getAsString()));
            }

        }
        return tags;
    }

}
