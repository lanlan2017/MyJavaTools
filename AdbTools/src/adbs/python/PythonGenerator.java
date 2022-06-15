package adbs.python;

import adbs.test.Device;
import adbs.test.DeviceRadioBtAcListener;
import tools.copy.SystemClipboard;
import tools.file.Files;
import tools.format.date.DateFormatters;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Python生成器
 * 生成要执行的Python文件
 */
public class PythonGenerator {
    /**
     * 当前选择的id的别名
     */

    public static void main(String[] args) {

        // String pythonPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\TaskCenter";
        // String pythonPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\JinRiTouTiao";
        String pythonPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\Video";
        // String pythonPathDir = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin";
        // Python文件路径
        // String pythonPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\WuKongLiuLanQi.py";
        // updatePythonFile(pythonPath);
        String switchCases = imagesInDir2SwitchCases(pythonPath);
        System.out.println(switchCases);
        SystemClipboard.setSysClipboardText(switchCases);
    }

    /**
     * 生成一个存放 目录下所有'.png'文件的python数组
     *
     * @param dirPath 目录的字符串名称（绝对路径）
     */
    private static String imagesInDir2SwitchCases(String dirPath) {
        File dir = new File(dirPath);
        if (dir.isDirectory()) {
            // 获取目录下的所有.png文件列表
            String[] pngList = dir.list((dir1, name) -> name.endsWith(".png"));
            if (pngList != null) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pngList.length; i++) {
                    sb.append("case ");
                    sb.append("\"");
                    sb.append(pngList[i]);
                    sb.append("\"");
                    sb.append(":\n");
                    // if (pngList[i].startsWith("await_") && !pngList[i + 1].startsWith("await_")) {
                    if (isAppendBreak(pngList, i, "await_")) {
                        sb.append("    Robots.delay(50 * 1000);\n");
                        sb.append("    Robots.delay(20 * 1000);\n");
                        sb.append("    break;\n");
                        // } else if (pngList[i].startsWith("begin_") && !pngList[i + 1].startsWith("begin_")) {
                    } else if (isAppendBreak(pngList, i, "begin_")) {
                        sb.append("    Robots.leftClickThenRightClick(point, 35*1000);\n");
                        sb.append("    break;\n");
                    } else if (isAppendBreak(pngList, i, "exit_")) {
                        sb.append("default:\n");
                        sb.append("    Robots.leftMouseButtonClick(point);\n");
                        sb.append("    Robots.delay(2 * 1000);\n");
                        sb.append("    break;\n");
                    }

                    // sb.append("    break;");
                    // sb.append("\n");
                }
                return sb.toString();
            }
        }
        return "";
    }

    /**
     * 判断是否应该添加break语句。
     *
     * @param pngList 数组
     * @param i       当前遍历的坐标
     * @param prefix  数组元素开头子串
     * @return 如果在交界处，或者是最后一个元素时，返回true
     */
    private static boolean isAppendBreak(String[] pngList, int i, String prefix) {
        return pngList[i].startsWith(prefix) && (i == pngList.length - 1 || !pngList[i + 1].startsWith(prefix));
    }

    /**
     * 更新Python文件
     *
     * @param pythonPath Python文件的路径
     */
    public static void updatePythonFile(String pythonPath) {
        File pythonFile = new File(pythonPath);
        // 如果存在该文件
        if (!pythonFile.exists()) {
            try {
                // 创建文件
                pythonFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 获取Python文件所在的目录的路径
        String pythonPathDir = pythonPath.substring(0, pythonPath.lastIndexOf("\\"));
        String brand = Device.getBrand();

        // System.out.println("品牌名:" + simpleId);
        System.out.println("厂商:" + brand);
        // 生成图片数组
        String images = imagesInDir2Array(pythonPathDir, brand);
        // System.out.println(images);
        // 拼接完整的Python代码
        String pythonCode = getPythonHead() + images + getPythonTail();
        //
        // System.out.println(pythonCode);
        // 把完整的Python代码写入Python文件
        Files.writerFile(pythonFile, pythonCode);
        // imagesInDir2SwitchCases(pythonPathDir);
        SystemClipboard.setSysClipboardText(imagesInDir2SwitchCases(pythonPathDir));
    }

    // private static String getBrand() {
    //     String simpleId = Device.findSimpleId(DeviceRadioBtAcListener.getId()).toLowerCase();
    //     String brand = null;
    //     if (simpleId.contains("oppo")) {
    //         brand = "oppo";
    //     } else if (simpleId.contains("honor")) {
    //         brand = "honor";
    //     }
    //     return brand;
    // }

    /**
     * 生成一个存放 目录下所有'.png'文件的python数组
     *
     * @param dirPath 目录的字符串名称（绝对路径）
     * @param brand   手机的品牌，制造商
     * @return
     */
    private static String imagesInDir2Array(String dirPath, String brand) {
        File dir = new File(dirPath);
        if (dir.isDirectory()) {
            // 获取目录下的所有.png文件列表
            String[] pngList = dir.list((dir1, name) -> {
                return name.endsWith(".png") && name.toLowerCase().contains(brand);
            });
            if (pngList != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("images = [\n");
                for (int i = 0; i < pngList.length; i++) {
                    sb.append("    ");
                    sb.append("sys.path[0]+");
                    sb.append("\"");
                    sb.append("\\");
                    if (pngList[i].startsWith("b") || pngList[i].startsWith("a")) {
                        sb.append("\\");
                    }
                    sb.append(pngList[i]);

                    sb.append("\"");
                    if (i < pngList.length - 1) {
                        sb.append(",");
                    }
                    sb.append("\n");
                }
                sb.append("]\n");
                // System.out.println(sb);
                return sb.toString();
            }
        }
        return "";
    }

    /**
     * 获取Python头部代码
     *
     * @return Python头部代码
     */
    private static StringBuilder getPythonHead() {
        StringBuilder pythonHead = new StringBuilder();
        // Date date=new Date();
        String format = DateFormatters.yyyyMMddHHmmss.format(new Date());
        pythonHead.append("# " + format + " Java 自动生成\n");
        pythonHead.append("import pyautogui\n");
        pythonHead.append("import time\n");
        pythonHead.append("import sys\n");
        pythonHead.append("import os\n");
        pythonHead.append("\n");
        pythonHead.append("# 获取当前文件的文件名（简单文件名，不包含绝对路径）\n");
        pythonHead.append("name = str(os.path.basename(__file__))\n");
        pythonHead.append("# 从右向左查找字符'.'\n");
        pythonHead.append("index = name.rfind(\".\")\n");
        pythonHead.append("# 拼接出存放当前python进程pid的.txt文件的绝对路径\n");
        pythonHead.append("pidFile = sys.path[0]+\"\\\\\"+name[0:index]+\".txt\"\n");
        pythonHead.append("# 打开文件\n");
        pythonHead.append("f = open(pidFile, \"w\")\n");
        pythonHead.append("# 把当前文件的pid写入文件中\n");
        pythonHead.append("f.write(str(os.getpid()))\n");
        pythonHead.append("f.close()\n\n");
        return pythonHead;
    }

    /**
     * 获取Python尾部代码
     *
     * @return Python尾部代码
     */
    private static Appendable getPythonTail() {
        StringBuilder pythonTail = new StringBuilder();

        pythonTail.append("\n# 循环查找图片\n");
        pythonTail.append("flag = True\n");
        pythonTail.append("while flag:\n");
        pythonTail.append("    for x in images:\n");
        pythonTail.append("        # 在屏幕上查找指定的图片\n");
        pythonTail.append("        location = pyautogui.locateCenterOnScreen(x, confidence=0.9)\n");
        pythonTail.append("        # 如果找到指定的图片\n");
        pythonTail.append("        if location is not None:\n");
        pythonTail.append("            print(x[x.rfind(\"\\\\\")+1:], location)\n");
        pythonTail.append("            # 修改外层循环标志，退出外层循环\n");
        pythonTail.append("            flag = False\n");
        pythonTail.append("            # 退出内层循环\n");
        pythonTail.append("            break\n");
        pythonTail.append("        else:\n");
        pythonTail.append("            # 等待\n");
        // pythonTail.append("            time.sleep(0.1)\n");
        pythonTail.append("            time.sleep(0.05)\n");
        return pythonTail;
    }
}
