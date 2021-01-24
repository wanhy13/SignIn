package com.example.signin.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.signin.MainActivity;
import com.example.signin.R;
import com.example.signin.VerificationActivity;
import com.example.signin.model.User;
import com.example.signin.service.AccountService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.signin.database.AccountDB;
import com.example.signin.util.SessionManager;

import static android.widget.Toast.LENGTH_SHORT;
import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;


public class SignInFragment extends Fragment {
    private Button sign_button;
    private EditText userName;
    private EditText password;
    private TextView getPass;
    private AccountService service;
    SessionManager sessionManager;
    float v=0;
    Dialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_in_fragment,container,false);
        sign_button = root.findViewById(R.id.signIn);
        userName = root.findViewById(R.id.userName);
        password = root.findViewById(R.id.password);
        getPass = root.findViewById(R.id.getPass);
        sign_button.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.button));
        service = new AccountService(new AccountDB(getActivity()));
        sessionManager = new SessionManager(getActivity().getApplicationContext());

        //animation
        userName.setTranslationX(800);
        password.setTranslationX(800);
        getPass.setTranslationX(800);
        sign_button.setTranslationX(800);

        userName.setAlpha(v);
        password.setAlpha(v);
        getPass.setAlpha(v);
        sign_button.setAlpha(v);

        userName.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        getPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        sign_button.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1000).start();



        return root;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(getActivity(), R.id.userName, "[a-zA-Z\\s]+", R.string.invalid_username);
        mAwesomeValidation.addValidation(getActivity(), R.id.password, ".{6,}", R.string.invalid_password);
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = userName.getText().toString();
                String inputPassword = password.getText().toString();

                if(sessionManager.getLogin()){
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }

                if (!mAwesomeValidation.validate()) {
                    //Toast.makeText(getActivity(), "success", LENGTH_SHORT).show();
                } else {
                    User user = service.getUser(inputUsername);
                    if(user == null) {
                        Toast.makeText(getActivity(), "Invalid Username", LENGTH_SHORT).show();
                    }else {
                        if (!validate(user.getPassword(), inputPassword)) {
                            Toast.makeText(getActivity(), "Wrong password", LENGTH_SHORT).show();
                        } else {
                            //welComeDialog();
                            sessionManager.setLogin(true);
                            sessionManager.setUsername(inputUsername);
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }

                }
            }
        });
        getPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VerificationActivity.class);
                startActivity(intent);
            }
        });
    }



    public boolean validate(String pass1, String pass2){
        return pass1.equals(pass2);
    }
}
