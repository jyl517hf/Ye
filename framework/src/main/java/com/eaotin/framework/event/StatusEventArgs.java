package com.eaotin.framework.event;

/**
 * 有状态事件参数
 */
public class StatusEventArgs extends EventArgs {
    /**
     * 返回结果码
     */
    private int resultCode;

    /**
     * 构造参数
     *
     * @param resultCode
     */
    public StatusEventArgs(int resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 获取返回结果码的接口
     *
     * @return
     */
    public int getResultCode() {
        return resultCode;
    }
}
