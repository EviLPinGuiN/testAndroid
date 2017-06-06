package ru.kpfu.itis.archgis.screen.auth;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.kpfu.itis.archgis.R;

public class StartActivity extends AppCompatActivity {


    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity_start);

        if (savedInstanceState == null) {

            loadFragment();
        }
    }

    private void loadFragment() {

        if (mLoginFragment == null) {

            mLoginFragment = new LoginFragment();
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fragmentFrame, mLoginFragment);
        ft.commit();


    }
}
