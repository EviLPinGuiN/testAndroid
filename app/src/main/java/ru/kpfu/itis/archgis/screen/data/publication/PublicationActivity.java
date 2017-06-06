package ru.kpfu.itis.archgis.screen.data.publication;


import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.adapter.BaseViewPagerAdapter;
import ru.kpfu.itis.archgis.screen.data.BaseCreateView;
import ru.kpfu.itis.archgis.screen.data.culture.CultureContainerFragment;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationContainerFragment;
import ru.kpfu.itis.archgis.screen.data.excavation.ExcavationPubFragment;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentContainerFragment;
import ru.kpfu.itis.archgis.screen.data.artifact.ArtifactFragment;
import ru.kpfu.itis.archgis.screen.data.monument.MonumentFragment;
import ru.kpfu.itis.archgis.screen.data.plan.PlanFragment;
import ru.kpfu.itis.archgis.screen.data.radiocarbon.RadiocarbonFragment;
import ru.kpfu.itis.archgis.screen.data.BasePresenter;
import ru.kpfu.itis.archgis.screen.data.research.ResearchFragment;
import ru.kpfu.itis.archgis.utils.Constants;

public class PublicationActivity extends BaseActivity implements BaseCreateView {


    @BindView(R.id.vp_pub) ViewPager mViewPager;
    @BindView(R.id.button_create_pub) AppCompatButton button;

    private PublicationViewPagerAdapter adapter;

    private BasePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.activity_publication, contentFrameLayout);
        mTabLayout.setVisibility(View.VISIBLE);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        String createData = getIntent().getStringExtra(Constants.CREATE_DATA_EXTRAS);
        adapter = new PublicationViewPagerAdapter(getSupportFragmentManager());
        adapter.setViewPager(mViewPager, createData);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    @OnClick(R.id.button_create_pub)
    public void onCreateData() {
        presenter.save();
    }


    public class PublicationViewPagerAdapter extends BaseViewPagerAdapter {


        public PublicationViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void setViewPager(ViewPager viewPager, String createData) {

            boolean stand = true;

            if (createData.contentEquals(getResources().getString(R.string.artifact))) {
                ResearchFragment researchFragment =  ResearchFragment.newInstance(stand);
                MonumentFragment monumentFragment =  MonumentFragment.newInstance();
                ExcavationPubFragment excavationFragment =  ExcavationPubFragment.newInstance();
                ArtifactFragment artifactFragment =  ArtifactFragment.newInstance();
                presenter = new ArtifactPresenter(artifactFragment, monumentFragment, excavationFragment,researchFragment);
                adapter.addFrag(researchFragment, getResources().getString(R.string.research));
                adapter.addFrag(monumentFragment, getResources().getString(R.string.monument));
                adapter.addFrag(excavationFragment, getResources().getString(R.string.excavation));
                adapter.addFrag(artifactFragment, getResources().getString(R.string.artifact));
            }
            if (createData.contentEquals(getResources().getString(R.string.radiocarbon_dating))) {
                ResearchFragment researchFragment =  ResearchFragment.newInstance(stand);
                MonumentFragment monumentFragment =  MonumentFragment.newInstance();
                ExcavationPubFragment excavationFragment =  ExcavationPubFragment.newInstance();
                RadiocarbonFragment radiocarbonFragment = RadiocarbonFragment.newInstance();
                presenter = new RadiocarbonPresenter(researchFragment, monumentFragment, excavationFragment,radiocarbonFragment);
                adapter.addFrag(researchFragment, getResources().getString(R.string.research));
                adapter.addFrag(monumentFragment, getResources().getString(R.string.monument));
                adapter.addFrag(excavationFragment, getResources().getString(R.string.excavation));
                adapter.addFrag(radiocarbonFragment, getResources().getString(R.string.radiocarbon_dating));

            }
            if (createData.contentEquals(getResources().getString(R.string.monument))) {
                ResearchFragment researchFragment =  ResearchFragment.newInstance(stand);
                MonumentFragment monumentFragment =  MonumentFragment.newInstance();
                ExcavationContainerFragment excavationFragment =  ExcavationContainerFragment.newInstance();
                PlanFragment planFragment =  PlanFragment.newInstance();
                presenter = new MonumentPresenter(researchFragment,monumentFragment,excavationFragment,planFragment);
                adapter.addFrag(researchFragment, getResources().getString(R.string.research));
                adapter.addFrag(monumentFragment, getResources().getString(R.string.monument));
                adapter.addFrag(excavationFragment, getResources().getString(R.string.excavation));
                adapter.addFrag(planFragment, getResources().getString(R.string.topoplan));
            }
            if (createData.contentEquals(getResources().getString(R.string.research))) {
                ResearchFragment researchFragment =  ResearchFragment.newInstance(stand);
                MonumentFragment monumentFragment =  MonumentFragment.newInstance();
                ExcavationPubFragment excavationFragment =  ExcavationPubFragment.newInstance();
                CultureContainerFragment cultureContainerFragment  = CultureContainerFragment.newInstance();
                presenter = new ResearchPresenter(researchFragment,monumentFragment,excavationFragment,cultureContainerFragment);
                adapter.addFrag(researchFragment, getResources().getString(R.string.research));
                adapter.addFrag(monumentFragment, getResources().getString(R.string.monument));
                adapter.addFrag(excavationFragment, getResources().getString(R.string.excavation));
                adapter.addFrag(cultureContainerFragment, getResources().getString(R.string.cultures));
            }
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(adapter.getCount());
        }

    }
}
