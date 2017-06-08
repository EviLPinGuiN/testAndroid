package ru.kpfu.itis.archgis.screen.data.artifact;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Artifact;
import ru.kpfu.itis.archgis.model.data.general.Image;
import ru.kpfu.itis.archgis.model.data.details.ArtifactDetails;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;
import ru.kpfu.itis.archgis.screen.data.SelectImage;
import ru.kpfu.itis.archgis.screen.data.report.ReportActivity;

import static android.app.Activity.RESULT_OK;
import static ru.kpfu.itis.archgis.utils.Validation.validateFields;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtifactFragment extends BaseFragment implements SelectImage, ArtifactView {

    public static final int LAYOUT = R.layout.item_add_one_photo;
    public static final int TITLE = R.id.tv_title_item_one_photo;
    public static final int IMAGE = R.id.iv_pick_one_photo_item;
    public static final int PICK = R.id.button_pick_one_photo;
    public static final int MORE = R.id.button_more_action_item_one_photo;
    public static final int SLIDE_LAYOUT = R.id.animate_container_one_photo_item;
    public static final int TEXT = R.string.one_photo;
    public static final int MAPPING = R.array.array_mapping;
    public static final int DATE = R.array.array_date;

    @BindView(R.id.container_one_photo) LinearLayout mContainerOnePhoto;
    @BindView(R.id.button_pick_photo_artifact) Button mBtAddPhoto;
    @BindView(R.id.et_name_artifact) EditText mEtName;
    @BindView(R.id.ti_name_artifact) TextInputLayout mTiName;
    @BindView(R.id.et_year_artifact) EditText mEtYear;
    @BindView(R.id.et_category_artifact) EditText mEtCategory;
    @BindView(R.id.et_material_artifact) EditText mEtMaterial;
    @BindView(R.id.sp_date_artifact) Spinner mSpDate;
    @BindView(R.id.et_from_artifact) EditText mEtFrom;
    @BindView(R.id.et_to_artifact) EditText mEtTo;
    @BindView(R.id.et_attribution_artifact) EditText mEtAttribution;
    @BindView(R.id.sp_mapping_artifact) Spinner mSpMapping;
    @BindView(R.id.et_n_artifact) EditText mEtN;
    @BindView(R.id.et_e_artifact) EditText mEtE;
    @BindView(R.id.et_desc_artifact) EditText mEtDesc;

    private HashMap<ImageView, String> imageHashMap;
    private List<ImageView> imageViewList;
    private int imageViewIndex;




    public ArtifactFragment() {
    }

    public static ArtifactFragment newInstance() {
        ArtifactFragment fragment = new ArtifactFragment();
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container,savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_artifact, container, false);
        ButterKnife.bind(this, view);
        selectImage = this;
        imageHashMap = new HashMap<>();
        imageViewList = new ArrayList<>();
//        presenter = new ArtifactPresenter(this);

        mBtAddPhoto.setOnClickListener(v -> addLayout(mContainerOnePhoto,
                LAYOUT, TITLE,
                MORE, SLIDE_LAYOUT,
                TEXT,
                PICK, IMAGE));
        setSpinners(mSpMapping, MAPPING);
        setSpinners(mSpDate, DATE);
        return view;
    }

//    public void onSomeClick(ArtifactDetails data) {
//        listener.getArtifactDetails(data);
//    }




    @Override
    public void galleryIntent(ImageView imageView) {
        if (!imageViewList.contains(imageView)){
            imageViewList.add(imageView);
            imageViewIndex = imageViewList.indexOf(imageView);
        }
        else {
            imageViewIndex = imageViewList.indexOf(imageView);
        }
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void cameraIntent(ImageView imageView) {
        if (!imageViewList.contains(imageView)){
            imageViewList.add(imageView);
            imageViewIndex = imageViewList.indexOf(imageView);
        }
        else {
            imageViewIndex = imageViewList.indexOf(imageView);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            }
            else if (requestCode == REQUEST_CAMERA){
                onCaptureImageResult(data);
                onSelectFromGalleryResult(data);
            }

        }

    }


    private void onSelectFromGalleryResult(Intent data){
        Uri returnUri = data.getData();
        String path = returnUri.getPath();
        ImageView temp = imageViewList.get(imageViewIndex);
        temp.setVisibility(View.VISIBLE);
        Picasso.with(getActivity()).load(returnUri).noPlaceholder().centerCrop().fit()
                .into(temp);
        if (!imageHashMap.containsKey(imageViewList.get(imageViewIndex))){
            imageHashMap.put(imageViewList.get(imageViewIndex) , path);
        }
        else {
            imageHashMap.replace(imageViewList.get(imageViewIndex), path);
        }

    }


    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public Artifact validatesData() {
        setError(mTiName);
        String name = mEtName.getText().toString();
        int err = 0;
        if (!validateFields(name)) {
            err++;
            mTiName.setError("Name should not be empty !");
        }
        if (err == 0) {
            Artifact details = new Artifact();
            details.setName(name);
            details.setYear(mEtYear.getText().toString());
            details.setCategory(mEtCategory.getText().toString());
            details.setMaterial(mEtMaterial.getText().toString());
            details.setDate(mSpDate.getSelectedItem().toString());
            details.setFrom(mEtFrom.getText().toString());
            details.setTo(mEtTo.getText().toString());
            details.setAttribution(mEtAttribution.getText().toString());
            details.setMapping(mSpMapping.getSelectedItem().toString());
            details.setN(mEtN.getText().toString());
            details.setE(mEtE.getText().toString());
            details.setDesc(mEtDesc.getText().toString());
            RealmList<Image> imageRealmList = new RealmList<>();
            for (ImageView view : imageViewList) {
                Image image = new Image();
                String path = imageHashMap.get(view);
                image.setPath(path);
                imageRealmList.add(image);
            }
            details.setImages(imageRealmList);

            return details;

        } else {
            showSnackBarMessage("Enter Valid Details !");
            return null;
        }
    }
}
