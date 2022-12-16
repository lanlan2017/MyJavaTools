package adbs.main.auto.ui.jpanels.adb.listener;

/**
 * 增加音量：
 * adb shell input keyevent 24
 */
public class VolumePlusBtnAcListener extends AdShellInputKeyEventAcListener {
    @Override
    public void setKey() {
        key = "24";
    }
}

