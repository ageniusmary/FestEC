package com.example.mary.mary.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.mary.mary.app.Mary;
import com.example.mary.mary.net.callback.IRequest;
import com.example.mary.mary.net.callback.ISuccess;
import com.example.mary.mary.util.fileUtil.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.net.download
 * 创建者：   Mary
 * 创建时间:  2018/1/26 20:54
 * 描述：     TODO
 */

public class SaveFileTask extends AsyncTask<Object,Void ,File>{

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess ISUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = ISUCCESS;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String extension = (String) objects[1];
        final ResponseBody body = (ResponseBody) objects[2];
        final String name = (String) objects[3];

        final InputStream is = body.byteStream();
        if(downloadDir == null || downloadDir.equals("")){
            downloadDir = "down_loads";
        }
        if(extension == null || extension.equals("")){
            extension = "";
        }
        if(name == null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else{
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS != null){
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }

    /*
    //当更新下载apk文件的时候
    private void autoInstallApk(File file){
        if(FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file),"");
            Mary.getApplicationContext().startActivities(install);
        }
    }
    */
}
