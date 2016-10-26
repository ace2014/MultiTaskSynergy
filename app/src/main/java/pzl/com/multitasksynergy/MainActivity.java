package pzl.com.multitasksynergy;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import java.util.concurrent.CountDownLatch;

import pzl.com.multitasksynergy.task.ImageLoadTask;
import pzl.com.multitasksynergy.task.MyFinalTask;
import pzl.com.multitasksynergy.task.StitchImagesTask;

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
    private ImageLoadTask[] imageLoadTasks;
    private CountDownLatch latch;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();
        initData();

    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
    }

    private void initData() {
        latch = new CountDownLatch(urls.length);
        imageLoadTasks = new ImageLoadTask[urls.length];
        for (int i = 0; i <= urls.length - 1; i++) {
            imageLoadTasks[i] = new ImageLoadTask(latch, urls[i]);
        }

        StitchImagesTask stitchImagesTask = new StitchImagesTask(latch, new MyFinalTask()) {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                iv.setImageBitmap((Bitmap) o);
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                Log.d(TAG, "progress=" + (int) (Float.parseFloat(String.format("%.2f", (float) values[1] / values[0])) * 100) + " %");
                ToastUtil.show((int) (Float.parseFloat(String.format("%.2f", (float) values[1] / values[0])) * 100) + " %", MainActivity.this);
            }
        };
        stitchImagesTask.execute(imageLoadTasks);
    }
}
