package ru.kpfu.itis.archgis.screen.data.plan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;
import ru.kpfu.itis.archgis.screen.data.SelectImage;


public class PlanFragment extends BaseFragment implements SelectImage,PlanView {

    public static final int LAYOUT = R.layout.item_add_topoplan;
    public static final int TITLE = R.id.tv_title_item_plan;
    public static final int IMAGE = R.id.iv_pick_plan_item;
    public static final int PICK = R.id.button_pick_plan;
    public static final int MORE = R.id.button_more_action_item_plan;
    public static final int SLIDE_LAYOUT = R.id.animate_container_plan_item;
    public static final int TEXT = R.string.topoplan;


    @BindView(R.id.container_plan) LinearLayout mContainer;
    @BindView(R.id.button_add_plan) Button mBtAddPlan;

    public PlanFragment() {
    }

    public static PlanFragment newInstance() {
        PlanFragment fragment = new PlanFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_plan, container, false);
        ButterKnife.bind(this, view);
        selectImage = this;
        mBtAddPlan.setOnClickListener(v -> addLayout(mContainer,
                LAYOUT, TITLE,
                MORE, SLIDE_LAYOUT,
                TEXT,
                PICK, IMAGE));
        return view;
    }

    @Override
    public void galleryIntent(ImageView imageView) {

    }

    @Override
    public void cameraIntent(ImageView imageView) {

    }


}
