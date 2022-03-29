package logger;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自定义日志级别
 */
public class MyLevel extends Level {
    protected MyLevel(String name, int value) {
        super(name, value);
    }

    public static void main(String[] args) {
        Logger logger = Logger.getAnonymousLogger();
        //由于默认级别是INFO,如果你设置的级别低于INFO（800），则显示不出来，
        logger.log(new MyLevel("MyLevel", 950), "自定义 lever!");
    }
}