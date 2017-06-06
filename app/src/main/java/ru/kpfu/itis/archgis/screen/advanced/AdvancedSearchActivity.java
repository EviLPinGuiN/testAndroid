package ru.kpfu.itis.archgis.screen.advanced;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.BaseActivity;

public class AdvancedSearchActivity extends BaseActivity {

    public static final int LAYOUT = R.layout.item_add_filter;
    public static final int TITLE = R.id.tv_title_item_filter;
    public static final int REMOVE = R.id.button_remove_item_filter;
    public static final int TEXT = R.string.filter;

    @BindView(R.id.container_filter) LinearLayout mContainerFilter;
    @BindView(R.id.button_add_filter) Button mBtAddFilter;
    @BindView(R.id.sp_search_object) Spinner mSpinnerSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.activity_advanced_search, contentFrameLayout);
        ButterKnife.bind(this);
        setSpinners();
        mBtAddFilter.setOnClickListener(v -> addLayout(mContainerFilter, LAYOUT, TITLE,
                REMOVE,TEXT));

    }



    private void setSpinners() {
        ArrayAdapter<CharSequence> adapterSubject = ArrayAdapter.createFromResource(
                this, R.array.search_spinner_array, android.R.layout.simple_spinner_item);
        adapterSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSearch.setAdapter(adapterSubject);
        mSpinnerSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String subject = mSpinnerSearch.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "The subject is:" + subject , Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addLayout(LinearLayout container, int layout, int title, int remove, int text) {
        int count = 1 + container.getChildCount();
        final View addView = getLayoutInflater().inflate(layout, null);
        TextView textOut = (TextView) addView.findViewById(title);
        textOut.setText(getResources().getString(text) + " №" + count);
        ImageButton imageButton = (ImageButton) addView.findViewById(remove);
        imageButton.setOnClickListener(v -> {
            ((LinearLayout) addView.getParent()).removeView(addView);
            updateChildTitle(container, title, text);
        });
        Spinner spinner = (Spinner) addView.findViewById(R.id.sp_filter);
        setChildSpinner(spinner);
        container.addView(addView);
    }

    private void setChildSpinner(Spinner spinner){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.filter_spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String filter = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), "The filter is:" + filter , Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateChildTitle(LinearLayout layout, int title, int text){
        for(int i = 0; i < layout.getChildCount(); i++){
            int count = i + 1;
            View updateView = layout.getChildAt(i);
            TextView textOut = (TextView) updateView.findViewById(title);
            textOut.setText(getResources().getString(text) + " №" + count);
        }
    }
}
