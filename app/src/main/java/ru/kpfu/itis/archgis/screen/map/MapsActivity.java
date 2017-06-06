package ru.kpfu.itis.archgis.screen.map;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.MainActivity;
import ru.kpfu.itis.archgis.screen.advanced.AdvancedSearchActivity;
import ru.kpfu.itis.archgis.screen.auth.StartActivity;
import ru.kpfu.itis.archgis.screen.data.CreateDataActivity;
import ru.kpfu.itis.archgis.screen.quick.QuickSearchActivity;
import ru.kpfu.itis.archgis.utils.ActionBarDrawerToggle;
import ru.kpfu.itis.archgis.utils.PreferencesManager;

import static android.R.id.toggle;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    protected DrawerLayout mDrawer;
    protected Toolbar mToolbar;
    protected NavigationView mNavigationView;
    private AutoCompleteTextView searchView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private TextView appName;
    private boolean isNavigationDrawerOpened;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content_map); //Remember this is the FrameLayout area within your activity_main.xml
//        getLayoutInflater().inflate(R.layout.map_fragment, contentFrameLayout);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mNavigationView = (NavigationView) findViewById(R.id.nav_view_map);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_map);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout_map);
        setSupportActionBar(mToolbar);
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.mainLayout);
        coordinatorLayout.setOnTouchListener((view, motionEvent) -> {
            if (isNavigationDrawerOpened) {
                hideSearchAndEnableAppName();
            }
            return false;
        });
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        searchView = (AutoCompleteTextView) findViewById(R.id.searchView);
        appName = (TextView) findViewById(R.id.appName);
        searchView.setVisibility(View.GONE);
        initNavigationDrawer();
        appName.setOnClickListener(view -> {
            if (!isNavigationDrawerOpened) {
                searchView.setVisibility(View.VISIBLE);
                searchView.requestFocus();
                appName.setVisibility(View.GONE);
                isNavigationDrawerOpened = true;
                animateDrawerIndicator(true);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);

            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


//        for(int i=0;i<10;i++){
//            SearchResult searchResult  = new SearchResult("Manikanta"+i, ContextCompat.getDrawable(NavigationDrawerActivity.this, R.drawable.ic_history));
//            searchResults.add(searchResult);
//        }
//
//        searchAdapter = new SearchAdapter(NavigationDrawerActivity.this,searchResults,searchView);
//        searchView.setAdapter(searchAdapter);
//
//        searchView.setDropDownAnchor(mToolbar.getId());
//        List<String> fragmentTitles = new ArrayList<>();
//        fragmentTitles.add("Tutorials");
//        fragmentTitles.add("Tools");
//        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(),fragmentTitles);
//        viewPager.setAdapter(homeViewPagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    protected void initNavigationDrawer() {
        mNavigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.home:
                    Intent home = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home);
                    mDrawer.closeDrawers();
                    break;
                case R.id.menu_create_data:
                    Intent createData = new Intent(getApplicationContext(), CreateDataActivity.class);
                    startActivity(createData);
                    mDrawer.closeDrawers();
                    break;
                case R.id.menu_quick:
                    Intent quick = new Intent(getApplicationContext(), QuickSearchActivity.class);
                    startActivity(quick);
                    mDrawer.closeDrawers();
                    break;
                case R.id.menu_map:
                    Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(map);
                    mDrawer.closeDrawers();
                    break;
                case R.id.menu_advanced:
                    Intent advanced = new Intent(getApplicationContext(), AdvancedSearchActivity.class);
                    startActivity(advanced);
                    mDrawer.closeDrawers();
                    break;
                case R.id.menu_exit:
                    Intent auth = new Intent(getApplicationContext(), StartActivity.class);
                    startActivity(auth);
                    mDrawer.closeDrawers();
                    break;
            }
            return true;
        });
        View header = mNavigationView.getHeaderView(0);
        final TextView tv_email = (TextView) header.findViewById(R.id.tv_main_email);
        final String mail = PreferencesManager.getUserName(getApplicationContext());
        tv_email.setText(mail);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        actionBarDrawerToggle.setToolbarNavigationClickListener(v -> {
                    if (isNavigationDrawerOpened) {
                        hideSearchAndEnableAppName();
                    } else {
                        actionBarDrawerToggle.toggle();
                    }
        });
        mDrawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else if (isNavigationDrawerOpened) {
            hideSearchAndEnableAppName();
        } else {
            super.onBackPressed();
        }
    }

    private void hideSearchAndEnableAppName() {
        animateDrawerIndicator(false);
        isNavigationDrawerOpened = false;
        searchView.setVisibility(View.GONE);
        appName.setVisibility(View.VISIBLE);
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void animateDrawerIndicator(boolean shouldAnimate) {
        ValueAnimator anim;
        if (shouldAnimate) {
            anim = ValueAnimator.ofFloat(0, 1);
        } else {
            anim = ValueAnimator.ofFloat(1, 0);
        }
        anim.addUpdateListener(valueAnimator -> {
            float slideOffset = (Float) valueAnimator.getAnimatedValue();
            // You should get the drawer layout and
            // toggler from your fragment to make the animation
            actionBarDrawerToggle.onDrawerSlide(mDrawer, slideOffset);
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(500);
        anim.start();
    }


}
