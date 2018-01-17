package com.example.mary.mary.app;

import android.content.Context;

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
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getMaryConfigs();
    }

    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
