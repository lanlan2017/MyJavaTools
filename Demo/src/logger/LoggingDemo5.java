package logger;

import robot.MyFormatter;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * 自定义控制台输出
 */
public class LoggingDemo5 {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("logger.my");
        // 不向上递归发送输出
        logger.setUseParentHandlers(false);
        // 创建Handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // 给Handler的设置 输出格式
        consoleHandler.setFormatter(new MyFormatter());
        // 添加一个Handler
        logger.addHandler(consoleHandler);

        logger.severe("严重信息");
        logger.warning("警示信息");
        logger.info("一般信息");
        logger.config("设定方面的信息");
        logger.fine("细微的信息");
        logger.finer("更细微的信息");
        logger.finest("最细微的信息");
    }
}
