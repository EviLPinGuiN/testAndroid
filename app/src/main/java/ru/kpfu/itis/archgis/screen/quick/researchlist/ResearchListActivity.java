package ru.kpfu.itis.archgis.screen.quick.researchlist;

import android.support.annotation.NonNull;
import android.os.Bundle;
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
import ru.kpfu.itis.archgis.model.response.ResearchResponse;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.common.CommonAdapter;
import ru.kpfu.itis.archgis.screen.common.CommonListView;
import ru.kpfu.itis.archgis.screen.common.LoadingDialog;
import ru.kpfu.itis.archgis.screen.common.LoadingView;
import ru.kpfu.itis.archgis.utils.widget.BaseAdapter;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.widget.DividerItemDecoration;
import ru.kpfu.itis.archgis.utils.widget.EmptyRecyclerView;

public class ResearchListActivity extends BaseActivity implements CommonListView<ResearchResponse>,
        BaseAdapter.OnItemClickListener<ResearchResponse> {

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.empty)
    View mEmptyView;

    private CommonAdapter<ResearchResponse> mAdapter;
    private ResearchListPresenter presenter;
    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.quick_activity_research_list, contentFrameLayout);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        String author = getIntent().getStringExtra(Constants.SEARCH_AUTHOR_EXTRAS);
        String year = getIntent().getStringExtra(Constants.SEARCH_YEAR_EXTRAS);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        presenter = new ResearchListPresenter(this);
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

    private CommonAdapter<ResearchResponse> getAdapter() {
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
        if(id == R.id.action_map){
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
    public void showItems(@NonNull List<ResearchResponse> items) {
        List<ResearchResponse> resp = items;
        double a = 1;
        System.out.println(a);
        mAdapter.changeDataSet(resp);
    }


    @Override
    public void showError() {
        Toast.makeText(this, R.string.some_error, Toast.LENGTH_LONG).show();
        mAdapter.clear();
    }

    @Override
    public void showError(Throwable tr) {
        Throwable tttrt= tr;
        System.out.println(tttrt);
        Toast.makeText(this, R.string.some_error, Toast.LENGTH_LONG).show();
        mAdapter.clear();
    }

    @Override
    public void showDetails(ResearchResponse item) {
//        AuthorShowActivity.start(this, item);
    }

    @Override
    public void onItemClick(@NonNull ResearchResponse item) {
        presenter.onItemClick(item);
    }
}
