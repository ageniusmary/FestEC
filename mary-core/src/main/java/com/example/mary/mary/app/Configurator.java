package com.example.mary.mary.app;


import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.app
 * 创建者：   Mary
 * 创建时间:  2018/1/16 13:40
 * 描述：     配置文件的存储和获取
 */

public class Configurator {

    private static final HashMap<Object,Object> MARY_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final  ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator(){
        MARY_CONFIGS.put(configKeys.CONFIG_READY.name(),false);
    }

    //单例  线程安全的懒汉模式
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<Object,Object> getMaryConfigs(){
        return MARY_CONFIGS;
    }

    public final void configure(){
        initIcons();
        MARY_CONFIGS.put(configKeys.CONFIG_READY.name(),true);
    }
    public final Configurator withApihost(String host){
        MARY_CONFIGS.put(configKeys.API_HOST.name(),host);
        return this;
    }
    public final Configurator withLoaderDelayed(long delayed){
        MARY_CONFIGS.put(configKeys.LOADER_DELAYED,delayed);
        return this;
    }

    private void initIcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        MARY_CONFIGS.put(configKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        MARY_CONFIGS.put(configKeys.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) MARY_CONFIGS.get(configKeys.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException ("configuration is not ready,call configure");
        }
    }

    final <T> T getConfiguration(Object key){
        checkConfiguration();
        return (T) MARY_CONFIGS.get(key);
    }
}
