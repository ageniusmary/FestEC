package com.example.mary.mary.net;

import android.content.Context;

import com.example.mary.mary.net.callback.IError;
import com.example.mary.mary.net.callback.IFailure;
import com.example.mary.mary.net.callback.IRequest;
import com.example.mary.mary.net.callback.ISuccess;
import com.example.mary.mary.net.callback.RequestCallbacks;
import com.example.mary.mary.ui.LoaderStyle;
import com.example.mary.mary.ui.MaryLoader;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Url;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.net
 * 创建者：   Mary
 * 创建时间:  2018/1/18 13:28
 * 描述：     TODO
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    //建造者以builder的形式创建出来
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            MaryLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            //execute会在主线程上执行（不推荐）  enqueue在后台另起的线程上执行，不会影响到ui线程（推荐）
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

}
