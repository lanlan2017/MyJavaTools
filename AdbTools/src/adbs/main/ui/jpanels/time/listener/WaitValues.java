package adbs.main.ui.jpanels.time.listener;

public class WaitValues {
    public static int[] values = {5, 8, 10, 15, 20, 30, 35, 65, 95, 120, 150, 3 * 60, 3 * 60 + 30, 4 * 60, 4 * 60 + 30, 5 * 60, 10 * 60, 12 * 60, 15 * 60, 20 * 60, 30 * 60, 40 * 60, 50 * 60, 60 * 60, 60 * 60 + 30 * 60, 2 * 60 * 60, 2 * 60 * 60 + 30 * 60, 3 * 60 * 60, 3 * 60 * 60 + 10 * 60, 4 * 60 * 60};
    // static String[] valueStrs = {"5", "8", "10", "15", "20", "30", "35", "65", "95", "120", "150", "3m", "3m+30", "4m", "4m+30", "5m", "10m", "12m", "15m", "20m", "30m", "40m", "50m", "1h", "2h", "3h", "3h+"};
    public static String[] valueStrs = {"", "", "", "", "", "", "", "", "", "2m", "2.5m", "3m", "3m+30", "4m", "4m+30", "5m", "10m", "12m", "15m", "20m", "30m", "40m", "50m", "1h", "1.5h", "2h", "2.5h", "3h", "3h+", "4h"};
    protected static int index = 0;

    public static void setIndex(int index) {
        WaitValues.index = index;
    }

    public static int getIndex() {
        return index;
    }

    /**
     * 获取正整数的位数，获取整数的长度。
     *
     * @param n int类型的正整数
     * @return 正整数的位数
     */

    protected int getNumLength(int n) {
        int l = 0;
        if (n < 0) {
            l = 0;
        } else if (n < 10) {
            l = 1;
        } else if (n < 100) {
            l = 2;
        } else if (n < 1000) {
            l = 3;
        } else if (n < 10000) {
            l = 4;
        } else if (n < 100000) {
            l = 5;
        } else if (n < 1000000) {
            l = 6;
        } else if (n < 10000000) {
            l = 7;
        } else if (n < 100000000) {
            l = 8;
        }
        return l;
    }


    /**
     * 大于或等于4的整数
     * greaterOrEqual4
     *
     * @param numLength 一个整数
     * @return 如果这个整数大于或等于4，则返回这个整数，如果这个整数小于4，则返回4
     */
    protected int greaterOrEqual4(int numLength) {
        return Math.max(numLength, 4);
    }

}
