package ru.kpfu.itis.archgis.screen.show.author;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.kpfu.itis.archgis.R;

/**
 * Created by DNS1 on 04.06.2017.
 */

public abstract class BaseFragment extends Fragment {


    protected ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        viewPager = (ViewPager) getActivity().findViewById(R.id.vp_author);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
