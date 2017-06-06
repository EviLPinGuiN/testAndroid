package ru.kpfu.itis.archgis.screen.quick.authorlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.common.CommonAdapter;
import ru.kpfu.itis.archgis.screen.common.CommonListView;
import ru.kpfu.itis.archgis.screen.common.LoadingDialog;
import ru.kpfu.itis.archgis.screen.common.LoadingView;
import ru.kpfu.itis.archgis.screen.show.author.AuthorShowActivity;
import ru.kpfu.itis.archgis.utils.BaseAdapter;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.DividerItemDecoration;
import ru.kpfu.itis.archgis.utils.EmptyRecyclerView;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class AuthorListActivity extends BaseActivity implements
        CommonListView<Author>, BaseAdapter.OnItemClickListener<Author> {

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.empty)
    View mEmptyView;


    private LoadingView mLoadingView;
    private CommonAdapter<Author> mAdapter;
    private AuthorListPresenter presenter;
    private String author;
    private String year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.quick_activity_author_list, contentFrameLayout);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        author = getIntent().getStringExtra(Constants.SEARCH_AUTHOR_EXTRAS);
        year = getIntent().getStringExtra(Constants.SEARCH_YEAR_EXTRAS);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        presenter = new AuthorListPresenter(this);
        initRecycler();
        presenter.init(author, year);


    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);
        mAdapter = getAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);
    }

    private CommonAdapter<Author> getAdapter() {
        return new CommonAdapter<>(new ArrayList<>());
    }


    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }


    @Override
    public void showLoading(Disposable disposable) {
        mLoadingView.showLoading(disposable);
    }

    @Override
    public void showItems(@NonNull List<Author> items) {
        mAdapter.changeDataSet(items);
    }


    @Override
    public void showError() {
        Toast.makeText(this, R.string.some_error, Toast.LENGTH_LONG).show();
        mAdapter.clear();
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showDetails(Author item) {
        AuthorShowActivity.start(this, item);
    }

    @Override
    public void onItemClick(@NonNull Author item) {
        presenter.onItemClick(item);
    }

}
