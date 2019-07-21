package com.xxx.jvm;

/**
 * @Description 逃逸分析
 * @Date 2019/7/1 21:42
 * @Version 1.0
 */
public class StackAllocation {

    public StackAllocation obj;

    /**
     * 逃逸
     * @return
     */
    public StackAllocation getInstance() {
        return obj == null ? new StackAllocation() : obj;
    }
    /**
     * 逃逸
     */
    public void setObj() {
        this.obj = new StackAllocation();
    }
    /**
     * 只在方法内部使用，没有逃逸，在栈空间分配
     */
    public void useStackAllocation() {
        StackAllocation stackAllocation = new StackAllocation();
    }
}
