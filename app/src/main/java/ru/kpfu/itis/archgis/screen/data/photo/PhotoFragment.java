package ru.kpfu.itis.archgis.screen.data.photo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmObject;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.data.general.Image;
import ru.kpfu.itis.archgis.screen.data.BaseFragment;
import ru.kpfu.itis.archgis.screen.data.SelectImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends BaseFragment implements SelectImage,PhotoView {


    public static final int LAYOUT = R.layout.item_add_photo;
    public static final int TITLE = R.id.tv_title_item_photo;
    public static final int IMAGE = R.id.iv_pick_photo_item;
    public static final int PICK = R.id.button_pick_photo;
    public static final int MORE = R.id.button_more_action_item_photo;
    public static final int SLIDE_LAYOUT = R.id.animate_container_photo_item;
    public static final int TEXT = R.string.photo;
    public static final int FORESHORTENING = R.array.photo_array_foreshortening;
    public static final int SPINNER = R.id.sp_foreshortening_photo;


    @BindView(R.id.container_photo) LinearLayout mContainer;
    @BindView(R.id.button_add_photo) Button mBtAddPhoto;


    public PhotoFragment() {
    }

    public static PhotoFragment newInstance() {
        PhotoFragment fragment = new PhotoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.constructor_fragment_photo, container, false);
        ButterKnife.bind(this, view);
        selectImage = this;
        mBtAddPhoto.setOnClickListener(v -> addLayoutWithSpinner(mContainer,
                LAYOUT, TITLE,
                MORE, SLIDE_LAYOUT,
                TEXT,
                PICK, IMAGE,
                SPINNER,FORESHORTENING));

        return view;
    }

    @Override
    public void galleryIntent(ImageView imageView) {

    }

    @Override
    public void cameraIntent(ImageView imageView) {

    }


    @Override
    public RealmList<Image> validatesPhotoList() {
        return null;
    }
}
