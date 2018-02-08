package com.example.mary.mary.net.callback;


import android.os.Handler;

import com.example.mary.mary.ui.loader.LoaderStyle;
import com.example.mary.mary.ui.loader.MaryLoader;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.net.callback
 * 创建者：   Mary
 * 创建时间:  2018/1/20 14:45
 * 描述：     TODO
 */

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADERSTYLE;
    //handler建议声明称static  避免内存泄漏
    private static final Handler HANDLER = new Handler();


    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error,LoaderStyle style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADERSTYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        stopLoading();

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        stopLoading();
    }

    private void stopLoading(){
        if(LOADERSTYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MaryLoader.stopLoading();
                }
            },1000);
        }
    }
}
