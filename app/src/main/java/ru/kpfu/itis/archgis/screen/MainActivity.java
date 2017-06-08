package ru.kpfu.itis.archgis.screen;



import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.MainIcons;
import ru.kpfu.itis.archgis.screen.adapter.MainRecycleView;
import ru.kpfu.itis.archgis.screen.advanced.AdvancedSearchActivity;
import ru.kpfu.itis.archgis.screen.data.CreateDataActivity;
import ru.kpfu.itis.archgis.screen.quick.QuickSearchActivity;
import ru.kpfu.itis.archgis.utils.widget.GridSpacingItemDecoration;
import ru.kpfu.itis.archgis.utils.PreferencesManager;


public class MainActivity extends BaseActivity {

//    @BindView(R.id.button_create_main) RelativeLayout mBtCreate;
//    @BindView(R.id.button_info_main) RelativeLayout mBtInfo;
//    @BindView(R.id.button_adv_search_main) RelativeLayout mBtAdv;
//    @BindView(R.id.button_quick_search_main) RelativeLayout mBtQuick;
    @BindView(R.id.rv_menu) RecyclerView recyclerView;

    private MainRecycleView adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
        ButterKnife.bind(this);
        initRecycle();
        String token = PreferencesManager.getToken(this);
        Toast.makeText(this, token, Toast.LENGTH_LONG).show();

    }


    private void initRecycle(){
        adapter = new MainRecycleView(MainIcons.getMainIcons());
        GridLayoutManager  gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(5), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setListener(position -> startActivity(position));
    }

    private void startActivity(int position){
        switch (position){
            case 0 : {
                Intent createData = new Intent(getApplicationContext(), CreateDataActivity.class);
                startActivity(createData);
                break;
            }
            case 1 : {
                break;
            }
            case 2 : {
                Intent quick = new Intent(getApplicationContext(), QuickSearchActivity.class);
                startActivity(quick);
                break;
            }
            case 3 : {
                Intent advanced = new Intent(getApplicationContext(), AdvancedSearchActivity.class);
                startActivity(advanced);
                break;
            }
            default:return;
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

//    @OnClick(R.id.button_adv_search_main)
//    public void goAdv(){
//        Intent advanced = new Intent(getApplicationContext(), AdvancedSearchActivity.class);
//        startActivity(advanced);
//    }
//
//    @OnClick(R.id.button_create_main)
//    public void goCreate(){
//        Intent createData = new Intent(getApplicationContext(), CreateDataActivity.class);
//        startActivity(createData);
//    }
//    @OnClick(R.id.button_info_main)
//    public void goInfo(){
//
//    }
//    @OnClick(R.id.button_quick_search_main)
//    public void goQuick(){
//        Intent quick = new Intent(getApplicationContext(), QuickSearchActivity.class);
//        startActivity(quick);
//    }


}
