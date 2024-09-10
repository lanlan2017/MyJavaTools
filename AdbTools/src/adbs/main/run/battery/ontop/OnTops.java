package adbs.main.run.battery.ontop;

import adbs.main.AdbTools;
import tools.swing.dialog.DialogFactory;

import javax.swing.*;
import java.util.ArrayList;

/**
 * 窗体，弹窗的置顶状态
 */
public class OnTops {
    private final JFrame frame;
    private final boolean frameAlwaysOnTopOld;
    private final ArrayList<JDialog> dialogs;
    private final boolean[] dialogsTopsOld;

    public OnTops() {
        System.out.println("获取之前的置顶状态");
        frame = AdbTools.getInstance().getFrame();
        // 记下之前的窗体置顶状态
        frameAlwaysOnTopOld = frame.isAlwaysOnTop();
        // 记下工厂中所有的对话框的置顶状态
        dialogs = DialogFactory.getDialogs();
        dialogsTopsOld = new boolean[dialogs.size()];
        for (int i = 0; i < dialogsTopsOld.length; i++) {
            dialogsTopsOld[i] = dialogs.get(i).isAlwaysOnTop();
        }
    }

    //    public OnTops(JFrame frame, boolean frameAlwaysOnTopOld, ArrayList<JDialog> dialogs, boolean[] dialogsTopsOld) {
    //        this.frameAlwaysOnTopOld = frameAlwaysOnTopOld;
    //        this.dialogs = dialogs;
    //        this.dialogsTopsOld = dialogsTopsOld;
    //    }

    public boolean isFrameAlwaysOnTopOld() {
        return frameAlwaysOnTopOld;
    }

    public ArrayList<JDialog> getDialogs() {
        return dialogs;
    }

    public boolean[] getDialogsTopsOld() {
        return dialogsTopsOld;
    }

    public void allFalse() {
        System.out.println("取消所有的置顶");
        frame.setAlwaysOnTop(false);
        for (JDialog dialog : dialogs) {
            dialog.setAlwaysOnTop(false);
        }
    }

    /**
     * 恢复之前的置顶状态
     */
    public void restore() {
        System.out.println("恢复之前的置顶状态");
        frame.setAlwaysOnTop(frameAlwaysOnTopOld);
        //        for (JDialog dialog : dialogs) {
        //            dialog.setAlwaysOnTop(true);
        //        }
        for (int i = 0; i < dialogsTopsOld.length; i++) {
            JDialog jDialog = dialogs.get(i);
            jDialog.setAlwaysOnTop(dialogsTopsOld[i]);
        }
    }
}
