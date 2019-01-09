package com.ashutosh.album.view;

public class BaseTest {

    protected static final int TIME_1MS = 1000;
    protected static final int TIME_5MS = 5000;

    protected void doWait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
