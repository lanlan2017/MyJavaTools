package adbs.action.listener.abs.shellinput;

import adbs.action.listener.abs.AdShellInputKeyEventAcListener;

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
