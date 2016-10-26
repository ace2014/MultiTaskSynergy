package com.pzl.library;

import android.support.annotation.CallSuper;
import android.support.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-25]
 */
public class FinalTask {
    protected ArrayList preTaskResults = new ArrayList();
    protected Object result;

    @CallSuper//第一行
    @WorkerThread//后台线程
    protected Object run(MultiTask[] preTasks) {
        getPreResults(preTasks);

        return result;
    }

    private void getPreResults(MultiTask[] preTasks) {
        for (MultiTask preTask : preTasks) {
            try {
                preTaskResults.add(preTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

}
