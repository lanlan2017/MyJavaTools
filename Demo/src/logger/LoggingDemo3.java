package logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggingDemo3 {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("LoggingDemo");
        try {
            FileHandler fileHandler = new FileHandler("3.txt");
            logger.addHandler(fileHandler);
            logger.info("测试信息");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}