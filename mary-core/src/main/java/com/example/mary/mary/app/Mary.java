package com.example.mary.mary.app;

import android.content.Context;

import java.security.Key;
import java.util.HashMap;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.app
 * 创建者：   Mary
 * 创建时间:  2018/1/16 13:37
 * 描述：     TODO
 */

public final class Mary {

    public static Configurator init(Context context){
        Configurator.getInstance()
                .getMaryConfigs()
                .put(configKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(configKeys.APPLICATION_CONTEXT);
    }


}
