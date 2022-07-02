package com.app.humanresource.Fragments.OtpFragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.humanresource.Fragments.OtpFragment.Presenter.OtpPresenter;
import com.app.humanresource.Fragments.OtpFragment.View.OtpView;
import com.app.humanresource.R;
import com.app.humanresource.Utils.GenericTextWatcher;
import com.app.humanresource.Utils.Utils;

//View.OnClickListener,
public class OtpFragment extends Fragment implements View.OnClickListener,  OtpView {

    private Activity activity;
    private View view;
    private EditText otpET1, otpET2, otpET3, otpET4;
    private Button btn_submit;
    private String code;
    private OtpPresenter otpPresenter;
    private String email;
    private TextView tv_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_otp, container, false);
        activity = getActivity();
        init();
        listeners();
        otpPresenter = new OtpPresenter(activity,this);
        email = requireArguments().getString("email");
        tv_email.setText(email);


        return view;
    }


    private void init() {

        otpET1 = view.findViewById(R.id.otpET1);
        otpET2 = view.findViewById(R.id.otpET2);
        otpET3 = view.findViewById(R.id.otpET3);
        otpET4 = view.findViewById(R.id.otpET4);
        btn_submit = view.findViewById(R.id.btn_submit);
        tv_email = view.findViewById(R.id.tv_email);

        EditText[] edit = {otpET1, otpET2, otpET3, otpET4};

        otpET1.addTextChangedListener(new GenericTextWatcher(otpET1, edit));
        otpET2.addTextChangedListener(new GenericTextWatcher(otpET2, edit));
        otpET3.addTextChangedListener(new GenericTextWatcher(otpET3, edit));
        otpET4.addTextChangedListener(new GenericTextWatcher(otpET4, edit));
    }

    private void listeners() {
        btn_submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view==btn_submit){
            String otp = otpET1.getText().toString()+otpET2.getText().toString()+otpET3.getText().toString()+otpET4.getText().toString();
            otpPresenter.otpMethod(otp);
//            Fragment fragment = new ResetPasswordFragment();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.login_container, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
        }

    }


    @Override
    public void showMessage(@Nullable Activity activity, @Nullable String msg) {
        Utils.Companion.showMessage(activity,msg);

    }

    @Override
    public void showDialog(@Nullable Activity activity) {
        Utils.Companion.showDialogMethod(activity,requireActivity().getFragmentManager());

    }

    @Override
    public void hideDialog() {
        Utils.Companion.hideDialog();

    }
}