package ru.kpfu.itis.archgis.screen.show.author;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.utils.Constants;

/**
 * Created by DNS1 on 08.06.2017.
 */

public class PublicationFragment extends BaseFragment{


    public PublicationFragment() {
    }


    public static PublicationFragment newInstance(Long id) {
        PublicationFragment fragment = new PublicationFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.ID_KEY, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.show_fragment_author, container, false);
        ButterKnife.bind(this, view);

        Long id = getArguments().getLong(Constants.ID_KEY);

        return view;
    }

}
