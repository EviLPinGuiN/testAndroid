package ru.kpfu.itis.archgis.screen.auth;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.HttpException;

import ru.kpfu.itis.archgis.api.ApiFactory;
import ru.kpfu.itis.archgis.screen.MainActivity;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.Response;
import ru.kpfu.itis.archgis.screen.data.CreateDataActivity;
import ru.kpfu.itis.archgis.utils.Constants;
import ru.kpfu.itis.archgis.utils.PreferencesManager;



import static ru.kpfu.itis.archgis.utils.Validation.validateEmail;
import static ru.kpfu.itis.archgis.utils.Validation.validateFields;
/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    @BindView(R.id.et_email) EditText mEtEmail;
    @BindView(R.id.et_password) EditText mEtPassword;
    @BindView(R.id.btn_login) Button mBtLogin;
    @BindView(R.id.tv_register) TextView mTvRegister;
    @BindView(R.id.tv_forgot_password) TextView mTvForgotPassword;
    @BindView(R.id.ti_email) TextInputLayout mTiEmail;
    @BindView(R.id.ti_password) TextInputLayout mTiPassword;
    @BindView(R.id.progress) ProgressBar mProgressBar;



    private CompositeDisposable mSubscriptions;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.auth_fragment_login, container, false);
        ButterKnife.bind(this, view);
        mSubscriptions = new CompositeDisposable();
        /** **/
        mEtEmail.setText("admin");
        mEtPassword.setText("qwerty");
        mBtLogin.setOnClickListener(v -> login());
        mTvRegister.setOnClickListener(v -> goToRegister());

        return view;
    }



    private void login() {

        setError();
        String email = mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();
        int err = 0;
        if (!validateFields(email)) {
            err++;
            mTiEmail.setError("Email should be valid !");
        }
        if (!validateFields(password)) {
            err++;
            mTiPassword.setError("Password should not be empty !");
        }

        if (err == 0) {
            loginProcess(email,password);
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            mProgressBar.setVisibility(View.VISIBLE);

        } else {
            showSnackBarMessage("Enter Valid Details !");
        }
    }

    private void setError() {
        mTiEmail.setError(null);
        mTiPassword.setError(null);
    }

    private void loginProcess(String email, String password) {
//        String mail = "admin@mail.ru";
//        String pass = "123";

//        if (email.equals(mail) && password.equals(pass)){
//            Intent intent = new Intent(getActivity(), MainActivity.class);
////            intent.putExtra("mail", mail);
//            PreferencesManager.saveUserName(mail, getActivity().getApplicationContext());
//            startActivity(intent);
//        }
        PreferencesManager.saveUserName(email, getActivity().getApplicationContext());
        RequestBody requestEmail = RequestBody.create(
                        MediaType.parse("multipart/form-data"), email);
        RequestBody requestPassword = RequestBody.create(
                        MediaType.parse("multipart/form-data"), password);
        mSubscriptions.add(ApiFactory.getUserService().login(requestEmail, requestPassword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(Response response) {

        mProgressBar.setVisibility(View.VISIBLE);
        PreferencesManager.saveToken(response.getToken(), getActivity().getApplicationContext());

//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putString(Constants.TOKEN,response.getToken());
//        editor.putString(Constants.EMAIL,response.getMessage());
//        editor.apply();
        mEtEmail.setText(null);
        mEtPassword.setText(null);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


    }

    private void handleError(Throwable error) {

        mProgressBar.setVisibility(View.GONE);
        if (error instanceof HttpException) {
            Gson gson = new GsonBuilder().create();
            try {
                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody,Response.class);
                showSnackBarMessage(response.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            showSnackBarMessage("Network Error !");
        }
    }

    private void showSnackBarMessage(String message) {
        if (getView() != null) {
            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
    }

    private void goToRegister(){

        FragmentManager manager = getFragmentManager();
        RegisterFragment fragment = new RegisterFragment();
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped){
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragmentFrame,fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

}
