package com.ii.nn.contract;

import org.json.JSONObject;

/**
 * Created by BISHOP on 2017-10-01.
 */

public interface MainContract {
    interface View{
        void showToast();
        void setJsonFile();
        void getFileList(String[] files);
        void start();
    }

    interface Presenter{
        void setView(MainContract.View view);
        void showToast();
        void setJsonFile(JSONObject jsons);
        String[] getFilesList();
        String addData(String str);
    }
}
