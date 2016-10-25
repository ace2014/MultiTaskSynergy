package pzl.com.multitasksynergy.task;

import com.pzl.library.FinalTask;
import com.pzl.library.MTaskSResultTask;

import java.util.concurrent.CountDownLatch;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-25]
 */
public class StitchImagesTask extends MTaskSResultTask {

    public StitchImagesTask(CountDownLatch latch, FinalTask finalTask) {
        super(latch, finalTask);
    }
}
