package ru.kpfu.itis.archgis.screen.data.monument;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.CulturalLayer;
import ru.kpfu.itis.archgis.model.data.general.Monument;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonumentContainerFragment extends BaseFragment implements MonumentView{


    public static final int LAYOUT = R.layout.item_add_monument;
    public static final int TITLE = R.id.tv_title_item_monument;
    public static final int MORE = R.id.button_more_action_item_monument;
    public static final int SLIDE_LAYOUT = R.id.animate_container_monument_item;
    public static final int TEXT = R.string.monument;


    @BindView(R.id.container_monument) LinearLayout mContainer;
    @BindView(R.id.button_add_monument) Button mBtAddMonument;

    public MonumentContainerFragment() {
    }

    public static MonumentContainerFragment newInstance() {
        MonumentContainerFragment fragment = new MonumentContainerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_monument_container, container, false);
        ButterKnife.bind(this, view);
        mBtAddMonument.setOnClickListener(v -> addLayout(mContainer, LAYOUT, TITLE,
                MORE, SLIDE_LAYOUT, TEXT));
        return view;
    }



    @Override
    public Monument validatesMonument() {
        return null;
    }

    @Override
    public CulturalLayer getCulturalLayer() {
        return null;
    }
}
