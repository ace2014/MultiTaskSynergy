package pzl.com.multitasksynergy.task;

import android.graphics.BitmapFactory;

import com.pzl.library.MultiTask;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-25]
 */
public class ImageLoadTask extends MultiTask {
    private String url;

    public ImageLoadTask(CountDownLatch latch, String url) {
        super(latch);
        this.url = url;
    }

    @Override
    public void run() {
        //业务逻辑
        try {
            result = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
