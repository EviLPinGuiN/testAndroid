package ru.kpfu.itis.archgis.screen.data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.data.publication.PublicationActivity;
import ru.kpfu.itis.archgis.screen.data.report.ReportActivity;
import ru.kpfu.itis.archgis.utils.Constants;

public class CreateDataActivity extends BaseActivity {

    @BindView(R.id.sp_what)
    Spinner mSpinnerWhat;
    @BindView(R.id.sp_basis)
    Spinner mSpinnerBasis;
    @BindView(R.id.btn_fill)
    Button mButtonFill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.activity_create_data, contentFrameLayout);
        ButterKnife.bind(this);
        setSpinners();
        mButtonFill.setOnClickListener(v -> fill());
    }


    private void setSpinners() {
        List<String> what = new ArrayList<String>();
        List<String> basis = new ArrayList<String>();
        what.add(getResources().getString(R.string.artifact));
        what.add(getResources().getString(R.string.radiocarbon_dating));
        what.add(getResources().getString(R.string.monument));
        what.add(getResources().getString(R.string.research));
        ArrayAdapter<String> adapterWhat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, what);
        adapterWhat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerWhat.setAdapter(adapterWhat);

        ArrayAdapter<String> adapterBasis = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, basis);
        adapterBasis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerWhat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                basis.clear();
                if (selectedItemPosition == 1) {
                    basis.add(getResources().getString(R.string.publication));
                } else {
                    basis.add(getResources().getString(R.string.report));
                    basis.add(getResources().getString(R.string.publication));
                }
                adapterBasis.notifyDataSetChanged();

            }

            public void onNothingSelected(AdapterView<?> parent) {
                basis.add(getResources().getString(R.string.report));
            }
        });

        mSpinnerBasis.setAdapter(adapterBasis);
    }

    private void fill() {
        String what = mSpinnerWhat.getSelectedItem().toString();
        int basisPosition = mSpinnerBasis.getSelectedItemPosition();
        if (what.contentEquals(getResources().getString(R.string.radiocarbon_dating))) {
            basisPosition = 1;
        }
        switch (basisPosition) {
            case 0: {
                Intent intent = new Intent(this, ReportActivity.class);
                intent.putExtra(Constants.CREATE_DATA_EXTRAS, what);
                startActivity(intent);
                break;
            }
            case 1: {
                Intent intent = new Intent(this, PublicationActivity.class);
                intent.putExtra(Constants.CREATE_DATA_EXTRAS, what);
                startActivity(intent);
                break;
            }
            default:
                break;
        }

    }

}
