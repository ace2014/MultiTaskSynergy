package com.pzl.library;

import android.os.AsyncTask;

import com.pzl.library.interfaces.IProgress;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-25]
 *          多任务并行都完成后的后续单一任务
 */
public class MTaskSResultTask extends AsyncTask<MultiTask, Integer, Object> implements IProgress {
    private ExecutorService executorService;//前置任务运行于另一个线程池中，最终任务在AsyncTask内置默认线程池或外来参数线程池中
    private CountDownLatch latch;
    private FinalTask finalTask;
    private int totalTaskAmount;

    public MTaskSResultTask(CountDownLatch latch, FinalTask finalTask) {
        this.latch = latch;
        this.finalTask = finalTask;
        executorService = Executors.newFixedThreadPool(1);
    }


    @Override
    protected Object doInBackground(MultiTask... params) {
        Object finalResult = null;
        totalTaskAmount = params.length;
        for (MultiTask task : params) {
            task.regIProgress(this);
            executorService.execute(task);
        }

        try {
            latch.await();
            finalResult = finalTask.run(params);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return finalResult;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    public void getRemainingAmount(int remaining) {
        publishProgress(totalTaskAmount, totalTaskAmount - remaining);
    }
}
