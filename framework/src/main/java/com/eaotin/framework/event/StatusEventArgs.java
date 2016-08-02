package com.eaotin.framework.event;

/**
 * 有状态事件参数
 * Created by Mark on 2015/1/9.
 */
public class StatusEventArgs extends EventArgs {

    private int resultCode;

    public StatusEventArgs(int resultCode) {
        this.resultCode =resultCode;
    }

    public int getErrCode() {
        return resultCode;
    }
}
