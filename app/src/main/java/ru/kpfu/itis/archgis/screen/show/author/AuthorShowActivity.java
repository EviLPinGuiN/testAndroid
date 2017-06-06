package ru.kpfu.itis.archgis.screen.show.author;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.adapter.BaseViewPagerAdapter;
import ru.kpfu.itis.archgis.utils.Constants;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class AuthorShowActivity extends BaseActivity {


    @BindView(R.id.vp_author)
    ViewPager mViewPager;

    private AuthorViewPagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.show_activity_author, contentFrameLayout);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        Long id = getIntent().getLongExtra(Constants.ID_KEY, 0L);
        adapter = new AuthorViewPagerAdapter(getSupportFragmentManager());
        adapter.setViewPager(mViewPager, id);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    public static void start(@NonNull Activity activity, @NonNull Author author) {
        Intent intent = new Intent(activity, AuthorShowActivity.class);
        intent.putExtra(Constants.ID_KEY, author.getId());
        activity.startActivity(intent);
    }


    public class AuthorViewPagerAdapter extends BaseViewPagerAdapter {


        public AuthorViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void setViewPager(ViewPager viewPager, Long id) {


            adapter.addFrag(GeneralFragment.newInstance(id), getResources().getString(R.string.general));

            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(adapter.getCount());
        }

    }


}
