package com.example.domain.service;

public interface TransactionTestService {
    /**
     * <p>トランザクションタイムアウトの検証を行う</p>
     *
     * @param beforeSleep トランザクション開始直後にsleepする秒数
     * @param loopNums DBアクセス処理を行うループ数
     * @param loopInSleep ループ内でsleepする秒数
     * @param afterSleep トランザクション終了直前にsleepする秒数
     */
    void check(long beforeSleep, int loopNums, long loopInSleep, long afterSleep);
}
