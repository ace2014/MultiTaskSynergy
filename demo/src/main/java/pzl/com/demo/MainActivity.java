package pzl.com.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

import pzl.com.demo.library.MainTask;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";

    private String[] urls = new String[]{
            "http://d.hiphotos.baidu.com/zhidao/pic/item/7acb0a46f21fbe09e8634a6c6d600c338744ad8d.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1282854645,2477251725&fm=21&gp=0.jpg",
            "http://cdn.duitang.com/uploads/item/201511/21/20151121125010_ruaV5.thumb.700_0.jpeg",
            "http://desk.fd.zol-img.com.cn/t_s1366x768c5/g5/M00/0F/06/ChMkJlfI162IeK07AAY_egKaTWoAAU6qQHhDUcABj-S342.jpg?downfile=1477390927648.jpg",
            "http://desk.fd.zol-img.com.cn/t_s1366x768c5/g5/M00/09/01/ChMkJlc6le6IQmniAAbn3PEhvygAARiPQKH3x8ABuf0614.jpg?downfile=1477390973405.jpg",
            //"http://desk.fd.zol-img.com.cn/t_s1366x768c5/g5/M00/09/01/ChMkJlc6leSIGSWGAAmpo-gihXAAARiPAIiUhAACam7299.jpg?downfile=1477390990293.jpg",
            //"http://desk.fd.zol-img.com.cn/t_s1280x800c5/g5/M00/0C/06/ChMkJlb6ReOIb-mOAARDBTql86AAAOwYAP5or4ABEMd121.jpg?downfile=1477391028768.jpg",
            //"http://desk.fd.zol-img.com.cn/t_s1138x640c5/g5/M00/0C/06/ChMkJ1b6ReSIc5FVAAE8OpEBCp4AAOwYQCwazEAATxS541.jpg?downfile=1477391045712.jpg",
            //"http://desk.fd.zol-img.com.cn/t_s1366x768c5/g5/M00/05/08/ChMkJlcEanOIHEkGAFQUYQuAx5sAAPUogHvNRQAVBR5840.jpg?downfile=1477391260603.jpg",
    };

    private ImageView iv;
    private MainTask mainTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv = (ImageView) findViewById(R.id.iv);

        ImagePreTask[] imagePreTasks = new ImagePreTask[urls.length];
        for (int i = 0; i <= urls.length - 1; i++) {
            imagePreTasks[i] = new ImagePreTask(urls[i]);
        }
        mainTask = new MyMainTask(imagePreTasks);
        mainTask.start();
    }

    public void click(View view) {
        try {
            Log.d(TAG, "setImageBitmap");
            iv.setImageBitmap((Bitmap) mainTask.getResult());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
