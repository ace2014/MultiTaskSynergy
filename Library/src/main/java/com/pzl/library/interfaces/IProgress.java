package com.pzl.library.interfaces;

/**
 * @author zl.peng
 * @version [1.0, 2016-10-26]
 */
public interface IProgress {
    /**
     * 获得剩余任务数目
     *
     * @param remaining
     */
    void getRemainingAmount(int remaining);
}