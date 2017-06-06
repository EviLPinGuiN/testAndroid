package ru.kpfu.itis.archgis.screen.data.monument;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

import ru.kpfu.itis.archgis.R;

import ru.kpfu.itis.archgis.model.data.general.CulturalLayer;
import ru.kpfu.itis.archgis.model.data.general.Monument;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;

import static ru.kpfu.itis.archgis.utils.Validation.validateFields;


public class MonumentFragment extends BaseFragment implements MonumentView {

    @BindView(R.id.et_name_monument) EditText mEtName;
    @BindView(R.id.ti_name_monument) TextInputLayout mTiName;
    @BindView(R.id.button_next_monument) Button mBtNext;


    public MonumentFragment() {
    }

    public static MonumentFragment newInstance() {
        return new MonumentFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_monument, container, false);
        ButterKnife.bind(this, view);
        mBtNext.setOnClickListener(v -> next());
        return view;
    }


    @Override
    public Monument validatesMonument() {
        setError(mTiName);
        String name = mEtName.getText().toString();
        int err = 0;
        if (!validateFields(name)) {
            err++;
            mTiName.setError("Name should not be empty !");
        }
        if (err == 0) {
            Monument details = new Monument();
            details.setName(name);
            return details;
        } else {
            showSnackBarMessage("Enter Valid Details !");
            return null;
        }
    }

    @Override
    public CulturalLayer getCulturalLayer() {
        return null;
    }
}
