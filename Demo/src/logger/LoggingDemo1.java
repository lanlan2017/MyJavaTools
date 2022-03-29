package logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDemo1 {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(LoggingDemo1.class.getName());
        //显示所有等级的信息
        logger.setLevel(Level.ALL);

        // 创建Handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        //显示所有等级的信息
        consoleHandler.setLevel(Level.ALL);
        //设定Logger的Handler为自定义
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