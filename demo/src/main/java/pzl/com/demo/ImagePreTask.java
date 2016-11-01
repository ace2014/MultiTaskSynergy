package pzl.com.demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

import pzl.com.demo.library.MainTask;

/**
 * @author zl.peng
 * @version [1.0, 2016-11-01]
 */
public class ImagePreTask extends MainTask.PreTask<Bitmap> {
    private String url;

    public ImagePreTask(String url) {
        this.url = url;
    }

    @Override
    public Bitmap runTask() {
        Bitmap result = null;
        try {
            result = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
