package ru.kpfu.itis.archgis.screen.data.excavation;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.details.ExcavationDetails;
import ru.kpfu.itis.archgis.model.data.general.Excavation;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;
import ru.kpfu.itis.archgis.screen.data.report.ReportActivity;

import static ru.kpfu.itis.archgis.utils.Validation.validateFields;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExcavationFragment extends BaseFragment implements ExcavationView{

    public static final int MAPPING = R.array.array_mapping;

    @BindView(R.id.ti_name_excavation) TextInputLayout mTiName;
    @BindView(R.id.et_name_excavation) EditText mEtName;
    @BindView(R.id.et_supervisor_excavation) EditText mEtSupervisor;
    @BindView(R.id.et_area_excavation) EditText mEtArea;
    @BindView(R.id.et_find_excavation) EditText mEtFind;
    @BindView(R.id.et_depth_excavation) EditText mEtDepth;
    @BindView(R.id.et_feature_excavation) EditText mEtFeature;
    @BindView(R.id.sp_mapping_excavation) Spinner mSpMapping;
    @BindView(R.id.et_n_excavation) EditText mEtN;
    @BindView(R.id.et_e_excavation) EditText mEtE;
    @BindView(R.id.button_next_excavation) Button mBtNext;


    public ExcavationFragment() {
    }

    public static ExcavationFragment newInstance() {
        ExcavationFragment fragment = new ExcavationFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_excavation, container, false);
        ButterKnife.bind(this, view);
        mBtNext.setOnClickListener(v->next());
        setSpinners(mSpMapping, MAPPING);
        return view;
    }



    @Override
    public Excavation validatesExcavation() {
        setError(mTiName);
        String name = mEtName.getText().toString();
        int err = 0;
        if (!validateFields(name)) {
            err++;
            mTiName.setError("Name should not be empty !");
        }
        if (err == 0) {
            Excavation excavation = new Excavation();
            excavation.setName(name);
            excavation.setSupervisor(mEtSupervisor.getText().toString());
            excavation.setFind(mEtFind.getText().toString());
            excavation.setArea(mEtArea.getText().toString());
            excavation.setDepth(mEtDepth.getText().toString());
            excavation.setFeature(mEtFeature.getText().toString());
            excavation.setMapping(mSpMapping.getSelectedItem().toString());
            excavation.setE(mEtE.getText().toString());
            excavation.setN(mEtN.getText().toString());
            return excavation;
        } else {
            showSnackBarMessage("Enter Valid Details !");
            return null;
        }
    }
}
