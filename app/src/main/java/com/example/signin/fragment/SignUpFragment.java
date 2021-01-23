package com.example.signin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.signin.R;
import com.example.signin.database.AccountDB;
import com.example.signin.model.User;
import com.example.signin.service.AccountService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.widget.Toast.LENGTH_SHORT;
import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class SignUpFragment extends Fragment {
    private Button sign_button;
    private EditText userName_up;
    private EditText password_up;
    private EditText checkPass;
    private EditText email;
    private AccountService service;
    final float v=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_fragment, container, false);
        sign_button = root.findViewById(R.id.signUp);
        userName_up = root.findViewById(R.id.userName_up);
        password_up = root.findViewById(R.id.password_up);
        checkPass = root.findViewById(R.id.confirmPassword);
        email = root.findViewById(R.id.email);

        service = new AccountService(new AccountDB(getActivity()));
        sign_button.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button));

        //animation
        userName_up.setTranslationX(800);
        password_up.setTranslationX(800);
        checkPass.setTranslationX(800);
        email.setTranslationX(800);
        sign_button.setTranslationX(800);

        userName_up.setAlpha(v);
        password_up.setAlpha(v);
        checkPass.setAlpha(v);
        email.setAlpha(v);
        sign_button.setAlpha(v);

        userName_up.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        password_up.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        checkPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1000).start();
        sign_button.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1200).start();


        return root;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(getActivity(), R.id.userName_up, "[a-zA-Z\\s]+", R.string.invalid_username);
        mAwesomeValidation.addValidation(getActivity(), R.id.email, android.util.Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        mAwesomeValidation.addValidation(getActivity(), R.id.password_up, ".{6,}", R.string.invalid_password);
        mAwesomeValidation.addValidation(getActivity(), R.id.confirmPassword, R.id.password_up, R.string.password_not_match);

        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAwesomeValidation.validate()) {
                    String name_db = userName_up.getText().toString();
                    String pass_db = password_up.getText().toString();
                    String email_db = email.getText().toString();
                    if(service.addNewUser(new User(name_db,email_db,pass_db))){
                        Toast.makeText(getActivity(),"Success! Now you can sign in!",LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"The Username or Email has been used. Please sign in.",LENGTH_SHORT).show();
                    }

                } else {
                    //Toast.makeText(getActivity(), "Failed", LENGTH_SHORT).show();
                }
            }
        });
    }
}
