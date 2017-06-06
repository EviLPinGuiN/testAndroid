package ru.kpfu.itis.archgis.screen.quick.excavationlist;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Author;
import ru.kpfu.itis.archgis.model.response.ExcavationResponse;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.common.CommonAdapter;
import ru.kpfu.itis.archgis.screen.common.CommonListView;
import ru.kpfu.itis.archgis.screen.common.LoadingDialog;
import ru.kpfu.itis.archgis.screen.common.LoadingView;
import ru.kpfu.itis.archgis.screen.map.SearchMapActivity;
import ru.kpfu.itis.archgis.utils.BaseAdapter;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.DividerItemDecoration;
import ru.kpfu.itis.archgis.utils.EmptyRecyclerView;

public class ExcavationListActivity extends BaseActivity implements
        CommonListView<ExcavationResponse>, BaseAdapter.OnItemClickListener<ExcavationResponse> {

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.empty)
    View mEmptyView;
    @BindView(R.id.button_show_in_map)
    Button mButton;

    private CommonAdapter<ExcavationResponse> mAdapter;
    private ExcavationListPresenter presenter;
    private LoadingView mLoadingView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.quick_activity_excavation_list, contentFrameLayout);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
        String author = getIntent().getStringExtra(Constants.SEARCH_AUTHOR_EXTRAS);
        String year = getIntent().getStringExtra(Constants.SEARCH_YEAR_EXTRAS);
        mLoadingView = LoadingDialog.view(getSupportFragmentManager());
        presenter = new ExcavationListPresenter(this);
        initRecycler();
        presenter.init(author, year);
        mButton.setOnClickListener(v -> callSearchMapActivivty(this));

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

    private CommonAdapter<ExcavationResponse> getAdapter() {
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
    public void showItems(@NonNull List<ExcavationResponse> items) {
        mButton.setVisibility(View.VISIBLE);
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
    public void showDetails(ExcavationResponse item) {
//        AuthorShowActivity.start(this, item);
    }

    @Override
    public void onItemClick(@NonNull ExcavationResponse item) {
        presenter.onItemClick(item);
    }
}