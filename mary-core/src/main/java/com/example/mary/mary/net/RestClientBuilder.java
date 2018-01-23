package com.example.mary.mary.net;

import com.example.mary.mary.net.callback.IError;
import com.example.mary.mary.net.callback.IFailure;
import com.example.mary.mary.net.callback.IRequest;
import com.example.mary.mary.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.net
 * 创建者：   Mary
 * 创建时间:  2018/1/20 12:23
 * 描述：     把建造者和宿主类分隔开  就不用静态内部类的方法
 */

public class RestClientBuilder {

    private  String mUrl;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IFailure mIFailure;
    private  IError mIError;
    private  RequestBody mBody;

    RestClientBuilder(){
    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }
    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }
    public final RestClientBuilder request(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError = iError;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody);
    }
}
