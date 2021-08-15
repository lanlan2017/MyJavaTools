package ui.setting;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.Font;
import java.util.Enumeration;

public class FontTools {
    public static Font f1 = new Font(Font.SERIF, Font.PLAIN, 12);
    public static Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 10);
    public static Font f3 = new Font(Font.DIALOG, Font.BOLD, 15);
    public static Font f4 = new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 15);

//     /**
//      * 设置全局字体
//      * @param font 字体
//      */
//     public static void initGobalFont(Font font) {
//         FontUIResource fontResource = new FontUIResource(font);
//         for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
//             Object key = keys.nextElement();
//             Object value = UIManager.get(key);
//             if (value instanceof FontUIResource) {
//                 System.out.println(key);
//                 UIManager.put(key, fontResource);
//             }
//         }
//     }
}
