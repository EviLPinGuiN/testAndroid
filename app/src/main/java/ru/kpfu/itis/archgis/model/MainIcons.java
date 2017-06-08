package ru.kpfu.itis.archgis.model;

import java.util.ArrayList;
import java.util.List;

import ru.kpfu.itis.archgis.R;

/**
 * Created by DNS1 on 08.06.2017.
 */

public class MainIcons {


    private int name;

    private int icon;

    public MainIcons(int name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public static final List<MainIcons> getMainIcons(){
        List<MainIcons> list = new ArrayList<>();
        list.add(new MainIcons(R.string.menu_create_data, R.drawable.ic_create_list));
        list.add(new MainIcons(R.string.info, R.drawable.ic_info_black_48dp));
        list.add(new MainIcons(R.string.menu_quick_search, R.drawable.ic_search_black_48dp));
        list.add(new MainIcons(R.string.menu_advanced_search, R.drawable.ic_zoom_in_black_48dp));
        return list;

    }



    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
