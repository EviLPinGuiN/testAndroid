package ru.kpfu.itis.archgis.screen.show.author;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.utils.Constants;


/**
 * Created by DNS1 on 04.06.2017.
 */

public class GeneralFragment extends BaseFragment implements GeneralView<Author> {


    @BindView(R.id.tv_name) TextView mTvName;
    @BindView(R.id.tv_birth) TextView mTvBirth;
    @BindView(R.id.tv_bio) TextView mTvBio;
    @BindView(R.id.tv_org) TextView mTvOrg;

    private AuthorShowPresenter presenter;

    public GeneralFragment() {
    }


    public static GeneralFragment newInstance(Long id) {
        GeneralFragment fragment = new GeneralFragment();
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
        presenter = new AuthorShowPresenter(this);
        Long id = getArguments().getLong(Constants.ID_KEY);
        try {
            presenter.init(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }




    @Override
    public void showError(Throwable throwable) {

        Throwable tttrt= throwable;
        System.out.println(tttrt);
        Toast.makeText(getActivity(), R.string.some_error, Toast.LENGTH_LONG).show();
    }



    @Override
    public void showInfo(Author item) {
        if (item.getName() != null)
            mTvName.setText(item.getName());
        if (item.getYear() != null)
            mTvBirth.setText(item.getYear());
        /**wait json in server**/
//        if (item.getName() != null)
//            mTvBio.setText(item.getName());
//        if (item.getName() != null)
//            mTvOrg.setText(item.getName());
    }
}
