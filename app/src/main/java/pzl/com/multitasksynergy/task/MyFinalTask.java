package pzl.com.multitasksynergy.task;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.pzl.library.FinalTask;
import com.pzl.library.MultiTask;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-25]
 */
public class MyFinalTask extends FinalTask {

    @Override
    protected Object run(MultiTask[] preTasks) {
        super.run(preTasks);

        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        for (int i = 0; i <= preTaskResults.size() - 1; i++) {
            Bitmap temp = (Bitmap) preTaskResults.get(i);
            bitmap = add2Bitmap(bitmap, temp);
        }
        result = bitmap;
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
