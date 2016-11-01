package pzl.com.demo;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import pzl.com.demo.library.MainTask;

/**
 * @author zl.peng
 * @version [1.0, 2016-11-01]
 */
public class MyMainTask extends MainTask<Bitmap> {

    public MyMainTask(PreTask... preTasks) {//todo 为什么不强行调用父构造
        super(preTasks);
    }

    @Override
    protected Bitmap onFinalTask(PreTask... preTasks) {

        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        for (int i = 0; i <= preTasks.length - 1; i++) {
            Bitmap temp = (Bitmap) preTasks[i].getResult();
            bitmap = add2Bitmap(bitmap, temp);
        }
        return bitmap;
    }

    /**
     * 将两张位图拼接成一张(纵向拼接)
     *
     * @param first
     * @param second
     * @return
     */
    private Bitmap add2Bitmap(Bitmap first, Bitmap second) {
        int width = Math.max(first.getWidth(), second.getWidth());
        int height = first.getHeight() + second.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, 0, first.getHeight(), null);
        return result;
    }

}
