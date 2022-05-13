package blue.commands.ui.event.button;

import ui.key.YamlTools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 监听退出按钮事件
 */
public class ExitButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 缓存配置文件中没有定义的命令
        YamlTools.saveCacheCommands();
        System.exit(0);
    }
}
