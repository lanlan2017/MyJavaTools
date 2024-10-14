package adbs.main.ui.jpanels.time.listener;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.model.Device;
import config.AdbToolsProperties;

import java.util.ArrayList;

/**
 * 刷视频等待时间区间
 */
public class ShiPinQuJian {
    // LinkedHashMap<I,QuJian>
    private static final ArrayList<QuJian> list = new ArrayList<>();
    private static int index;
    private static final int size;

    // public ShiPinQuJian() {
    //     list.add(new QuJian(5, 10));
    //     list.add(new QuJian(6, 12));
    //     list.add(new QuJian(7, 14));
    //     list.add(new QuJian(8, 16));
    //     list.add(new QuJian(9, 18));
    //     list.add(new QuJian(10, 20));
    //     list.add(new QuJian(12, 22));
    //     list.add(new QuJian(15, 30));
    //     list.add(new QuJian(20, 40));
    //     list.add(new QuJian(30, 60));
    //     list.add(new QuJian(40, 80));
    //     list.add(new QuJian(60, 90));
    //     list.add(new QuJian(90, 120));
    //     list.add(new QuJian(120, 150));
    //     list.add(new QuJian(150, 240));
    //     list.add(new QuJian(240, 300));
    //     index = 5;
    //     size = list.size();
    // }

    static {
        list.add(new QuJian(5, 10));
        list.add(new QuJian(6, 12));
        list.add(new QuJian(7, 14));
        list.add(new QuJian(8, 16));
        list.add(new QuJian(9, 18));
        list.add(new QuJian(10, 20));
        list.add(new QuJian(12, 22));
        list.add(new QuJian(15, 30));
        list.add(new QuJian(20, 40));
        list.add(new QuJian(30, 60));
        list.add(new QuJian(40, 80));
        list.add(new QuJian(60, 90));
        list.add(new QuJian(90, 120));
        list.add(new QuJian(120, 150));
        list.add(new QuJian(150, 240));
        list.add(new QuJian(240, 300));
        // index = 5;
        // index = -1;
        index = 0;
        size = list.size();
    }


    public static void main(String[] args) {
        // QuJian quJian;
        // while (index < size) {
        //
        //     quJian = next();
        //     System.out.println(quJian);
        // }

        // for (; index < size; index++) {
        //     int start = list.init(index).start;
        //     int end = list.init(index).getEnd();
        //     System.out.print(start);
        //     System.out.println("," + end);
        //     // int size = list.size();
        //     // if (index < size) {
        //     //     index++;
        //     // }
        //
        // }
        // for (int i = 0; i < 50; i++) {
        //     System.out.println(next());
        // }
        //
        // for (int i = 0; i < 50; i++) {
        //     System.out.println(previous());
        // }
        while (hasNext()) {
            System.out.println(next());
        }

        System.out.println("index = " + index);
        System.out.println("size = " + size);
        System.out.println();
        while (hasPrevious()) {
            System.out.println(previous());
        }
    }


    public static boolean hasNext() {
        return index >= 0 && index < size - 1;
    }


    public static QuJian next() {

        return list.get(++index);
        // return list.init(index++);
    }

    public static QuJian init() {

        Device device = AdbTools.getInstance().getDevice();
        String appCHName = device.getDeviceAppCHName();
        switch (appCHName) {
            case "淘宝":
                index = 4;
                // index = 5;
                break;
            // case "点淘":
            // index = 6;
            // break;
            default:
                index = 6;
                break;
        }

        // index = 4;
        // index = 5;
        // index = 6;
        return next();
    }

    private static void getDeviceAppCHName(Device device) {
        String packageName = AdbGetPackage.getTopPackageName(device.getSerial());
        AdbToolsProperties.getAppCHName(packageName);
    }

    public static boolean hasPrevious() {
        return index > 0;
    }

    // previous
    public static QuJian previous() {
        return list.get(--index);
    }
}
