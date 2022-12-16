package adbs.main.auto.ui.jpanels.adb.listener;

/**
 * 静音：
 * adb shell input keyevent 164
 */
public class VolumeNoneBtnAcListener extends AdShellInputKeyEventAcListener {
    @Override
    public void setKey() {
        key = "164";
    }
}
