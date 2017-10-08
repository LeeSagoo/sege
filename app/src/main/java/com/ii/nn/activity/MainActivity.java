package com.ii.nn.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ii.nn.R;
import com.ii.nn.contract.MainContract;
import com.ii.nn.presenter.MainPresenter;
import com.ii.nn.utils.Path;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Contract.View 안에 있는 메서드들은 여기서 실행 시키는 것이 아니라  Presenter 에서 실행시키는것
 * 여기는 정의부분임
 */

public class MainActivity extends AppCompatActivity implements MainContract.View {
    @BindView(R.id.main_btn_findFiles)
    Button mainBtnFindFiles;
    @BindView(R.id.main_tv_showFiles)
    TextView mainTvShowFiles;
    private MainContract.Presenter presenter;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.e(TAG, Environment.getDataDirectory()+"");
        Log.e(TAG, Environment.getExternalStorageDirectory()+"");
        Log.e(TAG, Environment.getRootDirectory()+"");
        Log.e(TAG, Environment.getExternalStorageDirectory().getAbsolutePath()+"");
        Log.e(TAG, "출 : getExternalMounts"+ Path.getExternalMounts()[0]+"/");
        File file = new File(/*Path.getExternalMounts()[0]+"/"*/"/storage/external_SD/");

        String[] externalArray;
        String second = System.getenv("SECONDARY_STORAGE");
        if(second!=null){
            externalArray = second.split(":");
        }else{
            externalArray = new String[0];
        }
        for(int i=0;i<externalArray.length;i++){
            Log.e(TAG, ":: "+externalArray[i]);
        }
/*        for(int i=0;i<file.list().length;i++){
            Log.e(TAG, "내부"+file.list()[i]);
        }*/
        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.showToast();
    }

    @Override
    public void showToast() {
        Toast.makeText(getApplicationContext(), presenter.addData("야호 "), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setJsonFile() {
    }

    @Override
    public void getFileList(String[] files) {
        Log.e(TAG, "파일 목록 리스트 : " + files);
    }

    @Override
    public void start() {

    }


    @OnClick(R.id.main_btn_findFiles)
    public void onViewClicked() {
        if(presenter.getFilesList()!=null) {
            StringBuffer buffer = new StringBuffer();
            for(String name:presenter.getFilesList()){
                buffer.append(name).append("\n\n");
            }
            mainTvShowFiles.setText("출력 : "+presenter.getFilesList().length+"// "+buffer);
        }
    }
}
