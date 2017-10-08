package com.ii.nn.repository;

import android.util.Log;

import com.ii.nn.utils.Path;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by BISHOP on 2017-10-07.
 */

public class InternalData {
    public static final String TAG = InternalData.class.getSimpleName();

    public String[] getInternalFileName(){
        String[] title = null;
        try{
            FilenameFilter filter = (dir, name) -> name.endsWith(".txt");
            File file = new File(Path.getSDCardPath()[0]);
            //File[] files = Environment.getExternalStorageDirectory().listFiles(filter);
            File[] files = file.listFiles(filter);
            title = new String[files.length];
            for(int i=0;i<files.length;i++){
                title[i] = files[i].getName();
            }
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
        return title;
    }
}
