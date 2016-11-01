package pzl.com.demo.library;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zl.peng
 * @version [1.0, 2016-11-01]
 */
public abstract class MainTask<T> extends Thread {
    public static final String TAG = "MainTask";

    private MyCallable myCallable;
    private FutureTask myFutureTask;

    public MainTask(PreTask... preTasks) {
        Log.d(TAG, "MainTask");
        myCallable = new MyCallable(preTasks);
        myFutureTask = new FutureTask(myCallable);
    }

    @Override
    public void run() {
        super.run();
        myFutureTask.run();
    }

    //todo 不同参数类型
    protected abstract T onFinalTask(PreTask... preTasks);

    public T getResult() throws ExecutionException, InterruptedException {
        return (T) myFutureTask.get();
    }

    public T getResult(long timeoutMilliseconds) throws InterruptedException, ExecutionException, TimeoutException {
        return (T) myFutureTask.get(timeoutMilliseconds, TimeUnit.MILLISECONDS);
    }

    class MyCallable implements Callable {
        private PreTask[] mPreTasks;

        public MyCallable(PreTask... preTasks) {
            mPreTasks = preTasks;
        }

        @Override
        public Object call() throws Exception {
            for (PreTask temp : mPreTasks) {
                try {
                    temp.start();
                    temp.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return onFinalTask(mPreTasks);
        }
    }

    public static abstract class PreTask<T> {
        private T result;//TODO 封装结果类型
        private MyThread thread;

        public PreTask() {
            thread = new MyThread();
        }

        class MyThread extends Thread {
            @Override
            public void run() {
                super.run();
                Log.d(TAG, "PreTask run");
                result = runTask();
            }
        }

        protected abstract T runTask();

        public void start() {
            thread.start();
        }

        public void join() throws InterruptedException {
            thread.join();
        }

        public T getResult() {
            return result;
        }
    }
}
