package adbs.python;

import tools.file.Files;
import tools.format.date.DateFormatters;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Python生成器
 * 生成要执行的Python文件
 */
public class PythonGenerator {
    public static void main(String[] args) {

        // String pythonPathDir = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou";
        // String pythonPathDir = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin";
        // Python文件路径
        String pythonPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\WuKongLiuLanQi.py";
        updatePythonFile(pythonPath);
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
        // 生成图片数组
        String images = imagesInDir2Array(pythonPathDir);
        // System.out.println(images);
        // 拼接完整的Python代码
        String pythonCode = getPythonHead() + images + getPythonTail();
        //
        // System.out.println(pythonCode);
        // 把完整的Python代码写入Python文件
        Files.writerFile(pythonFile, pythonCode);
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

    /**
     * 生成一个存放 目录下所有'.png'文件的python数组
     *
     * @param pathname 目录的字符串名称（绝对路径）
     */
    private static String imagesInDir2Array(String pathname) {
        File dir = new File(pathname);
        if (dir.isDirectory()) {
            // 获取目录下的所有.png文件列表
            String[] pngList = dir.list((dir1, name) -> name.endsWith(".png"));
            if (pngList != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("images = [\n");
                for (int i = 0; i < pngList.length; i++) {
                    sb.append("    ");
                    sb.append("sys.path[0]+");
                    sb.append("\"\\");
                    // sb.append(png);
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
}
