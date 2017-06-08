package ru.kpfu.itis.archgis.screen.quick;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.BaseActivity;
import ru.kpfu.itis.archgis.screen.quick.authorlist.AuthorListActivity;
import ru.kpfu.itis.archgis.screen.quick.chslist.ChsListActivity;
import ru.kpfu.itis.archgis.screen.quick.excavationlist.ExcavationListActivity;
import ru.kpfu.itis.archgis.screen.quick.monumentlist.MonumentListActivity;
import ru.kpfu.itis.archgis.screen.quick.radiocarbonlist.RadiocarbonListActivity;
import ru.kpfu.itis.archgis.screen.quick.reportlist.ReportListActivity;
import ru.kpfu.itis.archgis.screen.quick.researchlist.ResearchListActivity;
import ru.kpfu.itis.archgis.utils.Constants;

public class QuickSearchActivity extends BaseActivity{

    @BindView(R.id.button_quick_show_result) Button mBtResult;
    @BindView(R.id.sp_quick_search_object) Spinner mSpEntity;
    @BindView(R.id.sp_epoch_monument_quick) Spinner mSpEpoch;
    @BindView(R.id.tv_epoch_monument_quick) TextView mTvEpoch;
    @BindView(R.id.sp_type_monument_quick) Spinner mSpType;
    @BindView(R.id.tv_type_monument_quick) TextView mTvType;
    @BindView(R.id.et_name_monument_quick) EditText mEtNameMonument;
    @BindView(R.id.ti_name_monument_quick) TextInputLayout mTiNameMonument;
    @BindView(R.id.et_name_author_research_quick) EditText mEtAuthorResearch;
    @BindView(R.id.ti_name_author_research_quick) TextInputLayout mTiAuthorResearch;
    @BindView(R.id.et_year_research_quick) EditText mEtYearResearch;
    @BindView(R.id.ti_year_research_quick) TextInputLayout mTiYearResearch;
    @BindView(R.id.et_name_author_quick) EditText mEtAuthor;
    @BindView(R.id.ti_name_author_quick) TextInputLayout mTiAuthor;
    @BindView(R.id.et_name_author_report_quick) EditText mEtAuthorReport;
    @BindView(R.id.ti_name_author_report_quick) TextInputLayout mTiAuthorReport;
    @BindView(R.id.et_year_report_quick) EditText mEtYearReport;
    @BindView(R.id.ti_year_report_quick) TextInputLayout mTiYearReport;
    @BindView(R.id.et_name_chs_quick) EditText mEtChs;
    @BindView(R.id.ti_name_chs_quick) TextInputLayout mTiChs;
    @BindView(R.id.et_name_author_excavation_quick) EditText  mEtAuthorExcavation;
    @BindView(R.id.ti_name_author_excavation_quick) TextInputLayout  mTiAuthorExcavation;
    @BindView(R.id.et_year_research_excavation_quick) EditText  mEtYearExcavation;
    @BindView(R.id.ti_year_research_excavation_quick) TextInputLayout  mTiYearExcavation;
    @BindView(R.id.et_index_radiocarbon_quick) EditText  mEtIndex;
    @BindView(R.id.ti_index_radiocarbon_quick) TextInputLayout  mTiIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.activity_content);
        getLayoutInflater().inflate(R.layout.activity_quick_search, contentFrameLayout);
        ButterKnife.bind(this);
//        presenter = new QuickSearchPresenter(this);

        setEntity();
    }

    private void setEntity(){
        ArrayAdapter<CharSequence> adapterEntity = ArrayAdapter.createFromResource(
                this, R.array.search_spinner_array_quick, android.R.layout.simple_spinner_item);
        adapterEntity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterEpoch = ArrayAdapter.createFromResource(
                this, R.array.epoch, android.R.layout.simple_spinner_item);
        adapterEpoch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                this, R.array.monument_type, android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpEntity.setAdapter(adapterEntity);
        mSpEpoch.setAdapter(adapterEpoch);
        mSpType.setAdapter(adapterType);
        mSpEntity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.monument))){
                    setGone();
                    mTiNameMonument.setVisibility(View.VISIBLE);
                    mEtNameMonument.setVisibility(View.VISIBLE);
                    mTvEpoch.setVisibility(View.VISIBLE);
                    mTvType.setVisibility(View.VISIBLE);
                    mSpEpoch.setVisibility(View.VISIBLE);
                    mSpType.setVisibility(View.VISIBLE);
                }
                if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.research))){
                    setGone();
                    mTiAuthorResearch.setVisibility(View.VISIBLE);
                    mEtAuthorResearch.setVisibility(View.VISIBLE);
                    mTiYearResearch.setVisibility(View.VISIBLE);
                    mEtYearResearch.setVisibility(View.VISIBLE);
                }
                if (mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.author))){
                    setGone();
                    mTiAuthor.setVisibility(View.VISIBLE);
                    mEtAuthor.setVisibility(View.VISIBLE);
                }
                if (mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.report))){
                    setGone();
                    mTiAuthorReport.setVisibility(View.VISIBLE);
                    mEtAuthorReport.setVisibility(View.VISIBLE);
                    mTiYearReport.setVisibility(View.VISIBLE);
                    mEtYearReport.setVisibility(View.VISIBLE);
                }
                if (mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.okn))){
                    setGone();
                    mTiChs.setVisibility(View.VISIBLE);
                    mEtChs.setVisibility(View.VISIBLE);
                }
                if (mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.excavation))){
                    setGone();
                    mTiAuthorExcavation.setVisibility(View.VISIBLE);
                    mEtAuthorExcavation.setVisibility(View.VISIBLE);
                    mTiYearExcavation.setVisibility(View.VISIBLE);
                    mEtYearExcavation.setVisibility(View.VISIBLE);
                }
                if (mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.radiocarbon_dating))){
                    setGone();
                    mTiIndex.setVisibility(View.VISIBLE);
                    mEtIndex.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setGone(){
        mTiNameMonument.setVisibility(View.GONE);
        mEtNameMonument.setVisibility(View.GONE);
        mSpEpoch.setVisibility(View.GONE);
        mSpType.setVisibility(View.GONE);
        mTvEpoch.setVisibility(View.GONE);
        mTvType.setVisibility(View.GONE);
        mTiAuthorResearch.setVisibility(View.GONE);
        mEtAuthorResearch.setVisibility(View.GONE);
        mTiYearResearch.setVisibility(View.GONE);
        mEtYearResearch.setVisibility(View.GONE);
        mTiAuthor.setVisibility(View.GONE);
        mEtAuthor.setVisibility(View.GONE);
        mTiAuthorReport.setVisibility(View.GONE);
        mEtAuthorReport.setVisibility(View.GONE);
        mTiYearReport.setVisibility(View.GONE);
        mEtYearReport.setVisibility(View.GONE);
        mTiChs.setVisibility(View.GONE);
        mEtChs.setVisibility(View.GONE);
        mTiAuthorExcavation.setVisibility(View.GONE);
        mEtAuthorExcavation.setVisibility(View.GONE);
        mTiYearExcavation.setVisibility(View.GONE);
        mEtYearExcavation.setVisibility(View.GONE);
        mTiIndex.setVisibility(View.GONE);
        mEtIndex.setVisibility(View.GONE);
    }


    @OnClick(R.id.button_quick_show_result)
    public void quickSearch(){
        if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.author))){
            Intent intent = new Intent(this, AuthorListActivity.class);
            String author = mEtAuthor.getText().toString();
            String year="";
            intent.putExtra(Constants.SEARCH_AUTHOR_EXTRAS, author);
            intent.putExtra(Constants.SEARCH_YEAR_EXTRAS, year);
            startActivity(intent);
        }
        if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.excavation))){
            Intent intent = new Intent(this, ExcavationListActivity.class);
            String author = mEtAuthorExcavation.getText().toString();
            String year = "";
            if (mEtYearExcavation.getText() != null){
                year = mEtYearExcavation.getText().toString();
            }
            intent.putExtra(Constants.SEARCH_AUTHOR_EXTRAS, author);
            intent.putExtra(Constants.SEARCH_YEAR_EXTRAS, year);
            startActivity(intent);
        }
        if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.okn))){
            Intent intent = new Intent(this, ChsListActivity.class);
            String index = mEtChs.getText().toString();
            intent.putExtra(Constants.SEARCH_NAME_EXTRAS, index);
            startActivity(intent);
        }
        if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.monument))){
            Intent intent = new Intent(this, MonumentListActivity.class);
            String monument = mEtNameMonument.getText().toString();
            String epoch = "";
            if (mSpType.getSelectedItemPosition()!=0){
                 epoch = String.valueOf(mSpEpoch.getSelectedItemPosition());
            }
            String type = "";
            if (mSpType.getSelectedItemPosition()!=0){
                 type= String.valueOf(mSpType.getSelectedItemPosition());
            }
            intent.putExtra(Constants.SEARCH_MONUMENT_EXTRAS, monument);
            intent.putExtra(Constants.SEARCH_TYPE_EXTRAS, type);
            intent.putExtra(Constants.SEARCH_EPOCH_EXTRAS, epoch);
            startActivity(intent);
        }
        if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.radiocarbon_dating))){
            Intent intent = new Intent(this, RadiocarbonListActivity.class);
            String index = mEtIndex.getText().toString();
            intent.putExtra(Constants.SEARCH_NAME_EXTRAS, index);
            startActivity(intent);
        }
        if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.report))){
            Intent intent = new Intent(this, ReportListActivity.class);
            String author = mEtAuthorReport.getText().toString();
            String year = "";
            if (mEtYearReport.getText() != null){
                year = mEtYearReport.getText().toString();
            }
            intent.putExtra(Constants.SEARCH_AUTHOR_EXTRAS, author);
            intent.putExtra(Constants.SEARCH_YEAR_EXTRAS, year);
            startActivity(intent);
        }
        if(mSpEntity.getSelectedItem().toString().equals(getResources().getString(R.string.research))){
            Intent intent = new Intent(this, ResearchListActivity.class);
            String author = mEtAuthorResearch.getText().toString();
            String year = "";
            if (mEtYearResearch.getText() != null){
                year = mEtYearResearch.getText().toString();
            }
            intent.putExtra(Constants.SEARCH_AUTHOR_EXTRAS, author);
            intent.putExtra(Constants.SEARCH_YEAR_EXTRAS, year);
            startActivity(intent);
        }
    }





}
