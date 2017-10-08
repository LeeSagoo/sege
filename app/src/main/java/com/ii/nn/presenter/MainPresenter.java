package com.ii.nn.presenter;

import android.util.Log;

import com.ii.nn.contract.MainContract;
import com.ii.nn.repository.InternalData;

import org.json.JSONObject;

/**
 * Created by BISHOP on 2017-10-01.
 */

public class MainPresenter implements MainContract.Presenter {
    public static final String TAG = MainPresenter.class.getSimpleName();
    private MainContract.View view;

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void showToast() {
        view.showToast();
    }

    @Override
    public void setJsonFile(JSONObject jsons) {
        Log.e(TAG, ": "+jsons);
    }

    @Override
    public String[] getFilesList() {
        return new InternalData().getInternalFileName();
    }

    @Override
    public String addData(String str) {
        return str+" 테스트";
    }
}
