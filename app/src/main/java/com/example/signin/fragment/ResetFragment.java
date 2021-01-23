package com.example.signin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.signin.AccountActivity;
import com.example.signin.R;
import com.example.signin.VerificationActivity;
import com.example.signin.database.AccountDB;
import com.example.signin.service.AccountService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class ResetFragment extends Fragment {
    private Button submit;
    private AccountService service;
    private EditText pass1;
    private EditText pass2;
    private String name;
    private TextView cancel;
    final float v=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.reset_fragment,container,false);
        submit = root.findViewById(R.id.submit);
        submit.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button));
        pass1 = root.findViewById(R.id.password_reset);
        pass2 = root.findViewById(R.id.confirmpassword_reset);
        cancel = root.findViewById(R.id.cancel);

        //animation
        pass1.setTranslationX(800);
        pass2.setTranslationX(800);
        submit.setTranslationX(800);
        cancel.setTranslationX(800);

        pass1.setAlpha(v);
        pass2.setAlpha(v);
        submit.setAlpha(v);
        cancel.setAlpha(v);

        pass1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        pass2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        submit.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        cancel.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1000).start();

        service = new AccountService(new AccountDB((getActivity())));
        Bundle budle = this.getArguments();
        name = budle.getString("name");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //validate input
        super.onActivityCreated(savedInstanceState);
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(getActivity(), R.id.password_reset, ".{6,}", R.string.invalid_password);
        mAwesomeValidation.addValidation(getActivity(), R.id.confirmpassword_reset, R.id.password_reset, R.string.password_not_match);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAwesomeValidation.validate()){
                    String password = pass1.getText().toString();
                    service.resetPassword(name,password);
                    Intent intent = new Intent(getActivity(), AccountActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
