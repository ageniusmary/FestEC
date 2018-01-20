package com.example.mary.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.mary.mary.delegates.MaryDelegate;
import com.example.mary.mary.net.RestClient;
import com.example.mary.mary.net.callback.IError;
import com.example.mary.mary.net.callback.IFailure;
import com.example.mary.mary.net.callback.ISuccess;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.festec
 * 创建者：   Mary
 * 创建时间:  2018/1/18 12:38
 * 描述：     TODO
 */

public class ExampleDelegate extends MaryDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void testRestClient(){
        RestClient.builder()
                .url("")
                .params("","")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build();
    }
}
