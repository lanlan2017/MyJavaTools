package com.melloware.jintellitype;

import blue.ocr.baidu.BaiduOcrRunable;
import blue.ocr.ui.ScreenShotWindow;
import blue.ocr.ui.ToolsWindow;

/**
 * 全局快捷键注册器.
 */
public class HotkeyRegister {
    // 截屏 快捷键标识
    public static final int SCREENSHOT_HOT_KEY = 1;
    // 取消截屏 快捷键表示
    public static final int CANCEL_SCREENSHOT_HOT_KEY = 2;
    // 文字识别 快捷标识
    public static final int BAIDUOCR_HOT_KEY = 3;
    // 退出 快捷标识
    public static final int EXIT_KEY_MARK = 4;

    // 单例模式开始
    private static final HotkeyRegister instance = new HotkeyRegister();

    public static HotkeyRegister getInstance() {
        return instance;
    }

    private HotkeyRegister() {
        dingYiKuaiJieJian();
        kuaiJieJianHanShu();
    }

    // 单例模式结束
    private static void dingYiKuaiJieJian() {
        // 第2步：注册热键，第一个参数表示该热键的标识，第二个参数表示组合键，如果没有则为0，第三个参数为定义的主要热键
        // 截屏 快捷键 ctrl+alt+w
        JIntellitype.getInstance().registerHotKey(SCREENSHOT_HOT_KEY, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 'W');
        // 退出 快捷键 alt+Q
        JIntellitype.getInstance().registerHotKey(EXIT_KEY_MARK, JIntellitype.MOD_ALT, 'Q');
        // 取消截屏 快捷键: ctrl+alt+E
        JIntellitype.getInstance().registerHotKey(CANCEL_SCREENSHOT_HOT_KEY, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT, 'E');
        // 文字识别 快捷键: alt+B
        JIntellitype.getInstance().registerHotKey(BAIDUOCR_HOT_KEY, JIntellitype.MOD_ALT, 'B');
        // 书签识别 快捷: alt+s
        JIntellitype.getInstance().registerHotKey(BAIDUOCR_HOT_KEY, JIntellitype.MOD_ALT, 'S');
    }

    /**
     * 快捷键注册函数
     */
    private static void kuaiJieJianHanShu() {
        // 第3步：添加热键监听器JIntellitype
        JIntellitype.getInstance().addHotKeyListener(markCode -> {
            switch (markCode) {
                // 开始截屏
                case SCREENSHOT_HOT_KEY:
                    ScreenShotWindow.getInstance().screenshotAgain();
                    break;
                // 退出程序
                case EXIT_KEY_MARK:
                    System.exit(0);
                    break;
                // 退出截屏
                case CANCEL_SCREENSHOT_HOT_KEY:
                    // 让窗口不显示
                    ScreenShotWindow.getInstance().setVisible(false);
                    // 将工具条移动到左上角
                    ToolsWindow.defaultLocation();
                    break;
                // 百度文字识别
                case BAIDUOCR_HOT_KEY:
                    BaiduOcrRunable.startBaiduOCR();
                    break;
            }
        });
    }
}
