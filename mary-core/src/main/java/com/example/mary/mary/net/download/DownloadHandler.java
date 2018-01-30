package com.example.mary.mary.net.download;

import android.os.AsyncTask;

import com.example.mary.mary.net.RestCreator;
import com.example.mary.mary.net.callback.IError;
import com.example.mary.mary.net.callback.IFailure;
import com.example.mary.mary.net.callback.IRequest;
import com.example.mary.mary.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.net.download
 * 创建者：   Mary
 * 创建时间:  2018/1/26 20:41
 * 描述：     TODO
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url,
                           IRequest request,
                           String download_dir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handleDownload() {
        //不等于null说明开始下载
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {

                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                       if(response.isSuccessful()){
                           final ResponseBody responseBody = response.body();
                           final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                           //task.execute()是以队列的形式一个一个执行 task.executeOnExecutor()是线程池
                           task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);

                           //这里一定要注意判断，否则文件下载不全
                           if (task.isCancelled()) {
                               if (REQUEST != null) {
                                   REQUEST.onRequestEnd();
                               }
                           }
                       }else {
                           if(ERROR != null){
                               ERROR.onError(response.code(),response.message());
                           }
                       }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(FAILURE != null){
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
