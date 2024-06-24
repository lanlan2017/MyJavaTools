package screen;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Java获取屏幕像素
 */
public class ScreenResolution {  
    public static void main(String[] args) {  
        // 获取屏幕默认的Toolkit  
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取屏幕的尺寸（像素）  
        Dimension screenSize = toolkit.getScreenSize();
        // 输出屏幕的宽度和高度  
        System.out.println("屏幕宽度（像素）: " + screenSize.width);  
        System.out.println("屏幕高度（像素）: " + screenSize.height);  
    }  
}