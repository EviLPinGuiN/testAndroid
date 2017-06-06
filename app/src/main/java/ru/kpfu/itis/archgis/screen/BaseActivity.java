package ru.kpfu.itis.archgis.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.advanced.AdvancedSearchActivity;
import ru.kpfu.itis.archgis.screen.auth.StartActivity;
import ru.kpfu.itis.archgis.screen.data.CreateDataActivity;
import ru.kpfu.itis.archgis.screen.map.MapsActivity;
import ru.kpfu.itis.archgis.screen.map.SearchMapActivity;
import ru.kpfu.itis.archgis.screen.quick.QuickSearchActivity;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.PreferencesManager;

/**
 * Created by DNS1 on 14.05.2017.
 */

public class BaseActivity extends AppCompatActivity {


    protected DrawerLayout mDrawer;
    protected Toolbar mToolbar;
    protected TabLayout mTabLayout;
    protected NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        setSupportActionBar(mToolbar);
        initNavigationDrawer();

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
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    protected void callSearchMapActivivty(@NonNull Activity first){
        Intent intent = new Intent(first, SearchMapActivity.class);
        intent.putExtra(Constants.CALLING_ACTIVITY , first.getClass().getSimpleName());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
