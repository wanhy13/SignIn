package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.signin.database.AccountDB;
import com.example.signin.fragment.ResetFragment;
import com.example.signin.fragment.VerifyFragment;
import com.example.signin.service.AccountService;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class VerificationActivity extends AppCompatActivity {

    AccountDB db;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_verification);

        db = new AccountDB(this);
        AccountService service = new AccountService(db);

        VerifyFragment vf = new VerifyFragment();
        ResetFragment rf = new ResetFragment();

        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.canvas,vf).commit();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}