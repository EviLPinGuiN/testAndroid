package ru.kpfu.itis.archgis.model.data.details;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.kpfu.itis.archgis.model.data.general.Image;

/**
 * Created by DNS1 on 16.05.2017.
 */

public class RadiocarbonDetails extends RealmObject {

    @PrimaryKey
    private long id;
    private String index;
    private String dateType;
    private String dateBp;
    private String plus;
    private String bc68from;
    private String bc68to;
    private String bc95from;
    private String bc95to;
    private String mapping;
    private String n;
    private String e;
    private String depth;
    private String relative;
    private String area;
    private String object;
    private String stratigraphic;
    private String genesis;
    private String material;
    private String desc;
    private RealmList<Image> photo;

//    public void update(RadiocarbonDetails data) {
//        this.index = data.getIndex();
//        this.dateType = data.getDateType();
//        this.dateBp = data.getDateBp();
//        this.plus = data.getPlus();
//        this.bc68from = data.getBc68from();
//        this.bc68to = data.getBc68to();
//        this.bc95from = data.getBc95from();
//        this.bc95to = data.getBc95to();
//        this.mapping = data.getMapping();
//        this.n = data.getN();
//        this.e = data.getE();
//        this.depth = data.getDepth();
//        this.relative = data.getRelative();
//        this.area = data.getArea();
//        this.object = data.getObject();
//        this.stratigraphic = data.getStratigraphic();
//        this.genesis = data.getGenesis();
//        this.material = data.getMaterial();
//        this.desc = data.getDesc();
//        this.photo = data.getPhoto();
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getDateBp() {
        return dateBp;
    }

    public void setDateBp(String dateBp) {
        this.dateBp = dateBp;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getBc68from() {
        return bc68from;
    }

    public void setBc68from(String bc68from) {
        this.bc68from = bc68from;
    }

    public String getBc68to() {
        return bc68to;
    }

    public void setBc68to(String bc68to) {
        this.bc68to = bc68to;
    }

    public String getBc95from() {
        return bc95from;
    }

    public void setBc95from(String bc95from) {
        this.bc95from = bc95from;
    }

    public String getBc95to() {
        return bc95to;
    }

    public void setBc95to(String bc95to) {
        this.bc95to = bc95to;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getStratigraphic() {
        return stratigraphic;
    }

    public void setStratigraphic(String stratigraphic) {
        this.stratigraphic = stratigraphic;
    }

    public String getGenesis() {
        return genesis;
    }

    public void setGenesis(String genesis) {
        this.genesis = genesis;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public RealmList<Image> getPhoto() {
        return photo;
    }

    public void setPhoto(RealmList<Image> photo) {
        this.photo = photo;
    }
}
