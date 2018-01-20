package com.example.mary.mary.net;

import com.example.mary.mary.app.ConfigType;
import com.example.mary.mary.app.Mary;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.net
 * 创建者：   Mary
 * 创建时间:  2018/1/18 18:23
 * 描述：     创建RestClient
 */

public class RestCreator {

    private static final class ParamsHodler{
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }
    public static WeakHashMap<String,Object> getParams(){
        return ParamsHodler.PARAMS;
    }

    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }
    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Mary.getConfigurations().get(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    //okhttp惰性初始化
    private static final class OKHttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
