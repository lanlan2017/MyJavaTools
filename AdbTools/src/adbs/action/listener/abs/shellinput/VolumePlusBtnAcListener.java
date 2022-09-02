package adbs.action.listener.abs.shellinput;

import adbs.action.listener.abs.AdShellInputKeyEventAcListener;

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

