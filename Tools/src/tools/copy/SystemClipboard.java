package tools.copy;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SystemClipboard {
    /**
     * 从剪贴板中获取文本.
     *
     * @return 如果剪贴板中没有文本, 则返回null.如果获取到文本, 则返回文本.
     */
    public static String getSysClipboardText() {
        // 代码兼容JDK6,不然剪贴板获取文本可能错误
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        String text = null;
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪切板中的内容
        Transferable clipTf = sysClip.getContents(null);

        if (clipTf != null) {
            // 检查内容是否是文本类型
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 转换为文本
                    text = (String) clipTf
                            .getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return text;
    }

    /**
     * 把字符串写到系统剪贴板。
     *
     * @param writeMe 要写入剪贴板的文本
     */
    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }

    /**
     * 从系统剪贴板获取图片。
     *
     * @return 系统剪贴板里面的图片。
     */
    public static BufferedImage getImageFromClipboard() {
        try {
            Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable cc = sysc.getContents(null);
            if (cc == null)
                return null;
            else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))
                return (BufferedImage) cc
                        .getTransferData(DataFlavor.imageFlavor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把图片复制到剪贴板中。
     *
     * @param image 要复制到剪贴板的图片。
     */
    public static void setClipboardImage(final Image image) {
        Transferable trans = new Transferable() {
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]
                        {DataFlavor.imageFlavor};
            }

            public boolean isDataFlavorSupported(DataFlavor flavor) {
                return DataFlavor.imageFlavor.equals(flavor);
            }

            public Object getTransferData(DataFlavor flavor)
                    throws UnsupportedFlavorException, IOException {
                if (isDataFlavorSupported(flavor))
                    return image;
                throw new UnsupportedFlavorException(flavor);
            }

        };
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans,
                null);
    }

}