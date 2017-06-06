package ru.kpfu.itis.archgis.screen.data.excavation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Excavation;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;


public class ExcavationPubFragment extends BaseFragment implements ExcavationView{

    @BindView(R.id.et_author_excavation_pub) EditText mEtAuthor;
    @BindView(R.id.et_date_excavation_pub) EditText mEtDate;
    @BindView(R.id.et_name_excavation_pub) EditText mEtName;
    @BindView(R.id.button_next_excavation_pub) Button mBtNext;

    public ExcavationPubFragment() {
    }

    public static ExcavationPubFragment newInstance() {
        ExcavationPubFragment fragment = new ExcavationPubFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_excavation_pub, container, false);
        ButterKnife.bind(this, view);
        mBtNext.setOnClickListener(v -> next());
        return view;
    }


    @Override
    public Excavation validatesExcavation() {
        return null;
    }
}
