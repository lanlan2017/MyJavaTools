package adbs.main.ui.jpanels.adb.listener;

/**
 * 降低音量：
 * adb shell input keyevent 25
 */
public class VolumeMinusBtnAcListener extends AdShellInputKeyEventAcListener {
    @Override
    public void setKey() {
        key = "25";
    }
}