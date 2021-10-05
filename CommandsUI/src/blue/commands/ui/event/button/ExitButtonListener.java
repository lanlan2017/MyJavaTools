package blue.commands.ui.event.button;

import ui.key.YamlTools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 缓存配置文件中没有写的命令
        YamlTools.saveCacheCommands();
        System.exit(0);
    }
}
