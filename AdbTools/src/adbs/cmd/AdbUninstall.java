package adbs.cmd;

import adbs.main.AdbTools;
import config.AdbToolsProperties;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Consumer;

public class AdbUninstall {

    private static ArrayList<String> uninstallPackageList;

    public static void main(String[] args) {
        uninstallAllNonEssentialApps();

    }

    /**
     * 从当前Android设备中，卸载配置文件中保存的包名的APP
     */
    public static void uninstallAllNonEssentialApps() {
        uninstallPackageList = getUninstallPackageList();
        // printList(uninstallPackageList);
        Collections.sort(uninstallPackageList);

        String serial = AdbTools.getInstance().getDevice().getSerial();

        ArrayList<String> package3List = getPackage3List(serial);
        // printList(package3List);
        Collections.sort(package3List);

        if (uninstallPackageList.size() > 0 && package3List.size() > 0) {
            // findUninstall(package3List);
            // 使用二分查找 来卸载
            binarySearchUninstall(serial, package3List);
        }
    }

    /**
     * 从当前Android设备中，卸载配置文件中的APP。
     *
     * @param serial       要卸载的设备的序列号
     * @param package3List 保存该Android设备中的所有第三方APP的列表
     */
    private static void binarySearchUninstall(String serial, ArrayList<String> package3List) {

        // 获取第三方APP列表的迭代器
        Iterator<String> itPackage3 = package3List.iterator();

        while (itPackage3.hasNext()) {
            // 获取一个第第三方APP的包名
            String nextPackage3 = itPackage3.next();
            // 在要卸载的APP包名列表中查找 这个第三方APP的包名
            int i = Collections.binarySearch(uninstallPackageList, nextPackage3);
            // System.out.println("i = " + i);
            // 如果找到这个包名
            if (i >= 0) {
                System.out.println();
                System.out.println("要卸载 nextPackage3 = " + nextPackage3);
                String code = "adb -s " + serial + " uninstall " + nextPackage3;
                // System.out.println("code = " + code);
                AdbCommands.runAbdCmd(code);
            }
        }
        System.out.println("卸载完毕");

        JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), "卸载完毕");
    }

    private static void findUninstall(ArrayList<String> package3List) {
        Iterator<String> itUninstall = uninstallPackageList.iterator();
        // 遍历需要卸载的APP列表
        while (itUninstall.hasNext()) {
            String nextUninstall = itUninstall.next();
            // 遍历第Android设备上的第三方APP列表
            Iterator<String> itPackage3 = package3List.iterator();
            while (itPackage3.hasNext()) {
                String nextPackage3 = itPackage3.next();

                // 如果该APP是要卸载的APP
                if (nextPackage3.equals(nextUninstall)) {
                    System.out.println(nextUninstall + "  = " + nextPackage3);
                    // System.out.println(nextPackage3 + " = " + nextUninstall);
                    System.out.println("需要卸载 nextPackage3 = " + nextPackage3);


                    // 已经卸载掉的APP，不再参与后面的比较
                    itPackage3.remove();
                    break;
                } else {

                    System.out.println(nextUninstall + " != " + nextPackage3);
                    // System.out.println(nextPackage3 + " = " + nextUninstall);
                }
            }
            // 已经找过
            // itUninstall.remove();
        }
    }

    private static ArrayList<String> getUninstallPackageList() {
        ArrayList<String> needToUninstallPackageList = new ArrayList<>();
        String fileOfTheAppToBeUninstalled = AdbToolsProperties.moneyApkPro.getProperty("fileOfTheAppToBeUninstalled");
        System.out.println("fileOfTheAppToBeUninstalled = " + fileOfTheAppToBeUninstalled);

        File file = new File(fileOfTheAppToBeUninstalled);
        if (file.isFile()) {
            try {
                Scanner fs = new Scanner(file);
                while (fs.hasNextLine()) {
                    String lineFile = fs.nextLine();
                    // System.out.println("lineFile = |" + lineFile + "|");
                    if (lineFile.contains(".") && lineFile.contains(" ")) {
                        String substring = lineFile.substring(0, lineFile.indexOf(" "));
                        // System.out.println("substring = |" + substring + "|");
                        needToUninstallPackageList.add(substring);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return needToUninstallPackageList;
    }

    private static void printList(ArrayList<String> package3List) {
        package3List.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = |" + s + "|");
            }
        });
    }

    private static ArrayList<String> getPackage3List(String serial) {
        ArrayList<String> package3List = new ArrayList<>();

        String package_3 = AdbCommands.runAbdCmd("adb -s " + serial + " shell pm list packages -3");
        // System.out.println("package_3 = " + package_3);

        Scanner scanner = new Scanner(package_3);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("package:")) {
                String packageName = line.substring("package:".length());
                // System.out.println("packageName = |" + packageName + "|");
                package3List.add(packageName);
            }
        }
        scanner.close();
        return package3List;
    }
}
