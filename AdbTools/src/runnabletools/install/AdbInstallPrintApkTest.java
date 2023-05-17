package runnabletools.install;

import tools.constant.QuanJiao;

import java.io.File;
import java.io.FilenameFilter;

public class AdbInstallPrintApkTest {
    private static void printAkps(String[] apkArray) {
        // System.out.println("-------------------------");
        // for (int i = 0; i < apkArray.length; i++) {
        //     System.out.println("    " + i + "\t" + apkArray[i]);
        // }
        // System.out.println("-------------------------");


        System.out.println("-------------------------");
        for (int i = 0; i < apkArray.length; i++) {
            // if (i > 0 && i % 2 == 0) {
            //     System.out.println("|");
            // }

            System.out.print("|");
            // int length = apkArray[i].length();
            int length =countSpace(apkArray[i]);

            int maxLength = 30;
            if (length < maxLength) {
                int diff = maxLength - length;
                for (int j = 0; j < diff; j++) {
                    System.out.print(QuanJiao.kongGe);
                    // System.out.print(" ");
                }
                System.out.print(apkArray[i]);
            }

                System.out.println("|");


        }
        System.out.println();
        System.out.println("-------------------------");
    }

    private static int countSpace(String input) {
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            char charAt = input.charAt(i);
            if (isASCII(charAt)) {
                // yingwen++;
                count++;
                // System.out.println("英文:"+charAt);
            } else {
                count++;
                count++;
                // System.out.println("中文:"+charAt);
            }
        }

        // // 如果英文的个数不是偶数个
        // if (yingwen % 2 != 0) {
        //     input = input + " ";
        // }
        // if (zhongwen % 2 != 0) {
        //     input = input + QuanJiao.kongGe;
        // }

        return count;
    }

    private static boolean isASCII(char charAt) {
        // return charAt >= '0' && charAt <= '9' || charAt >= 'a' && charAt <= 'z' || charAt >= 'A' && charAt <= 'Z';
        // return charAt >= ' ' && charAt <= '~';
        return charAt >= 32 && charAt <= 126;
    }

    public static void main(String[] args) {
        File apkDir = new File("D:\\网络共享\\可读可写\\apk");
        if (apkDir.isDirectory()) {
            // 获取.apk列表
            String[] apkArray = apkDir.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".apk");
                }
            });

            printAkps(apkArray);


        }
    }
}
