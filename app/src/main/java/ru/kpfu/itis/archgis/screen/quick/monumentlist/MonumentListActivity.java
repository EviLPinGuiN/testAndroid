package ru.kpfu.itis.archgis.screen.quick.monumentlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.common.CommonAdapter;
import ru.kpfu.itis.archgis.screen.common.CommonListView;
import ru.kpfu.itis.archgis.screen.common.LoadingDialog;
import ru.kpfu.itis.archgis.screen.common.LoadingView;
import ru.kpfu.itis.archgis.screen.map.SearchMapActivity;
import ru.kpfu.itis.archgis.screen.show.MonumentShowActivity;
import ru.kpfu.itis.archgis.utils.BaseAdapter;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.DividerItemDecoration;
import ru.kpfu.itis.archgis.utils.EmptyRecyclerView;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class MonumentListActivity extends BaseActivity implements
        CommonListView<MonumentResponse>, BaseAdapter.OnItemClickListener<MonumentResponse> {

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.empty)
    View mEmptyView;

    private CommonAdapter<MonumentResponse> mAdapter;
    private MonumentListPresenter presenter;
    private LoadingView mLoadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.quick_activity_monument_list, contentFrameLayout);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        String monument = getIntent().getStringExtra(Constants.SEARCH_MONUMENT_EXTRAS);
        String type = getIntent().getStringExtra(Constants.SEARCH_TYPE_EXTRAS);
        String epoch = getIntent().getStringExtra(Constants.SEARCH_EPOCH_EXTRAS);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        presenter = new MonumentListPresenter(this);
        initRecycler();
        presenter.init(monument, epoch, type);
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

    private CommonAdapter<MonumentResponse> getAdapter() {
        return new CommonAdapter<>(new ArrayList<>());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_map) {
            callSearchMapActivivty(this);
        }
        return super.onOptionsItemSelected(item);
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
    public void showItems(@NonNull List<MonumentResponse> items) {
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
    public void showDetails(MonumentResponse item) {
        MonumentShowActivity.start(this, item);
    }

    @Override
    public void onItemClick(@NonNull MonumentResponse item) {
        presenter.onItemClick(item);
    }



}
