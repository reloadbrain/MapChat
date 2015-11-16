package com.example.dyyao.mapchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by luluzhao on 11/15/15.
 */
public class Login extends AppCompatActivity implements View.OnClickListener {
    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    public static final String SERVER_IP_ADDRESS = "192.168.1.111";
    public static final int SERVER_PORT_WR = 4444;
    public static final int SERVER_PORT_R = 5555;
    public Queue<String> mCommandBuffer;
    private static final String TAG = "Debug";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvRegisterLink = (TextView) findViewById(R.id.tv_RegisterLink);
        bLogin = (Button) findViewById(R.id.b_Login);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        mCommandBuffer = new LinkedList<>();

        ClientTaskWR mClientTaskWR = new ClientTaskWR(mCommandBuffer);
        //ClientTaskR mClientTaskR = new ClientTaskR();
        mClientTaskWR.execute();
        Log.d(TAG, "R connected");
        //mClientTaskR.execute();
        //Log.d(TAG, "WR connected");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_Login:
                mCommandBuffer.add("login\n" + etUsername.getText().toString() + "\n" + etPassword.getText().toString());
                //startActivity(new Intent(this, friendList.class));

                break;

            case R.id.tv_RegisterLink:

                startActivity(new Intent(this, Register.class));
                break;

        }
    }
}