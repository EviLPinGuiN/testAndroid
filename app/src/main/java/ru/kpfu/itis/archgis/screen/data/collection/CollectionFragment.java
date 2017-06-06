package ru.kpfu.itis.archgis.screen.data.collection;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kpfu.itis.archgis.R;

import ru.kpfu.itis.archgis.model.data.general.Collection;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;


import static ru.kpfu.itis.archgis.utils.Validation.validateFields;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends BaseFragment implements CollectionView{


    @BindView(R.id.et_name_collection) EditText mEtName;
    @BindView(R.id.ti_name_collection) TextInputLayout mTiName;
    @BindView(R.id.et_book_collection) EditText mEtBook;
    @BindView(R.id.et_inventory_collection) EditText mEtInventory;
    @BindView(R.id.et_accounting_collection) EditText mEtAccounting;
    @BindView(R.id.et_field_number_collection) EditText mEtFieldNumber;
    @BindView(R.id.et_cipher_collection) EditText mEtCipher;
    @BindView(R.id.et_year_collection) EditText mEtYear;
    @BindView(R.id.button_next_collection) Button mBtNext;




    public CollectionFragment() {
    }

    public static CollectionFragment newInstance() {
        CollectionFragment fragment = new CollectionFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_collection, container, false);
        ButterKnife.bind(this, view);
        mBtNext.setOnClickListener(v -> next());
        return view;
    }


    @Override
    public Collection validatesCollection() {
        setError(mTiName);
        String name = mEtName.getText().toString();
        int err = 0;
        if (!validateFields(name)) {
            err++;
            mTiName.setError("Name should not be empty !");
        }
        if (err == 0) {
            Collection collection = new Collection();
            collection.setName(name);
            collection.setBook(mEtBook.getText().toString());
            collection.setAccounting(mEtAccounting.getText().toString());
            collection.setYear(mEtYear.getText().toString());
            collection.setCipher(mEtCipher.getText().toString());
            collection.setFieldNumber(mEtFieldNumber.getText().toString());
            collection.setInventory(mEtInventory.getText().toString());

            return collection;
        } else {
            showSnackBarMessage("Enter Valid Details !");
            return null;
        }
    }
}
