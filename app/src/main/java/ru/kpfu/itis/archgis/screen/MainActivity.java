package ru.kpfu.itis.archgis.screen;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.advanced.AdvancedSearchActivity;
import ru.kpfu.itis.archgis.screen.data.CreateDataActivity;
import ru.kpfu.itis.archgis.screen.quick.QuickSearchActivity;
import ru.kpfu.itis.archgis.utils.PreferencesManager;


public class MainActivity extends BaseActivity {

    @BindView(R.id.button_create_main) RelativeLayout mBtCreate;
    @BindView(R.id.button_info_main) RelativeLayout mBtInfo;
    @BindView(R.id.button_adv_search_main) RelativeLayout mBtAdv;
    @BindView(R.id.button_quick_search_main) RelativeLayout mBtQuick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.button_adv_search_main)
    public void goAdv(){
        Intent advanced = new Intent(getApplicationContext(), AdvancedSearchActivity.class);
        startActivity(advanced);
    }

    @OnClick(R.id.button_create_main)
    public void goCreate(){
        Intent createData = new Intent(getApplicationContext(), CreateDataActivity.class);
        startActivity(createData);
    }
    @OnClick(R.id.button_info_main)
    public void goInfo(){

    }
    @OnClick(R.id.button_quick_search_main)
    public void goQuick(){
        Intent quick = new Intent(getApplicationContext(), QuickSearchActivity.class);
        startActivity(quick);
    }


}
