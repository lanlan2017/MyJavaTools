package process.tools.test;

import process.tools.CmdCls;

/**
 * 测试在cmd中实现清屏功能.不要在IDE中设置.在cmd控制台中运行这个测试类可以看到清屏效果.
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
        // 调用清屏功能.
        CmdCls.cls();
        System.out.println("清屏结束");
    }
}
