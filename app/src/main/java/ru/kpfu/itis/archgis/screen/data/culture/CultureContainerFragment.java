package ru.kpfu.itis.archgis.screen.data.culture;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.CulturalLayer;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;
import ru.kpfu.itis.archgis.screen.data.report.ReportActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CultureContainerFragment extends BaseFragment implements CultureView{

    public static final int LAYOUT = R.layout.item_add_culture;
    public static final int TITLE = R.id.tv_title_item_culture;
    public static final int MORE = R.id.button_more_action_item_culture;
    public static final int SLIDE_LAYOUT = R.id.animate_container_culture_item;
    public static final int TEXT = R.string.culture;
    public static final int DATE = R.array.array_date;
    public static final int SPINNER = R.id.sp_date_culture;


    @BindView(R.id.container_culture) LinearLayout mContainer;
    @BindView(R.id.button_add_culture) Button mBtAddCulture;



    public CultureContainerFragment() {
    }

    public static CultureContainerFragment newInstance() {
        CultureContainerFragment fragment = new CultureContainerFragment();
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_culture_container, container, false);
        ButterKnife.bind(this, view);
        mBtAddCulture.setOnClickListener(v -> addLayoutWithSpinner(mContainer, LAYOUT, TITLE,
                 MORE, SLIDE_LAYOUT, TEXT, SPINNER, DATE));
        return view;
    }

    @Override
    public CulturalLayer validatesCulturalLayer() {
        return null;
    }
}
