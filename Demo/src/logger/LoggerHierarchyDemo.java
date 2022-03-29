package logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger的层次关系
 */
public class LoggerHierarchyDemo {
    public static void main(String[] args) {
        Logger a = Logger.getLogger("a");
        Logger b = Logger.getLogger("a.b");
        System.out.println("root logger:" + a.getParent());
        System.out.println("a logger:"+a.getName());
        System.out.println("b Parent logger:" + b.getParent().getName());
        System.out.println("b Logger:" + b.getName() + "\n");

        a.setLevel(Level.WARNING);
        // b还没有设置级别，默认使用父Logger的级别，也就是WARNING，
        // 则info方法的输出讲不显示
        b.info("b ' info 1");

        b.setLevel(Level.INFO);
        b.info("b ' info 2");
    }
}