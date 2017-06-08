package ru.kpfu.itis.archgis.screen.quick.reportlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.response.ReportResponse;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.common.CommonAdapter;
import ru.kpfu.itis.archgis.screen.common.CommonListView;
import ru.kpfu.itis.archgis.screen.common.LoadingDialog;
import ru.kpfu.itis.archgis.screen.common.LoadingView;
import ru.kpfu.itis.archgis.utils.widget.BaseAdapter;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.widget.DividerItemDecoration;
import ru.kpfu.itis.archgis.utils.widget.EmptyRecyclerView;

/**
 * Created by DNS1 on 30.05.2017.
 */

public class ReportListActivity extends BaseActivity implements
        CommonListView<ReportResponse>, BaseAdapter.OnItemClickListener<ReportResponse> {

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.empty)
    View mEmptyView;

    private CommonAdapter<ReportResponse> mAdapter;
    private ReportListPresenter presenter;
    private LoadingView mLoadingView;
    private String author;
    private String year;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.quick_activity_report_list, contentFrameLayout);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
         author = getIntent().getStringExtra(Constants.SEARCH_AUTHOR_EXTRAS);
         year = getIntent().getStringExtra(Constants.SEARCH_YEAR_EXTRAS);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        presenter = new ReportListPresenter(this);
        initRecycler();
        presenter.init(author,year);


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

    private CommonAdapter<ReportResponse> getAdapter() {
        return new CommonAdapter<>(new ArrayList<>());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
    public void showItems(@NonNull List<ReportResponse> items) {
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
    public void showDetails(ReportResponse item) {
//        AuthorShowActivity.start(this, item);
    }

    @Override
    public void onItemClick(@NonNull ReportResponse item) {
        presenter.onItemClick(item);
    }
}
