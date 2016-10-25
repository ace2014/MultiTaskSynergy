package com.pzl.library;

import android.os.AsyncTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-25]
 *          多任务并行都完成后的后续单一任务
 */
public class MTaskSResultTask extends AsyncTask<MultiTask, Integer, Object> {
    private ExecutorService executorService;
    private CountDownLatch latch;
    private FinalTask finalTask;

    public MTaskSResultTask(CountDownLatch latch, FinalTask finalTask) {
        this.latch = latch;
        this.finalTask = finalTask;
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    protected Object doInBackground(MultiTask... params) {
        Object finalResult = null;
        for (MultiTask task : params) {
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
}
