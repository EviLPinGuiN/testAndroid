package ru.kpfu.itis.archgis.screen.show.author;

import ru.kpfu.itis.archgis.screen.common.LoadingView;

/**
 * Created by DNS1 on 04.06.2017.
 */

public interface GeneralView<T> {

    void showInfo(T item);

    void showError(Throwable t);


}
