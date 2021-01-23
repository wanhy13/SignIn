package com.example.signin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import androidx.fragment.app.FragmentTransaction;

import static android.widget.Toast.LENGTH_SHORT;
import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class VerifyFragment extends Fragment {
    private Button verify;
    private AccountService service;
    private EditText username;
    private EditText email;
    private TextView back;
    final float v=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.verify_fragment,container,false);
        verify = root.findViewById(R.id.verify);
        verify.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.button));
        service = new AccountService(new AccountDB(getActivity()));
        username = root.findViewById(R.id.userName_verify);
        email = root.findViewById(R.id.email_verify);
        back = root.findViewById(R.id.back_to_login);

        username.setTranslationX(800);
        email.setTranslationX(800);
        verify.setTranslationX(800);
        back.setTranslationX(800);


        username.setAlpha(v);
        email.setAlpha(v);
        verify.setAlpha(v);
        back.setAlpha(v);


        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(400).start();
        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        verify.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(800).start();
        back.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1000).start();

        back.setOnClickListener(new View.OnClickListener() {
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
        super.onActivityCreated(savedInstanceState);
        AwesomeValidation mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(getActivity(), R.id.userName_verify, "[a-zA-Z\\s]+", R.string.invalid_username);
        mAwesomeValidation.addValidation(getActivity(), R.id.email_verify, android.util.Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAwesomeValidation.validate()){
                    String username_string = username.getText().toString();
                    String email_string = email.getText().toString();
                    if(service.verifyIdentity(username_string,email_string)){
                        ResetFragment rf = new ResetFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", username_string);
                        rf.setArguments(bundle);
                        FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                        transaction.replace(R.id.canvas,rf);
                        transaction.commit();
                    }else{
                        Toast.makeText(getActivity(),"The Username and Email dont match. Please retry.",LENGTH_SHORT).show();
                        Log.d("Verify fragment","Email, username not match");
                    }

                }else{
                    Log.d("Verify fragment","Verify faild");
                }

            }
        });
    }
}
