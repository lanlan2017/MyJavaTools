package adbs.main.ui.jpanels.time.listener;

public class WaitValues {
    public static int[] values;
    /**
     * 5小时
     */
    protected static int h5 = 5 * 60 * 60;
    /**
     * 4小时
     */
    protected static int h4 = 4 * 60 * 60;
    /**
     * 3小时
     */
    protected static int h3 = 3 * 60 * 60;
    /**
     * 10分钟
     */
    protected static int m10 = 10 * 60;
    /**
     * 3小时10分钟
     */
    protected static int h3m10 = h3 + m10;
    /**
     * 2小时
     */
    protected static int h2 = 2 * 60 * 60;
    /**
     * 30分钟
     */
    protected static int m30 = 30 * 60;
    /**
     * 2小时30分钟
     */
    protected static int h2m30 = h2 + m30;
    /**
     * 1小时
     */
    protected static int h1 = 60 * 60;
    protected static int h1m30 = h1 + m30;
    protected static int m50 = 50 * 60;
    protected static int m40 = 40 * 60;
    protected static int m20 = 20 * 60;
    protected static int m15 = 15 * 60;
    protected static int m12 = 12 * 60;
    protected static int m5 = 5 * 60;
    protected static int m4s30 = 4 * 60 + 30;

    protected static int m4 = 4 * 60;
    protected static int m3s30 = 3 * 60 + 30;
    protected static int m3 = 3 * 60;

    static {
        values = new int[]{5, 8, 10, 15, 20, 30, 35, 65, 95, 120, 150, m3, m3s30, m4, m4s30, m5, m10, m12, m15, m20, m30, m40, m50, h1, h1m30, h2, h2m30, h3, h3m10, h4, h5};
    }

    // static String[] valueStrs = {"5", "8", "10", "15", "20", "30", "35", "65", "95", "120", "150", "3m", "3m+30", "4m", "4m+30", "5m", "10m", "12m", "15m", "20m", "30m", "40m", "50m", "1h", "2h", "3h", "3h+"};
    public static String[] valueStrs = {"", "", "", "", "", "", "", "", "", "2m", "2.5m", "3m", "3m+30", "4m", "4m+30", "5m", "10m", "12m", "15m", "20m", "30m", "40m", "50m", "1h", "1.5h", "2h", "2.5h", "3h", "3h+", "4h", "5h"};
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

    protected int getIndex(int time) {
        /**
         * 根据value查找对应的字符串
         */
        int index = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == time) {
                index = i;
                break;
            }
        }
        return index;
    }

    protected String getValueStr(int value) {
        int index = getIndex(value);
        // System.out.println("index_zzz = " + index);
        String valueStr = valueStrs[index];
        if ("".equals(valueStr) || valueStr == null) {
            valueStr = String.valueOf(value);
        }
        return valueStr;
    }

}
