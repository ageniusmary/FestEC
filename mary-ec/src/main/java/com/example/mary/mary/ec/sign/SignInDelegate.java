package com.example.mary.mary.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.example.mary.mary.delegates.MaryDelegate;
import com.example.mary.mary.ec.R;
import com.example.mary.mary.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.ec.sign
 * 创建者：   Mary
 * 创建时间:  2018/2/8 20:32
 * 描述：     TODO
 */

public class SignInDelegate extends MaryDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if(checkForm()){

        }
    }
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){

    }
    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请输入至少6位密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
