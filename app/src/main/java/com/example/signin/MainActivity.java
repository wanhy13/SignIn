package com.example.signin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.signin.util.SessionManager;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    private Button leave;
    private TextView user_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        sessionManager = new SessionManager(getApplicationContext());
        String username = sessionManager.getUsername();

        user_name = findViewById(R.id.text_username);
        user_name.setText(username);


        leave = findViewById(R.id.leave);

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.setLogin(false);
                sessionManager.setUsername("");
                Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
