package ru.kpfu.itis.archgis.screen.auth;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.HttpException;
import ru.kpfu.itis.archgis.R;
import ru.kpfu.itis.archgis.model.Response;
import ru.kpfu.itis.archgis.model.User;
import static ru.kpfu.itis.archgis.utils.Validation.validateEmail;
import static ru.kpfu.itis.archgis.utils.Validation.validateFields;
import static ru.kpfu.itis.archgis.utils.Validation.validatePassword;


public class RegisterFragment extends Fragment {


    @BindView(R.id.et_name)  EditText mEtName;
    @BindView(R.id.et_email) EditText mEtEmail;
    @BindView(R.id.et_password) EditText mEtPassword;
    @BindView(R.id.et_confirmPassword) EditText mEtConfirmPassword;
    @BindView(R.id.buttonRegister) Button mBtRegister;
    @BindView(R.id.ti_name) TextInputLayout mTiName;
    @BindView(R.id.ti_email) TextInputLayout mTiEmail;
    @BindView(R.id.ti_password) TextInputLayout mTiPassword;
    @BindView(R.id.ti_confirmPassword) TextInputLayout mTiConfirmPassword;
    @BindView(R.id.tv_login) TextView mLogin;
    @BindView(R.id.progress) ProgressBar mProgressbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auth_fragment_register, container, false);
        ButterKnife.bind(this, view);
        mBtRegister.setOnClickListener(v -> register());
        mLogin.setOnClickListener(v -> goToLogin());
        return view;
    }

    private void register() {

        setError();

        String name = mEtName.getText().toString();
        String email = mEtEmail.getText().toString();
        String password = mEtPassword.getText().toString();
        String confirm = mEtConfirmPassword.getText().toString();
        int err = 0;
        if (!validateFields(name)) {
            err++;
            mTiName.setError("Name should not be empty !");
        }
        if (!validateEmail(email)) {
            err++;
            mTiEmail.setError("Email should be valid !");
        }
        if (!validateFields(password)) {
            err++;
            mTiPassword.setError("Password should not be empty !");
        }
        if (!validatePassword(password, confirm)){
            err++;
            mTiConfirmPassword.setError("Password does not matches");
        }

        if (err == 0) {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);

            mProgressbar.setVisibility(View.VISIBLE);
            registerProcess(user);
        } else {
            showSnackBarMessage("Enter Valid Details !");
        }
    }

    private void setError() {
        mTiName.setError(null);
        mTiEmail.setError(null);
        mTiPassword.setError(null);
    }

    private void registerProcess(User user) {

//        mSubscriptions.add(NetworkUtil.getRetrofit().register(user)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(Response response) {
        mProgressbar.setVisibility(View.GONE);
        showSnackBarMessage(response.getMessage());
    }

    private void handleError(Throwable error) {

        mProgressbar.setVisibility(View.GONE);
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

    private void goToLogin(){

        FragmentManager manager = getFragmentManager();
        LoginFragment fragment = new LoginFragment();
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped){
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragmentFrame,fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    private void emptyInputEditText() {
        mEtName.setText(null);
        mEtEmail.setText(null);
        mEtPassword.setText(null);
        mEtConfirmPassword.setText(null);
    }

}
