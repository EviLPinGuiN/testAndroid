package ru.kpfu.itis.archgis.screen.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.response.MonumentResponse;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.show.author.AuthorShowActivity;
import ru.kpfu.itis.archgis.utils.Constants;

/**
 * Created by DNS1 on 29.05.2017.
 */

public class MonumentShowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        //TODO: 29.05.2017
        getLayoutInflater().inflate(R.layout.activity_quick_search, contentFrameLayout);
        ButterKnife.bind(this);

    }

    public static void start(@NonNull Activity activity, @NonNull MonumentResponse response) {
        Intent intent = new Intent(activity, AuthorShowActivity.class);
        intent.putExtra(Constants.AUTHOR_KEY, response);
        intent.putExtra(Constants.ID_KEY, response.getId());
        activity.startActivity(intent);
    }


}
