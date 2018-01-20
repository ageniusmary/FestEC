package com.example.mary.mary.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.net
 * 创建者：   Mary
 * 创建时间:  2018/1/18 13:49
 * 描述：     TODO
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    @FormUrlEncoded
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @GET
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);

    @GET
    //retrofit默认下载方式是把文件一次性下载到内存里然后写入  但是文件过大就内存泄漏
    //所以Streaming是边下载边写入
    @Streaming
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);

    @POST
    @Multipart
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
