package ru.kpfu.itis.archgis.screen.data;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;

import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.screen.data.publication.PublicationActivity;
import ru.kpfu.itis.archgis.screen.data.report.ReportActivity;
import ru.kpfu.itis.archgis.utils.widget.AnimationUtil;
import ru.kpfu.itis.archgis.utils.Utility;


/**
 * Created by DNS1 on 11.05.2017.
 */

public abstract class BaseFragment extends Fragment {

    public static final int SELECT_FILE = 1001;
    public static final int REQUEST_CAMERA = 1002;


    protected ViewPager viewPager;
    private AnimationUtil animationUtil;
    private ImageView temp;
    protected SelectImage selectImage;

    protected String userChoosenTask;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getActivity().getLocalClassName().contains(ReportActivity.class.getSimpleName())) {
            viewPager = (ViewPager) getActivity().findViewById(R.id.vp_report);
        } else if (getActivity().getLocalClassName().contains(PublicationActivity.class.getSimpleName())) {
            viewPager = (ViewPager) getActivity().findViewById(R.id.vp_pub);
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }



    protected void addLayout(LinearLayout container, int layout, int title, int more,
                             int slideLayout, int text) {
        int count = 1 + container.getChildCount();
        LayoutInflater ltInflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        final View addView = ltInflater.inflate(layout, null);
        TextView textOut = (TextView) addView.findViewById(title);
        textOut.setText(getResources().getString(text) + " №" + count);
        buttonsListener(addView, container, title, more, slideLayout, text);

        container.addView(addView);
    }



    protected void addLayout(LinearLayout container, int layout, int title,
                             int more, int slideLayout,
                             int text,
                             int pick, int image) {
        int count = 1 + container.getChildCount();
        LayoutInflater ltInflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        final View addView = ltInflater.inflate(layout, null);
        //**title**//
        TextView textOut = (TextView) addView.findViewById(title);
        textOut.setText(getResources().getString(text) + " №" + count);

        Button pickButton = (Button) addView.findViewById(pick);
        ImageView imageView = (ImageView) addView.findViewById(image);
        pickButton.setOnClickListener(v ->
                selectImage(imageView)
        );

        buttonsListener(addView, container, title, more, slideLayout, text);

        container.addView(addView);

    }

    protected void addLayoutWithSpinner(LinearLayout container, int layout, int title,
                                        int more, int slideLayout,
                                        int text,
                                        int pick, int image,
                                        int spinner, int array) {
        int count = 1 + container.getChildCount();
        LayoutInflater ltInflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        final View addView = ltInflater.inflate(layout, null);
        //**title**//
        TextView textOut = (TextView) addView.findViewById(title);
        textOut.setText(getResources().getString(text) + " №" + count);

        Button pickButton = (Button) addView.findViewById(pick);
        ImageView imageView = temp = (ImageView) addView.findViewById(image);
        pickButton.setOnClickListener(v ->
                selectImage(imageView)
        );
        Spinner sp = (Spinner) addView.findViewById(spinner);
        setSpinners(sp, array);

        buttonsListener(addView, container, title, more, slideLayout, text);

        container.addView(addView);

    }

    protected void addLayoutWithSpinner(LinearLayout container, int layout, int title,
                                        int more, int slideLayout,
                                        int text,
                                        int spinner, int array) {
        int count = 1 + container.getChildCount();
        LayoutInflater ltInflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        final View addView = ltInflater.inflate(layout, null);
        //**title**//
        TextView textOut = (TextView) addView.findViewById(title);
        textOut.setText(getResources().getString(text) + " №" + count);
        Spinner sp = (Spinner) addView.findViewById(spinner);
        setSpinners(sp, array);
        buttonsListener(addView, container, title, more, slideLayout, text);

        container.addView(addView);

    }


    protected void next() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }


    protected void setError(TextInputLayout text) {
        text.setError(null);
    }

    protected void showSnackBarMessage(String message) {
        if (getView() != null) {
            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
    }

    protected void setSpinners(Spinner spinner, int array) {
        ArrayAdapter<CharSequence> adapterSubject = ArrayAdapter.createFromResource(
                getActivity(), array, android.R.layout.simple_spinner_item);
        adapterSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSubject);
    }

    private void buttonsListener(View addView, LinearLayout container, int title, int more, int slideLayout, int text) {
        ImageButton moreButton = (ImageButton) addView.findViewById(more);
        RelativeLayout relativeLayout = (RelativeLayout) addView.findViewById(slideLayout);
        final boolean[] isClicked = {true};
        moreButton.setOnClickListener(v -> {
            PopupMenu menu = new PopupMenu(getActivity() , v);
            Object menuHelper;
            Class[] argTypes;
            try {
                Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
                fMenuHelper.setAccessible(true);
                menuHelper = fMenuHelper.get(menu);
                argTypes = new Class[]{boolean.class};
                menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
            }
            catch (Exception e) {
                menu.show();
                return;
            }
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.more_slide:
                        if (isClicked[0]) {
                            animationUtil.collapse(relativeLayout);
                            isClicked[0] = false;
                        } else {
                            animationUtil.expand(relativeLayout);
                            isClicked[0] = true;
                        }
                        return true;
                    case R.id.more_delete:
                        ((LinearLayout) addView.getParent()).removeView(addView);
                        updateChildTitle(container, title, text);
                        return true;
                    default: return false;
                }
            });
            menu.inflate(R.menu.menu_more_item_action);
            if (isClicked[0]) {
                MenuItem item = menu.getMenu().getItem(0);
                item.setIcon(R.drawable.ic_keyboard_arrow_up_black_24dp);
                item.setTitle(R.string.menu_up);
            } else {
                MenuItem item = menu.getMenu().getItem(0);
                item.setIcon(R.drawable.ic_keyboard_arrow_down_black_24dp);
                item.setTitle(R.string.menu_down);
            }
            menu.show();
        });
    }

    private void updateChildTitle(LinearLayout layout, int title, int text) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            int count = i + 1;
            View updateView = layout.getChildAt(i);

            TextView textOut = (TextView) updateView.findViewById(title);
            textOut.setText(getResources().getString(text) + " №" + count);
        }
    }



    private void selectImage(ImageView imageView) {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, (dialog, item) -> {
            boolean result= Utility.checkPermission(getActivity());
            if (items[item].equals("Take Photo")) {
                userChoosenTask="Take Photo";
                if(result)
                    selectImage.cameraIntent(imageView);
            } else if (items[item].equals("Choose from Gallery")) {
                userChoosenTask="Choose from Gallery";
                if(result)
                    selectImage.galleryIntent(imageView);
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Take Photo"))
                        selectImage.cameraIntent(temp);
                    else if(userChoosenTask.equals("Choose from Gallery"))
                        selectImage.galleryIntent(temp);
                } else {
                }
                break;
        }
    }


}
