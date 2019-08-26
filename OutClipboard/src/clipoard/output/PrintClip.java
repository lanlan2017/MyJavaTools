package clipoard.output;

import clipboard.util.SysClipboardUtil;

/**
 * 输出剪贴板中的内容.
 */
public class PrintClip {
    public static void main(String[] args) {
        System.out.print(SysClipboardUtil.getSysClipboardText());
    }
}
