package templace.algorithm.sort.java;

import java.util.Arrays;

/**
 * <a href="https://mp.weixin.qq.com/s/wO11PDZSM5pQ0DfbQjKRQA">冒泡排序</a>:
 * 两两比较相邻元素,如果后面的小,就交换到前面.
 * 冒泡排序升级版本:<a href="https://mp.weixin.qq.com/s/tXqjWWyjQ1ILfvnFv3_f7Q">鸡尾酒排序</a>
 */
public class BubbleSort {
    /**
     * 冒泡排序最基础版本.
     *
     * @param array 待排序的数组.
     */
    private static void bubbleSort1(int array[]) {
        int tmp = 0;
        // 遍历n次
        for (int i = 0; i < array.length; i++) {
            // 遍历剩下未排序的元素
            for (int j = 0; j < array.length - i - 1; j++) {
                // 如果前面的大
                if (array[j] > array[j + 1]) {
                    // 保存大的数
                    tmp = array[j];
                    // 小的放前面
                    array[j] = array[j + 1];
                    // 大的放后面
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序优化版:如果已经有序,则不再排序
     * 利用布尔变量isSorted作为标记。如果在本轮排序中，元素有交换，则说明数列无序；如果没有元素交换，说明数列已然有序，直接跳出大循环
     *
     * @param array 待排序的数组.
     */

    private static void bubbleSort2(int array[]) {
        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            // 如果没有元素交换说明已经有序,就可结束了.
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 冒泡排序优化:
     * sortBorder就是无序数列的边界。每一轮排序过程中，sortBorder之后的元素就完全不需要比较了，肯定是有序的
     *
     * @param array 待排序的数组.
     */
    private static void bubbleSort3(int array[]) {
        int tmp = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                // 如果前面的数大于后面的数
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            // 如果没有交换元素,则是有序的,
            if (isSorted) {
                // 直接退出
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        bubbleSort1(array);
        System.out.println(Arrays.toString(array));
    }
}