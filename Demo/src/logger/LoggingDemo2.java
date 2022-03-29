package logger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDemo2 {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("LoggingDemo");
        logger.log(Level.SEVERE, "严重信息test");
    }
}