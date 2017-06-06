package ru.kpfu.itis.archgis.screen.data.chs;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;
import ru.kpfu.itis.archgis.screen.data.report.ReportActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleChsFragment extends BaseFragment implements ChsView{


    @BindView(R.id.et_simple_chs) EditText mEtName;
    @BindView(R.id.button_next_simple_chs) Button mBtNext;


    public SimpleChsFragment() {
    }


    public static SimpleChsFragment newInstance() {
        SimpleChsFragment fragment = new SimpleChsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_simple_chs, container, false);
        ButterKnife.bind(this, view);
        mBtNext.setOnClickListener(v -> next());
        return view;
    }




}
