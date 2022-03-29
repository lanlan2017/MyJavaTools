package logger;

import java.util.logging.Logger;

public class LoggingDemo {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("LoggingDemo");
        logger.severe("严重信息");
        logger.warning("警示信息");
        logger.info("一般信息");
        logger.config("设定方面的信息");
        logger.fine("细微的信息");
        logger.finer("更细微的信息");
        logger.finest("最细微的信息");
    }
}