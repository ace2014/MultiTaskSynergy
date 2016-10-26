package com.pzl.library;

import android.os.AsyncTask;
import android.support.annotation.CallSuper;

import com.pzl.library.interfaces.IProgress;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-25]
 *          多并行任务
 */
public class MultiTask implements RunnableFuture<Object> {
    private IProgress progress;
    private CountDownLatch latch;
    /**
     * 任务结果
     */
    protected Object result;

    public MultiTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @CallSuper
    @Override
    public void run() {
        latch.countDown();
        progress.getRemainingAmount((int) latch.getCount());
        destroy();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {//todo
        return false;
    }

    @Override
    public boolean isCancelled() {//todo
        return false;
    }

    @Override
    public boolean isDone() {//todo
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return result;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException { //todo
        return null;
    }

    public void regIProgress(IProgress progress) {
        this.progress = progress;
    }

    private void destroy() {
        progress = null;
        latch = null;
    }
}
