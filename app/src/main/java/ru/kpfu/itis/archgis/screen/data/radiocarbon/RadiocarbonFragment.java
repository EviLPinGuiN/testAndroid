package ru.kpfu.itis.archgis.screen.data.radiocarbon;


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
import ru.kpfu.itis.archgis.screen.data.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadiocarbonFragment extends BaseFragment implements RadiocarbonView{


    public static final int LAYOUT = R.layout.item_add_radiocarbon;
    public static final int TITLE = R.id.tv_title_item_radiocarbon;
    public static final int MORE = R.id.button_more_action_item_radiocarbon;
    public static final int SLIDE_LAYOUT = R.id.animate_container_radiocarbon_item;
    public static final int TEXT = R.string.radiocarbon_dating;
    public static final int MAPPING = R.array.array_mapping;
    public static final int SPINNER = R.id.sp_mapping_radiocarbon;


    @BindView(R.id.container_radiocarbon_dating) LinearLayout mContainer;
    @BindView(R.id.button_add_radiocarbon_dating) Button mBtAddRadiocarbon;

    public RadiocarbonFragment() {
    }

    public static RadiocarbonFragment newInstance() {
        RadiocarbonFragment fragment = new RadiocarbonFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_radiocarbon, container, false);
        ButterKnife.bind(this, view);
        mBtAddRadiocarbon.setOnClickListener(v -> addLayoutWithSpinner(mContainer, LAYOUT, TITLE,
                MORE, SLIDE_LAYOUT, TEXT, SPINNER, MAPPING));
        return view;
    }


}
