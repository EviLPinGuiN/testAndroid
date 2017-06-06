package ru.kpfu.itis.archgis.screen.data.excavation;


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
import ru.kpfu.itis.archgis.model.data.general.Excavation;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExcavationContainerFragment extends BaseFragment implements ExcavationView{


    public static final int LAYOUT = R.layout.item_add_excavation;
    public static final int TITLE = R.id.tv_title_item_excavation;
    public static final int MORE = R.id.button_more_action_item_excavation;
    public static final int SLIDE_LAYOUT = R.id.animate_container_excavation_item;
    public static final int TEXT = R.string.excavation;
    public static final int MAPPING = R.array.array_mapping;
    public static final int SPINNER = R.id.sp_mapping_excavation_item;


    @BindView(R.id.container_excavation) LinearLayout mContainer;
    @BindView(R.id.button_add_excavation) Button mBtAddExcavation;




    public ExcavationContainerFragment() {
    }

    public static ExcavationContainerFragment newInstance() {
        ExcavationContainerFragment fragment = new ExcavationContainerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_excavation_container, container, false);
        ButterKnife.bind(this, view);
        mBtAddExcavation.setOnClickListener(v -> addLayoutWithSpinner(mContainer, LAYOUT, TITLE,
                MORE, SLIDE_LAYOUT, TEXT, SPINNER, MAPPING));
        return view;
    }


    @Override
    public Excavation validatesExcavation() {
        return null;
    }
}
