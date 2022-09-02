package adbs.action.listener.abs.shellinput;

import adbs.action.listener.abs.AdShellInputKeyEventAcListener;

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