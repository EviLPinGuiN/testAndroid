package ru.kpfu.itis.archgis.screen.data.research;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.model.data.details.ResearchDetails;
import ru.kpfu.itis.archgis.model.data.general.Publication;
import ru.kpfu.itis.archgis.model.data.general.Report;
import ru.kpfu.itis.archgis.screen.adapter.AuthorFilterableRealmAdapter;
import ru.kpfu.itis.archgis.screen.adapter.AuthorSearchRecyclerViewAdapter;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;
import ru.kpfu.itis.archgis.screen.data.publication.PublicationActivity;
import ru.kpfu.itis.archgis.screen.data.report.ReportActivity;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.DividerItemDecoration;

import static ru.kpfu.itis.archgis.utils.Validation.validateFields;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResearchFragment extends BaseFragment implements ResearchView {

    public static final int LAYOUT = R.layout.item_add_collaborator;
    public static final int TITLE = R.id.tv_title_item_coll;
    public static final int SLIDE_LAYOUT = R.id.animate_container_coll_item;
    public static final int MORE_MENU = R.id.button_more_action_item_coll;
    public static final int TEXT = R.string.collaborator;

    @BindView(R.id.container_author)
    LinearLayout mContainerAuthor;
    //    @BindView(R.id.ti_author) TextInputLayout mTiAuthor;
//    @BindView(R.id.et_author) EditText mEtAuthor;
    @BindView(R.id.button_add_coll)
    Button mBtAddColl;
    @BindView(R.id.button_next_research)
    Button mBtNext;
    @BindView(R.id.ti_date_research)
    TextInputLayout mTiDateReport;
    @BindView(R.id.et_date_research)
    EditText mEtDateReport;
    @BindView(R.id.ti_report_research_vis)
    TextInputLayout mTiVisReport;
    @BindView(R.id.et_report_research_vis)
    EditText mEtVisReport;
    @BindView(R.id.layout_stub)
    ViewStub mVsLayout;
    @BindView(R.id.rv_author)
    RecyclerView mRecyclerView;
    @BindView(R.id.searchView)
    AutoCompleteTextView mAutoCompleteTextView;

    private TextInputLayout mTiReport;
    private EditText mEtReport;
    private TextView mTvReport;
    private TextInputLayout mTiDescReport;
    private CheckBox mCbReport;

    private AuthorSearchRecyclerViewAdapter adapter;
    private Realm realm;

    private AuthorFilterableRealmAdapter filterableRealmAdapter;


    private boolean stand;


    public ResearchFragment() {
    }

    public static ResearchFragment newInstance(boolean id) {
        ResearchFragment fragment = new ResearchFragment();
        Bundle args = new Bundle();
        args.putBoolean(Constants.ID_KEY, id);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_research, container, false);
        ButterKnife.bind(this, view);
        stand = getArguments().getBoolean(Constants.ID_KEY);
        initLayout();
        mBtAddColl.setOnClickListener(v -> addLayout(mContainerAuthor, LAYOUT, TITLE,
                MORE_MENU, SLIDE_LAYOUT, TEXT));
        mBtNext.setOnClickListener(v -> next());
        realm = Realm.getDefaultInstance();
        initRecycler();
//        filterableRealmAdapter = new AuthorFilterableRealmAdapter(getActivity(), realm.where(Author.class).findAll(), mAutoCompleteTextView );
//        mAutoCompleteTextView.setAdapter(filterableRealmAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void initRecycler() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new AuthorSearchRecyclerViewAdapter(realm, realm.where(Author.class).findAll());
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setHasFixedSize(true);

    }


    private void initLayout() {

        if (stand) {
            mVsLayout.setLayoutResource(R.layout.container_research_content_stand);
            View inflated = mVsLayout.inflate();
            mTiReport = (TextInputLayout) inflated.findViewById(R.id.ti_report_research);
            mEtReport = (EditText) inflated.findViewById(R.id.et_report_research);
            mTvReport = (TextView) inflated.findViewById(R.id.tv_report_research);
            if (getActivity().getLocalClassName().contains(PublicationActivity.class.getSimpleName())) {
                mTvReport.setText(R.string.research_subtitle_pub);
                mTiReport.setHint(getResources().getString(R.string.research_tv_pub_title));
            }
        } else {
            mVsLayout.setLayoutResource(R.layout.container_research_content);
            View inflated = mVsLayout.inflate();
            mTiDateReport.setVisibility(View.VISIBLE);
            mTiVisReport.setVisibility(View.VISIBLE);
            mCbReport = (CheckBox) inflated.findViewById(R.id.cb_add_research);
            mTiDescReport = (TextInputLayout) inflated.findViewById(R.id.ti_desc_research);
            mCbReport.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    mTiDescReport.setVisibility(View.VISIBLE);
                } else {
                    mTiDescReport.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public Author validatesAuthor() {
//        setError(mTiAuthor);
//        String name = mEtAuthor.getText().toString();
//        int err = 0;
//        if (!validateFields(name)) {
//            err++;
//            mTiAuthor.setError("Author should not be empty !");
//        }
//        if (!validateFields(mEtReport.getText().toString())) {
//            err++;
//            mTiDateReport.setError("Report should not be empty !");
//        }
//
//        if (err == 0) {
//
//            Author author = new Author();
//            author.setName(name);
//
//            return author;
//        } else {
//            showSnackBarMessage("Enter Valid Details !");
        return null;
//        }
    }

    @Override
    public RealmList<Author> validatesCollaboratorList() {
        return null;
    }

    @Override
    public Report validatesReport() {
        return null;
    }

    @Override
    public Publication validatesPublication() {
        return null;
    }
}
