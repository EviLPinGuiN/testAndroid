package ru.kpfu.itis.archgis.screen.data.report;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.adapter.BaseViewPagerAdapter;
import ru.kpfu.itis.archgis.screen.data.BaseCreateView;
import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.artifact.ArtifactFragment;
import ru.kpfu.itis.archgis.screen.data.collection.CollectionFragment;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationContainerFragment;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationFragment;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentContainerFragment;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentFragment;
import ru.kpfu.itis.archgis.screen.data.photo.PhotoFragment;
import ru.kpfu.itis.archgis.screen.data.plan.PlanFragment;
import ru.kpfu.itis.archgis.screen.data.research.ResearchFragment;
import ru.kpfu.itis.archgis.screen.data.chs.SimpleChsFragment;
import ru.kpfu.itis.archgis.utils.Constants;

public class ReportActivity extends BaseActivity implements BaseCreateView
//        implements ArtifactPresenter.ArtifactView,
//        CollectionFragment.OnCollectionDetailsCreateListener, MonumentFragment.OnMonumentDetailsCreateListener,
//        ExcavationFragment.OnExcavationDetailsCreateListener, ResearchFragment.OnResearchDetailsCreateListener
{


    @BindView(R.id.vp_report) ViewPager mViewPager;
    @BindView(R.id.button_create_report) Button mBtCreate;


    private ReportViewPagerAdapter adapter;
    private BasePresenter presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.activity_report, contentFrameLayout);
        mTabLayout.setVisibility(View.VISIBLE);

        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }

        String createData = getIntent().getStringExtra(Constants.CREATE_DATA_EXTRAS);
        adapter = new ReportViewPagerAdapter(getSupportFragmentManager());
        adapter.setViewPager(mViewPager, createData);
        mTabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    @OnClick(R.id.button_create_report)
    public void onCreateData() {
        presenter.save();
    }


//    @Override
//    public void showArtifacts(List<Artifact> list) {
//        List<Artifact> a = list;
//        double acv=0;
//    }
//
//    @Override
//    public void showError() {
//
//    }


    public class ReportViewPagerAdapter extends BaseViewPagerAdapter{

        public ReportViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void setViewPager(ViewPager viewPager, String createData) {
            boolean stand = true;

            if (createData.contentEquals(getResources().getString(R.string.artifact))) {
                ResearchFragment researchFragment =  ResearchFragment.newInstance(stand);
                MonumentFragment monumentFragment =  MonumentFragment.newInstance();
                ExcavationFragment excavationFragment =  ExcavationFragment.newInstance();
                CollectionFragment collectionFragment =  CollectionFragment.newInstance();
                ArtifactFragment artifactFragment =  ArtifactFragment.newInstance();
                presenter = new ArtifactPresenter(artifactFragment, collectionFragment, monumentFragment, excavationFragment, researchFragment);
                adapter.addFrag(researchFragment, getResources().getString(R.string.research));
                adapter.addFrag(monumentFragment, getResources().getString(R.string.monument));
                adapter.addFrag(excavationFragment, getResources().getString(R.string.excavation));
                adapter.addFrag(collectionFragment, getResources().getString(R.string.collection));
                adapter.addFrag(artifactFragment, getResources().getString(R.string.artifact));


            }
            if (createData.contentEquals(getResources().getString(R.string.monument))) {
                ResearchFragment researchFragment =  ResearchFragment.newInstance(stand);
                MonumentFragment monumentFragment =  MonumentFragment.newInstance();
                SimpleChsFragment simpleChsFragment =  SimpleChsFragment.newInstance();
                ExcavationContainerFragment excavationFragment =  ExcavationContainerFragment.newInstance();
                PhotoFragment photoFragment =  PhotoFragment.newInstance();
                PlanFragment planFragment =  PlanFragment.newInstance();
                presenter = new MonumentPresenter(researchFragment, simpleChsFragment, monumentFragment, excavationFragment, photoFragment, planFragment);
                adapter.addFrag(researchFragment, getResources().getString(R.string.research));
                adapter.addFrag(simpleChsFragment, getResources().getString(R.string.okn));
                adapter.addFrag(monumentFragment, getResources().getString(R.string.monument));
                adapter.addFrag(excavationFragment, getResources().getString(R.string.excavation));
                adapter.addFrag(photoFragment, getResources().getString(R.string.photo));
                adapter.addFrag(planFragment, getResources().getString(R.string.topoplan));
            }
            if (createData.contentEquals(getResources().getString(R.string.research))) {
                //flag
                ResearchFragment researchFragment =  ResearchFragment.newInstance(!stand);
                MonumentContainerFragment monumentFragment =  MonumentContainerFragment.newInstance();
                ExcavationContainerFragment excavationFragment =  ExcavationContainerFragment.newInstance();
                presenter = new ResearchPresenter(researchFragment, monumentFragment,excavationFragment);
                adapter.addFrag(researchFragment, getResources().getString(R.string.research));
                adapter.addFrag(monumentFragment, getResources().getString(R.string.monument));
                adapter.addFrag(excavationFragment, getResources().getString(R.string.excavation));
            }
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(adapter.getCount());
        }

    }
}




